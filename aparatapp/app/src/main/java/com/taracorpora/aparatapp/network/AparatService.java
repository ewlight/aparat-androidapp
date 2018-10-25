package com.taracorpora.aparatapp.network;

import com.taracorpora.aparatapp.model.AparatGroupMemberModel;
import com.taracorpora.aparatapp.model.AparatGroupModel;
import com.taracorpora.aparatapp.model.AparatGroupRequestModel;
import com.taracorpora.aparatapp.model.AparatPesertaModel;

import java.util.List;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;
import rx.Observable;

public interface AparatService {
    @POST("/login")
    Observable<AparatPesertaModel> login(@Body AparatPesertaModel aparatPesertaModel);

    @GET("/profile")
    Observable<AparatPesertaModel> profile(@Query("fbid") String fbid);

    @GET("/grouplist")
    Observable<List<AparatGroupModel>> grouplist(@Query("fbid") String fbid);

    @POST("/newgroup")
    Observable<AparatGroupRequestModel> newgroup(@Body AparatGroupRequestModel aparatGroupRequestModel);

    @GET("/groupmember")
    Observable<List<AparatGroupMemberModel>> groupmember(@Query("groupid") int groupid);
}
