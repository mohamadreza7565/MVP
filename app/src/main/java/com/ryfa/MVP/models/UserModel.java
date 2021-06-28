package com.ryfa.MVP.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ryfa.MVP.app.Application;
import com.ryfa.MVP.fragments.dialog.LoadingDialogFragment;
import com.ryfa.MVP.interfaces.OnObjectResultCallBak;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserModel {

    @SerializedName("status")
    @Expose
    int status;
    @SerializedName("result")
    @Expose
    boolean result;
    @SerializedName("message")
    @Expose
    String message;
    @SerializedName("user")
    @Expose
    User user;

    public int getStatus() {
        return status;
    }

    public UserModel setStatus(int status) {
        this.status = status;
        return this;
    }

    public boolean isResult() {
        return result;
    }

    public UserModel setResult(boolean result) {
        this.result = result;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public UserModel setMessage(String message) {
        this.message = message;
        return this;
    }

    public User getUser() {
        return user;
    }

    public UserModel setUser(User user) {
        this.user = user;
        return this;
    }

    public class User {
        @SerializedName("id")
        @Expose
        int id;
        @SerializedName("name")
        @Expose
        String name;
        @SerializedName("verify_code")
        @Expose
        String verifyCode;
        @SerializedName("mobile")
        @Expose
        String mobile;
        @SerializedName("state")
        @Expose
        int state;
        @SerializedName("city")
        @Expose
        int city;
        @SerializedName("state_name")
        @Expose
        String stateName;
        @SerializedName("city_name")
        @Expose
        String cityName;
        @SerializedName("address")
        @Expose
        String address;

        public String getAddress() {
            return address;
        }

        public User setAddress(String address) {
            this.address = address;
            return this;
        }

        public int getId() {
            return id;
        }

        public User setId(int id) {
            this.id = id;
            return this;
        }

        public String getName() {
            return name;
        }

        public User setName(String name) {
            this.name = name;
            return this;
        }

        public String getVerifyCode() {
            return verifyCode;
        }

        public User setVerifyCode(String verifyCode) {
            this.verifyCode = verifyCode;
            return this;
        }

        public String getMobile() {
            return mobile;
        }

        public User setMobile(String mobile) {
            this.mobile = mobile;
            return this;
        }

        public int getState() {
            return state;
        }

        public User setState(int state) {
            this.state = state;
            return this;
        }

        public int getCity() {
            return city;
        }

        public User setCity(int city) {
            this.city = city;
            return this;
        }

        public String getStateName() {
            return stateName;
        }

        public User setStateName(String stateName) {
            this.stateName = stateName;
            return this;
        }

        public String getCityName() {
            return cityName;
        }

        public User setCityName(String cityName) {
            this.cityName = cityName;
            return this;
        }

    }

    public static final void login(String mobile, OnObjectResultCallBak<UserModel> onObjectResultCallBak) {
        LoadingDialogFragment loadingDialogFragment = LoadingDialogFragment.newInstance();
        onObjectResultCallBak.onStart(loadingDialogFragment);

        Application.getApi().signIn(mobile).enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.isSuccessful()) {
                    onObjectResultCallBak.onResponse(response.body(), loadingDialogFragment);
                } else {
                    onObjectResultCallBak.onFailure(response.code(), loadingDialogFragment);
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                onObjectResultCallBak.onFailure(0, loadingDialogFragment);
            }
        });
    }

    public static final void verifyUser(String mobile, String verifyCode, OnObjectResultCallBak<UserModel> onObjectResultCallBak) {
        LoadingDialogFragment loadingDialogFragment = LoadingDialogFragment.newInstance();
        onObjectResultCallBak.onStart(loadingDialogFragment);

        Application.getApi().verifyUser(mobile, verifyCode).enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.isSuccessful()) {
                    onObjectResultCallBak.onResponse(response.body(), loadingDialogFragment);
                } else {
                    onObjectResultCallBak.onFailure(response.code(), loadingDialogFragment);
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                onObjectResultCallBak.onFailure(0, loadingDialogFragment);
            }
        });
    }

    public static final void updateUser(User user, OnObjectResultCallBak<UserModel> onObjectResultCallBak) {
        LoadingDialogFragment loadingDialogFragment = LoadingDialogFragment.newInstance();
        onObjectResultCallBak.onStart(loadingDialogFragment);

        Application.getApi().updateUser(user.getId(), user.getName(), user.getAddress(), user.getState(), user.getCity()).enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.isSuccessful()) {
                    onObjectResultCallBak.onResponse(response.body(), loadingDialogFragment);
                } else {
                    onObjectResultCallBak.onFailure(response.code(), loadingDialogFragment);
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                onObjectResultCallBak.onFailure(0, loadingDialogFragment);
            }
        });
    }

}
