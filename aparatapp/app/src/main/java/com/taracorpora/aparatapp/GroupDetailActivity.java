package com.taracorpora.aparatapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.taracorpora.aparatapp.adapter.ListanggotaAdapter;
import com.taracorpora.aparatapp.model.AparatGroupMemberModel;

import com.taracorpora.aparatapp.model.AparatNewGroupMemberModel;
import com.taracorpora.aparatapp.presenter.GroupDetailPresenter;
import com.taracorpora.aparatapp.view.GroupDetailView;

import java.util.List;

public class GroupDetailActivity extends AppCompatActivity implements GroupDetailView {
    ListView listanggota;
    ListanggotaAdapter adapter;
    String fbid;
    int groupId;
    String groupName;
    ImageView imageAddNewMember;
    GroupDetailPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_detail);
        Bundle bundle = getIntent().getExtras();
        presenter = new GroupDetailPresenter(this);
        if (bundle != null) {
            fbid = bundle.getString("fbid");
            groupId = bundle.getInt("groupid");
            groupName = bundle.getString("groupname");

        }
        bindViewById();
        getSupportActionBar().setTitle(groupName.toString());
        presenter.getGroupMember(groupId);
        setClickListener();

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
                startActivityForResult(intent, groupId);
            }
        });
    }

    @Override
    public void showGroupMember(List<AparatGroupMemberModel> member) {
        adapter = new ListanggotaAdapter(this, member);
        listanggota.setAdapter(adapter);

    }

    @Override
    public void onError(String title, String message) {
        dialogBuilder(title,message);
    }

    @Override
    public void updateGroupMember(AparatNewGroupMemberModel groupMemberModel) {
        presenter.getGroupMember(groupId);
        Toast.makeText(getApplicationContext(), "Member: " + groupMemberModel.memberid + " berhasil ditambahkan ke group", Toast.LENGTH_LONG).show();
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == groupId) {
            if (resultCode == RESULT_OK) {
                String invitedUser = data.getDataString();
                AparatNewGroupMemberModel newGroup = new AparatNewGroupMemberModel(invitedUser,groupId, groupName);
                presenter.saveNewMember(newGroup);
            }
        }
    }

    public void dialogBuilder(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }


}


