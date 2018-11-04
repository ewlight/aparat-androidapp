package com.taracorpora.aparatapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;
import com.taracorpora.aparatapp.HomeActivity;
import com.taracorpora.aparatapp.R;
import com.taracorpora.aparatapp.adapter.AparatPengumumanAdapter;
import com.taracorpora.aparatapp.model.AparatPengumumanModel;
import com.taracorpora.aparatapp.presenter.GroupPresenter;
import com.taracorpora.aparatapp.presenter.PengumumanPresenter;
import com.taracorpora.aparatapp.view.PengumumanView;

import java.util.List;

public class PengumumanFragment extends Fragment implements PengumumanView {
    private String fbid;
    private PengumumanPresenter presenter;
    private CircleProgressBar progressBar;
    private HomeActivity parentActivity;
    private ListView viewListPengumuman;
    private Context context;
    private AparatPengumumanAdapter adapter;

    public static PengumumanFragment newInstance(String fbid) {
        Bundle args = new Bundle();
        args.putString("fbid", fbid);
        PengumumanFragment fragment = new PengumumanFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void getDataFromBundle() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            fbid = bundle.getString("fbid");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getDataFromBundle();
        return inflater.inflate(R.layout.pengumuman_fragment, container, false);
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        parentActivity = (HomeActivity) getActivity();
        this.context = context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new PengumumanPresenter(this);
        bindViewById(view);
        fetchData();
    }

    @Override
    public void onResume() {
        super.onResume();
        fetchData();
    }

    private void bindViewById(View view) {
        progressBar = view.findViewById(R.id.progressbar_pengumuman_fragment);
        viewListPengumuman = view.findViewById(R.id.listview_pengumuman_list);

    }

    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    private void fetchData() {
        showProgressBar();
        presenter.getListPengumuman(fbid);
    }


    @Override
    public void showPengmumulanList(List<AparatPengumumanModel> listPengumuman) {
        adapter = new AparatPengumumanAdapter(context, listPengumuman);
        viewListPengumuman.setAdapter(adapter);
        hideProgressBar();
    }

    @Override
    public void onErrorShowPengumuman() {
        hideProgressBar();
    }
}
