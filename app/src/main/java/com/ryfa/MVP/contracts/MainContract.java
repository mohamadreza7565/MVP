package com.ryfa.MVP.contracts;

import com.ryfa.MVP.interfaces.BasePresenter;
import com.ryfa.MVP.interfaces.BaseView;
import com.ryfa.MVP.models.AppModel;

import java.util.ArrayList;

public interface MainContract {

    interface View extends BaseView {

        void showApps(ArrayList<AppModel.App> apps);

        void getApps(boolean showLoading);

        void addApp(AppModel.App app);

        void updateApp(int position,AppModel.App app);

        void setEmptyApp(boolean visible);

        void showError(String message);

    }

    interface Presenter extends BasePresenter<View> {

        void onAppClickItem(int position,AppModel.App app);

        void getApps(boolean showLoading);

    }

}
