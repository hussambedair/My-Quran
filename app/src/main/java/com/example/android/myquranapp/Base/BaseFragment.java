package com.example.android.myquranapp.Base;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.afollestad.materialdialogs.MaterialDialog;

public class BaseFragment extends Fragment {

    BaseActivity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (BaseActivity) context;
    }

    public MaterialDialog showConfirmationMessage(int titleResId, int messageResId,
                                                  int posTextResId,
                                                  MaterialDialog.SingleButtonCallback onPosAction) {

        return activity.showConfirmationMessage(titleResId,messageResId,
                posTextResId, onPosAction);


    }


    public MaterialDialog showMessage(int titleResId, int messageResId,
                                      int posTextResId) {

        return activity.showMessage(titleResId,messageResId,posTextResId);


    }

    public MaterialDialog showMessage(String title, String message,
                                      String posText) {

        return activity.showMessage(title,message,posText);


    }











}
