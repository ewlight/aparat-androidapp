package com.taracorpora.aparatapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.taracorpora.aparatapp.ListanggotaModel;
import com.taracorpora.aparatapp.R;
import com.taracorpora.aparatapp.model.AparatGroupMemberModel;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListanggotaAdapter extends BaseAdapter {
    private Context mcontext;
    private List<AparatGroupMemberModel> groupMember;

    public ListanggotaAdapter(Context mcontext, List<AparatGroupMemberModel> groupMember) {
        this.mcontext = mcontext;
        this.groupMember = groupMember;
    }

    @Override
    public int getCount() {
        return groupMember.size();
    }

    @Override
    public Object getItem(int position) {
        return groupMember.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View v = View.inflate(mcontext, R.layout.listview_anggota,null);
        CircleImageView imageProfile = v.findViewById(R.id.image_profile_member);
        TextView textMemberName = v.findViewById(R.id.text_member_nama);
        TextView textMemberRole = v.findViewById(R.id.text_member_role);

        loadImage(groupMember.get(position).profilepict, imageProfile);
        textMemberName.setText(groupMember.get(position).name);
        if(groupMember.get(position).is_admin = true) {
            textMemberRole.setText("Admin");
        } else {
            textMemberRole.setText("Anggota");
        }


        return v;
    }

    private void loadImage(String img, CircleImageView view){
        Glide
                .with(mcontext)
                .load(img)
                .into(view);
    }
}
