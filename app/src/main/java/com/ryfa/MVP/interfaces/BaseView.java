package com.ryfa.MVP.interfaces;

import com.ryfa.MVP.fragments.dialog.LoadingDialogFragment;

public interface BaseView {

    void showLoading(LoadingDialogFragment loading);

    void dismissLoading(LoadingDialogFragment loading);

}
