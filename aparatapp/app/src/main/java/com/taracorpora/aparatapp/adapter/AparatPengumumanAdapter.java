package com.taracorpora.aparatapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.taracorpora.aparatapp.R;
import com.taracorpora.aparatapp.model.AparatPengumumanModel;

import java.util.List;

public class AparatPengumumanAdapter extends BaseAdapter {
    private Context context;
    private List<AparatPengumumanModel> pengumumanModels;
    private TextView textPengumumanName;
    private TextView textPengumumanSubtitle;

    public AparatPengumumanAdapter(Context context, List<AparatPengumumanModel> pengumumanModels) {
        this.context = context;
        this.pengumumanModels = pengumumanModels;
    }

    private void bindViewById(View v) {
        textPengumumanName = v.findViewById(R.id.text_rapat_name);
        textPengumumanSubtitle = v.findViewById(R.id.text_rapat_subtitle);
    }

    private void populateData(int i) {
        textPengumumanName.setText(pengumumanModels.get(i).nama_rapat);
        textPengumumanSubtitle.setText(pengumumanModels.get(i).nama_group+" - tanggal "+pengumumanModels.get(i).tanggal);
    }


    @Override
    public int getCount() {
        return pengumumanModels.size();
    }

    @Override
    public Object getItem(int i) {
        return pengumumanModels.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.pengumuman_cardview_layout,null);
        bindViewById(v);
        populateData(i);
        return v;
    }


}
