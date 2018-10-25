package com.taracorpora.aparatapp.view;

import com.taracorpora.aparatapp.model.AparatGroupMemberModel;

import java.util.List;

public interface GroupDetailView {
    void showGroupMember(List<AparatGroupMemberModel> member);
    void onError();
}
