package com.taracorpora.aparatapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.taracorpora.aparatapp.R;
import com.taracorpora.aparatapp.model.AparatGroupModel;

import java.util.List;

public class AparatGroupAdapter extends BaseAdapter {


    private Context context;
    private List<AparatGroupModel> groupList;

    public AparatGroupAdapter(Context context, List<AparatGroupModel> groupList){
        this.context = context;
        this.groupList = groupList;

    }


    @Override
    public int getCount() {
        return groupList.size();
    }

    @Override
    public Object getItem(int i) {
        return groupList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.item_cardview_layout,null);
        TextView textNamaGroup = v.findViewById(R.id.text_list_name);
        textNamaGroup.setText(groupList.get(i).name);
        return v;
    }
}
