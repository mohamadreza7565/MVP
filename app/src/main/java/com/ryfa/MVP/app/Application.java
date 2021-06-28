package com.ryfa.MVP.app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ryfa.MVP.api.ApiService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Application extends android.app.Application {

    Retrofit retrofit;
    private static ApiService apiService;

    @Override
    public void onCreate() {
        super.onCreate();

        Gson gson = new GsonBuilder().setLenient().create();
        OkHttpClient client = new OkHttpClient();
        retrofit = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(ApiService.BASE_URL)
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public static ApiService getApi() {
        return apiService;
    }
}
