package com.taracorpora.aparatapp.dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;

import com.taracorpora.aparatapp.R;

public class NewGroupDialogFragment extends DialogFragment {
    private EditText textGroupName;
    public NewGroupDialogFragment() {

    }

    public static NewGroupDialogFragment newInstance(String title) {
        NewGroupDialogFragment frag = new NewGroupDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }




    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_newgroup, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textGroupName = view.findViewById(R.id.txt_group_name);
        String title = getArguments().getString("title", "Enter Name");
        getDialog().setTitle(title);
        textGroupName.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        int width = 400;
        int height = 400;
        getDialog().getWindow().setLayout(width, height);
    }



    public void sendBackResult() {
        EditNameDialogListener listener = (EditNameDialogListener) getTargetFragment();
        listener.onFinishEditDialog(textGroupName.getText().toString());
        dismiss();
    }
}
