package com.taracorpora.aparatapp.view;

import com.taracorpora.aparatapp.model.AparatGroupModel;

import java.util.List;

public interface GroupView {
    void onError();
    void showListView(List<AparatGroupModel> listGroup);
}
