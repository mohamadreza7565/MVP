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

public class PayModel {

    @SerializedName("status")
    @Expose
    int status;
    @SerializedName("result")
    @Expose
    boolean result;
    @SerializedName("message")
    @Expose
    String message;
    @SerializedName("pay")
    @Expose
    Pay pay;
    @SerializedName("pays")
    @Expose
    ArrayList<Pay> pays;

    public int getStatus() {
        return status;
    }

    public PayModel setStatus(int status) {
        this.status = status;
        return this;
    }

    public boolean isResult() {
        return result;
    }

    public PayModel setResult(boolean result) {
        this.result = result;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public PayModel setMessage(String message) {
        this.message = message;
        return this;
    }

    public Pay getPay() {
        return pay;
    }

    public PayModel setPay(Pay pay) {
        this.pay = pay;
        return this;
    }

    public ArrayList<Pay> getPays() {
        return pays;
    }

    public PayModel setPays(ArrayList<Pay> pays) {
        this.pays = pays;
        return this;
    }

    public class Pay {
        @SerializedName("id")
        @Expose
        int id;
        @SerializedName("user_id")
        @Expose
        int userId;
        @SerializedName("app_id")
        @Expose
        int appId;
        @SerializedName("date")
        @Expose
        long date;
        @SerializedName("price")
        @Expose
        String price;

        public int getId() {
            return id;
        }

        public Pay setId(int id) {
            this.id = id;
            return this;
        }

        public int getUserId() {
            return userId;
        }

        public Pay setUserId(int userId) {
            this.userId = userId;
            return this;
        }

        public int getAppId() {
            return appId;
        }

        public Pay setAppId(int appId) {
            this.appId = appId;
            return this;
        }

        public long getDate() {
            return date;
        }

        public Pay setDate(long date) {
            this.date = date;
            return this;
        }

        public String getPrice() {
            return price;
        }

        public Pay setPrice(String price) {
            this.price = price;
            return this;
        }
    }

    public static final void payApp(int appId, int userId, OnObjectResultCallBak<PayModel> onObjectResultCallBak) {
        LoadingDialogFragment loadingDialogFragment = LoadingDialogFragment.newInstance();
        onObjectResultCallBak.onStart(loadingDialogFragment);

        Application.getApi().payApp(appId, userId).enqueue(new Callback<PayModel>() {
            @Override
            public void onResponse(Call<PayModel> call, Response<PayModel> response) {
                if (response.isSuccessful()) {
                    onObjectResultCallBak.onResponse(response.body(), loadingDialogFragment);
                } else {
                    onObjectResultCallBak.onFailure(response.code(), loadingDialogFragment);
                }
            }

            @Override
            public void onFailure(Call<PayModel> call, Throwable t) {
                onObjectResultCallBak.onFailure(0, loadingDialogFragment);
            }
        });
    }

    public static final void getPaysApp(long from, long to, int appId, OnObjectResultCallBak<PayModel> onObjectResultCallBak) {
        LoadingDialogFragment loadingDialogFragment = LoadingDialogFragment.newInstance();
        onObjectResultCallBak.onStart(loadingDialogFragment);

        Application.getApi().getPaysApp(from, to, appId).enqueue(new Callback<PayModel>() {
            @Override
            public void onResponse(Call<PayModel> call, Response<PayModel> response) {
                if (response.isSuccessful()) {
                    onObjectResultCallBak.onResponse(response.body(), loadingDialogFragment);
                } else {
                    onObjectResultCallBak.onFailure(response.code(), loadingDialogFragment);
                }
            }

            @Override
            public void onFailure(Call<PayModel> call, Throwable t) {
                onObjectResultCallBak.onFailure(0, loadingDialogFragment);
            }
        });
    }

    public static final void getAllPaysApp(int userId,OnObjectResultCallBak<PayModel> onObjectResultCallBak) {
        LoadingDialogFragment loadingDialogFragment = LoadingDialogFragment.newInstance();
        onObjectResultCallBak.onStart(loadingDialogFragment);

        Application.getApi().getAllPaysApp(userId).enqueue(new Callback<PayModel>() {
            @Override
            public void onResponse(Call<PayModel> call, Response<PayModel> response) {
                if (response.isSuccessful()) {
                    onObjectResultCallBak.onResponse(response.body(), loadingDialogFragment);
                } else {
                    onObjectResultCallBak.onFailure(response.code(), loadingDialogFragment);
                }
            }

            @Override
            public void onFailure(Call<PayModel> call, Throwable t) {
                onObjectResultCallBak.onFailure(0, loadingDialogFragment);
            }
        });
    }

    public static final void getPaysUser(long from, long to, int userId, OnObjectResultCallBak<PayModel> onObjectResultCallBak) {
        LoadingDialogFragment loadingDialogFragment = LoadingDialogFragment.newInstance();
        onObjectResultCallBak.onStart(loadingDialogFragment);

        Application.getApi().getPaysUser(from, to, userId).enqueue(new Callback<PayModel>() {
            @Override
            public void onResponse(Call<PayModel> call, Response<PayModel> response) {
                if (response.isSuccessful()) {
                    onObjectResultCallBak.onResponse(response.body(), loadingDialogFragment);
                } else {
                    onObjectResultCallBak.onFailure(response.code(), loadingDialogFragment);
                }
            }

            @Override
            public void onFailure(Call<PayModel> call, Throwable t) {
                onObjectResultCallBak.onFailure(0, loadingDialogFragment);
            }
        });
    }

}
