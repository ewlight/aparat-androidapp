package com.taracorpora.aparatapp.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.taracorpora.aparatapp.HomeActivity;
import com.taracorpora.aparatapp.R;
import com.taracorpora.aparatapp.adapter.AparatGroupAdapter;
import com.taracorpora.aparatapp.model.AparatGroupModel;
import com.taracorpora.aparatapp.presenter.GroupPresenter;
import com.taracorpora.aparatapp.presenter.PengaturanPresenter;
import com.taracorpora.aparatapp.view.GroupView;

import java.util.List;

public class GroupFragment extends Fragment implements GroupView {

    private ListView listView;
    private HomeActivity parentActivity;
    private GroupPresenter presenter;
    private String fbid;
    private AparatGroupAdapter groupAdapter;
    private Context context;


    public static GroupFragment newInstance(String fbid) {
        Bundle args = new Bundle();
        args.putString("fbid", fbid);
        GroupFragment fragment = new GroupFragment();
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new GroupPresenter(this);
        bindViewById(view);
        presenter.getListGroup(fbid);
    }


    public void bindViewById(View view) {
        getDataFromBundle();
        listView = view.findViewById(R.id.listview_group_list);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_group, container, false);
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        parentActivity = (HomeActivity) getActivity();
        this.context = context;
    }

    @Override
    public void onError() {

    }

    @Override
    public void showListView(List<AparatGroupModel> listGroup) {
        groupAdapter = new AparatGroupAdapter(context, listGroup);
        listView.setAdapter(groupAdapter);

    }
}
