package com.taracorpora.aparatapp.network;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.taracorpora.aparatapp.model.AparatGroupMemberModel;
import com.taracorpora.aparatapp.model.AparatGroupModel;
import com.taracorpora.aparatapp.model.AparatGroupRequestModel;
import com.taracorpora.aparatapp.model.AparatPesertaModel;
import com.taracorpora.aparatapp.model.AparatNewGroupMemberModel;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;
import rx.Observable;

public class AparatNetworkManager {
    private AparatService aparatService;
    public static final String DATE_FORMAT = "yyyy-MM-dd'T'hh:mm:ssZ";
    private GeneralNetworkHandler generalNetworkHandler;
    private static final String TAG = AparatNetworkManager.class.getSimpleName();
    private static final String BASE_URL = "http://taracorpora.com/aparat/index.php/aparat";

    public AparatNetworkManager(GeneralNetworkHandler generalNetworkHandler) {
        this.generalNetworkHandler = generalNetworkHandler;
        initializeNetworkInterface();
    }

    private void initializeNetworkInterface() {

        Gson gson = gsonHandler(new GsonBuilder().setPrettyPrinting())
                .setDateFormat(DATE_FORMAT)
                .create();

        RestAdapter.Builder restAdapterBuilder = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .setConverter(new GsonConverter(gson))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setLog(new RestAdapter.Log() {
                    @Override
                    public void log(String message) {
                        Log.d(TAG, message);
                    }
                });
        aparatService = restAdapterBuilder.build().create(AparatService.class);
    }

    private GsonBuilder gsonHandler(GsonBuilder builder) {
        builder.registerTypeAdapterFactory(new ResponseTypeAdapterFactory());
        return builder;
    }

    private class ResponseTypeAdapterFactory implements TypeAdapterFactory {

        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            final TypeAdapter<T> delegate = gson.getDelegateAdapter(this, type);
            final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);

            return new TypeAdapter<T>() {
                @Override
                public void write(JsonWriter out, T value) throws IOException {
                    delegate.write(out, value);
                }

                @Override
                public T read(JsonReader in) throws IOException {

                    JsonElement jsonElement = elementAdapter.read(in);
                    if (jsonElement.isJsonObject()) {
                        JsonObject jsonObject = jsonElement.getAsJsonObject();
                        if (jsonObject.has("data") && jsonObject.get("data").isJsonObject()) {
                            jsonElement = jsonObject.get("data");
                        } else if (jsonObject.has("data") && jsonObject.get("data").isJsonArray()) {
                            jsonElement = jsonObject.get("data").getAsJsonArray();
                        }
                    }
                    return delegate.fromJsonTree(jsonElement);
                }
            }.nullSafe();
        }
    }

    private <T> Observable<T> addInterceptor(final Observable<T> originObservable) {
        return originObservable
                .doOnSubscribe(generalNetworkHandler::onRequesting)
                .doOnTerminate(generalNetworkHandler::onRequestEnd)
                .onErrorResumeNext(throwable -> {
                    return handleNetworkError(originObservable, throwable);
                });
    }

    private <T> Observable<T> handleNetworkError(final Observable<T> originObservable,
                                                 final Throwable throwable) {
        if (throwable instanceof RetrofitError) {
            return onRetrofitError(originObservable, throwable);
        }
        return Observable.error(throwable);
    }

    private <T> Observable<T> onRetrofitError(final Observable<T> originObservable,
                                              final Throwable throwable) {
        RetrofitError retrofitError = (RetrofitError) throwable;
        if (retrofitError.getKind().equals(RetrofitError.Kind.NETWORK)) {
            onRetrofitErrorNetwork(retrofitError);
        } else if (retrofitError.getKind().equals(RetrofitError.Kind.HTTP)) {
            return onRetrofitErrorCode(originObservable, retrofitError, throwable);
        }
        return Observable.error(throwable);
    }

    private void onRetrofitErrorNetwork(RetrofitError retrofitError) {
        if (retrofitError.getCause() instanceof UnknownHostException) {
            generalNetworkHandler.onNoInternetConnection();
        } else {
            generalNetworkHandler.onNetworkProblem();
        }
    }

    private <T> Observable<T> onRetrofitErrorCode(final Observable<T> originObservable,
                                                  final RetrofitError retrofitError,
                                                  final Throwable throwable) {
        Response response = retrofitError.getResponse();
        if (response != null) {
            generalNetworkHandler.onFailedToProcessRequest(response);
        }
        return Observable.error(throwable);
    }

    public Observable<AparatPesertaModel> postLogin(AparatPesertaModel aparatPesertaModel) {
        return addInterceptor(aparatService.login(aparatPesertaModel));
    }

    public Observable<AparatPesertaModel> getPeserta(String fbid) {
        return addInterceptor(aparatService.profile(fbid));
    }

    public Observable<List<AparatGroupModel>> getGroup(String fb) {
        return addInterceptor(aparatService.grouplist(fb));
    }

    public Observable<AparatGroupRequestModel> postNewGroup(AparatGroupRequestModel aparatGroupRequestModel) {
        return addInterceptor(aparatService.newgroup(aparatGroupRequestModel));
    }

    public Observable<List<AparatGroupMemberModel>> getGroupMember(int groupId) {
        return addInterceptor(aparatService.groupmember(groupId));
    }

    public Observable postGroupMember(AparatNewGroupMemberModel groupMember) {
        return addInterceptor(aparatService.inviteNewMember(groupMember));
    }
}
