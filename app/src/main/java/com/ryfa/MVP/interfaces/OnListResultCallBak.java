package com.ryfa.MVP.interfaces;

import com.ryfa.MVP.fragments.dialog.LoadingDialogFragment;

import java.util.ArrayList;

public interface OnListResultCallBak<T> {

        void onStart(LoadingDialogFragment dialog);

        void onResponse(ArrayList<T> list, LoadingDialogFragment loadingDialog);

        void onFailure(int statusCode, LoadingDialogFragment loadingDialog);
    }

