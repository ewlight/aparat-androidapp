package com.taracorpora.aparatapp;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Pengumuman extends Fragment {
    private ListView listPengumuman;
    private ListpengumumanAdapter adapter;
    private List<ListpengumumanModel> mlistpengumumanModelList;
    private Context context;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        context = getActivity();
        return inflater.inflate(R.layout.pengumuman1, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listPengumuman = view.findViewById(R.id.lisviewpengumuman);

        mlistpengumumanModelList = new ArrayList<ListpengumumanModel>();
        mlistpengumumanModelList.add(new ListpengumumanModel("Judul", "Nama" , "Tanggal"));
        mlistpengumumanModelList.add(new ListpengumumanModel("Judul", "Nama" , "Tanggal"));
        mlistpengumumanModelList.add(new ListpengumumanModel("Judul", "Nama" , "Tanggal"));
        adapter = new ListpengumumanAdapter(context,mlistpengumumanModelList);
        listPengumuman.setAdapter(adapter);
        listPengumuman.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(getContext(), "Click Prouduck Id", Toast.LENGTH_SHORT).show();

            }
        });


    }
}
