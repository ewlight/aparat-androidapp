package com.taracorpora.aparatapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GroupDetailActivity extends AppCompatActivity {
    private ListView listanggota;
    private ListanggotaAdapter adapter;
    private List<ListanggotaModel> mlistanggotaModelList;
    private String fbid;
    private int groupId;
    private String groupName;
    private ImageView imageAddNewMember;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_detail);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            fbid = bundle.getString("fbid");
            groupId = bundle.getInt("groupid");
            groupName = bundle.getString("groupname");
            getSupportActionBar().setTitle(groupName.toString());
        }
        bindViewById();
        loadList();
        setClickListener();

    }

    private void loadList() {

        mlistanggotaModelList = new ArrayList<ListanggotaModel>();
        mlistanggotaModelList.add(new ListanggotaModel("fbid: "+fbid+" groupId: "+groupId));
        adapter = new ListanggotaAdapter(this, mlistanggotaModelList);
        listanggota.setAdapter(adapter);
        listanggota.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(GroupDetailActivity.this, "Click Prouduck Id", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void bindViewById(){
        imageAddNewMember = findViewById(R.id.image_add_new_member);
        listanggota = findViewById(R.id.listanggota);
    }

    private void setClickListener() {
        imageAddNewMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GroupDetailActivity.this, BarcodeScannerActivity.class);
                startActivity(intent);
            }
        });
    }
}


