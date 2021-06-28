package com.ryfa.MVP.interfaces;

import com.ryfa.MVP.fragments.dialog.LoadingDialogFragment;

public interface OnObjectResultCallBak<T> {
        void onStart(LoadingDialogFragment loadingDialog);

        void onResponse(T object, LoadingDialogFragment loadingDialog);

        void onFailure(int statusCode, LoadingDialogFragment loadingDialog);
    }