package com.ryfa.MVP.presenters;

import android.content.Context;

import com.ryfa.MVP.api.ApiService;
import com.ryfa.MVP.contracts.MainContract;
import com.ryfa.MVP.fragments.dialog.LoadingDialogFragment;
import com.ryfa.MVP.interfaces.OnObjectResultCallBak;
import com.ryfa.MVP.models.AppModel;
import com.ryfa.MVP.models.PayModel;
import com.ryfa.MVP.models.UserModel;

import java.util.ArrayList;

public class MainPresenter implements MainContract.Presenter {

    MainContract.View view;
    ArrayList<AppModel.App> apps;
    UserModel.User user;
    Context context;

    public MainPresenter(Context context, UserModel.User user) {
        this.context = context;
        this.user = user;
    }

    @Override
    public void onAppClickItem(int position, AppModel.App app) {
        PayModel.payApp(app.getId(), user.getId(), new OnObjectResultCallBak<PayModel>() {
            @Override
            public void onStart(LoadingDialogFragment loadingDialog) {
                view.showLoading(loadingDialog);
            }

            @Override
            public void onResponse(PayModel object, LoadingDialogFragment loadingDialog) {
                view.dismissLoading(loadingDialog);
                if (object.getStatus() == 200) {
                    app.setPayed(1);
                    view.updateApp(position,app);
                } else {
                    view.showError(object.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, LoadingDialogFragment loadingDialog) {
                view.dismissLoading(loadingDialog);
                view.showError(ApiService.MESSAGE_ERROR);
            }
        });
    }

    @Override
    public void getApps(boolean showLoading) {
        AppModel.getAllApps(user.getId(), new OnObjectResultCallBak<AppModel>() {
            @Override
            public void onStart(LoadingDialogFragment loadingDialog) {
                if (showLoading)
                    view.showLoading(loadingDialog);
            }

            @Override
            public void onResponse(AppModel object, LoadingDialogFragment loadingDialog) {
                if (showLoading)
                    view.dismissLoading(loadingDialog);
                if (object.getStatus() == 200)
                    view.showApps(object.getApps());
                else
                    view.showError(object.getMessage());

                MainPresenter.this.apps = object.getApps();
            }

            @Override
            public void onFailure(int statusCode, LoadingDialogFragment loadingDialog) {
                if (showLoading)
                    view.dismissLoading(loadingDialog);
                view.showError(ApiService.MESSAGE_ERROR);
            }
        });
    }

    @Override
    public void onAttach(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void onDetach() {

    }
}
