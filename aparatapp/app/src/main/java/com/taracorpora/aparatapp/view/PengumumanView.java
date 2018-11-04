package com.taracorpora.aparatapp.view;

import com.taracorpora.aparatapp.model.AparatPengumumanModel;

import java.util.List;

public interface PengumumanView {
    void showPengmumulanList(List<AparatPengumumanModel> listPengumuman);
    void onErrorShowPengumuman();
}
