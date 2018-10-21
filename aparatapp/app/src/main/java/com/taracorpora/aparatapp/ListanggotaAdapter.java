package com.taracorpora.aparatapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ListanggotaAdapter extends BaseAdapter {
    private Context mcontext;
    private List<ListanggotaModel> mlistanggotaModelList;

    public ListanggotaAdapter(Context mcontext, List<ListanggotaModel> mlistanggotaModelList) {
        this.mcontext = mcontext;
        this.mlistanggotaModelList = mlistanggotaModelList;
    }

    @Override
    public int getCount() {
        return mlistanggotaModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return mlistanggotaModelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View v = View.inflate(mcontext, R.layout.listview_anggota,null);
        TextView NamaAnggota = (TextView) v.findViewById(R.id.tv_anggota);
        NamaAnggota.setText(mlistanggotaModelList.get(position).getNamaanggota());
        return v;
    }
}
