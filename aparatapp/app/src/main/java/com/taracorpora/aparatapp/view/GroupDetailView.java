package com.taracorpora.aparatapp.view;

import com.taracorpora.aparatapp.model.AparatGroupMemberModel;
import com.taracorpora.aparatapp.model.AparatNewGroupMemberModel;

import java.util.List;

public interface GroupDetailView {
    void showGroupMember(List<AparatGroupMemberModel> member);
    void onError(String title, String message);
    void updateGroupMember(AparatNewGroupMemberModel groupMemberModel);
}
