package com.ryfa.MVP.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ryfa.MVP.app.Application;
import com.ryfa.MVP.fragments.dialog.LoadingDialogFragment;
import com.ryfa.MVP.interfaces.OnObjectResultCallBak;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppModel {

    @SerializedName("status")
    @Expose
    int status;
    @SerializedName("result")
    @Expose
    boolean result;
    @SerializedName("message")
    @Expose
    String message;
    @SerializedName("apps")
    @Expose
    ArrayList<App> apps;
    @SerializedName("app")
    @Expose
    App app;

    public int getStatus() {
        return status;
    }

    public AppModel setStatus(int status) {
        this.status = status;
        return this;
    }

    public boolean isResult() {
        return result;
    }

    public AppModel setResult(boolean result) {
        this.result = result;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public AppModel setMessage(String message) {
        this.message = message;
        return this;
    }

    public ArrayList<App> getApps() {
        return apps;
    }

    public AppModel setApps(ArrayList<App> apps) {
        this.apps = apps;
        return this;
    }

    public App getApp() {
        return app;
    }

    public AppModel setApp(App app) {
        this.app = app;
        return this;
    }

    public class App {
        @SerializedName("id")
        @Expose
        int id;
        @SerializedName("user_id")
        @Expose
        int userId;
        @SerializedName("payed")
        @Expose
        int payed;
        @SerializedName("name")
        @Expose
        String name;
        @SerializedName("category")
        @Expose
        int category;
        @SerializedName("des")
        @Expose
        String description;
        @SerializedName("price")
        @Expose
        String price;

        public int getId() {
            return id;
        }

        public App setId(int id) {
            this.id = id;
            return this;
        }

        public int getUserId() {
            return userId;
        }

        public App setUserId(int userId) {
            this.userId = userId;
            return this;
        }

        public int getPayed() {
            return payed;
        }

        public App setPayed(int payed) {
            this.payed = payed;
            return this;
        }

        public String getName() {
            return name;
        }

        public App setName(String name) {
            this.name = name;
            return this;
        }

        public int getCategory() {
            return category;
        }

        public App setCategory(int category) {
            this.category = category;
            return this;
        }

        public String getDescription() {
            return description;
        }

        public App setDescription(String description) {
            this.description = description;
            return this;
        }

        public String getPrice() {
            return price;
        }

        public App setPrice(String price) {
            this.price = price;
            return this;
        }

        @Override
        public String toString() {
            return ", userId=" + userId == null ? "" : userId + "&" +
                    ", name=" + name == null ? "" : name + "&" +
                    ", category=" + category == null ? "" : category + "&" +
                    ", price=" + price == null ? "" : price + "&" +
                    ", des=" + description == null ? "" : description;
        }
    }

    public final static void getAllApps(int userId, OnObjectResultCallBak<AppModel> onObjectResultCallBak) {

        LoadingDialogFragment loadingDialogFragment = LoadingDialogFragment.newInstance();

        onObjectResultCallBak.onStart(loadingDialogFragment);

        Application.getApi().getApps(userId).enqueue(new Callback<AppModel>() {
            @Override
            public void onResponse(Call<AppModel> call, Response<AppModel> response) {
                if (response.isSuccessful()) {
                    onObjectResultCallBak.onResponse(response.body(), loadingDialogFragment);
                } else {
                    onObjectResultCallBak.onFailure(response.code(), loadingDialogFragment);
                }
            }

            @Override
            public void onFailure(Call<AppModel> call, Throwable t) {
                onObjectResultCallBak.onFailure(0, loadingDialogFragment);
            }
        });

    }

    public final static void addApp(App app, OnObjectResultCallBak<AppModel> onObjectResultCallBak) {

        LoadingDialogFragment loadingDialogFragment = LoadingDialogFragment.newInstance();

        onObjectResultCallBak.onStart(loadingDialogFragment);

        Application.getApi().addApp(app.getUserId(),app.getName(),app.getCategory(),app.getPrice(),app.getDescription()).enqueue(new Callback<AppModel>() {
            @Override
            public void onResponse(Call<AppModel> call, Response<AppModel> response) {
                if (response.isSuccessful()) {
                    onObjectResultCallBak.onResponse(response.body(), loadingDialogFragment);
                } else {
                    onObjectResultCallBak.onFailure(response.code(), loadingDialogFragment);
                }
            }

            @Override
            public void onFailure(Call<AppModel> call, Throwable t) {
                onObjectResultCallBak.onFailure(0, loadingDialogFragment);
            }
        });

    }

    public final static void getOneApp(int appId, int userId, OnObjectResultCallBak<AppModel> onObjectResultCallBak) {

        LoadingDialogFragment loadingDialogFragment = LoadingDialogFragment.newInstance();

        onObjectResultCallBak.onStart(loadingDialogFragment);

        Application.getApi().getOneApp(appId, userId).enqueue(new Callback<AppModel>() {
            @Override
            public void onResponse(Call<AppModel> call, Response<AppModel> response) {
                if (response.isSuccessful()) {
                    onObjectResultCallBak.onResponse(response.body(), loadingDialogFragment);
                } else {
                    onObjectResultCallBak.onFailure(response.code(), loadingDialogFragment);
                }
            }

            @Override
            public void onFailure(Call<AppModel> call, Throwable t) {
                onObjectResultCallBak.onFailure(0, loadingDialogFragment);
            }
        });

    }

}
