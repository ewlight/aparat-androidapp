package com.taracorpora.aparatapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class ListpengumumanAdapter extends BaseAdapter {
    private Context mcontext;
    private List<ListpengumumanModel> mlistpengumumanModelList;

    public ListpengumumanAdapter(Context mcontext, List<ListpengumumanModel> mlistpengumumanModelList) {
        this.mcontext = mcontext;
        this.mlistpengumumanModelList = mlistpengumumanModelList;
    }

    @Override
    public int getCount() {
        return mlistpengumumanModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return mlistpengumumanModelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View v = View.inflate(mcontext, R.layout.listpengumuman,null);
        TextView Judul = (TextView) v.findViewById(R.id.judul);
        TextView Nama = (TextView) v.findViewById(R.id.nama);
        TextView Tanggal = (TextView) v.findViewById(R.id.tanggal);
        Judul.setText(mlistpengumumanModelList.get(position).getJudul());
        Nama.setText(mlistpengumumanModelList.get(position).getNama());
        Tanggal.setText(mlistpengumumanModelList.get(position).getTanggal());
        return v;
    }
}
