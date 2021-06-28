package com.ryfa.MVP.api;


import com.ryfa.MVP.models.AppModel;
import com.ryfa.MVP.models.PayModel;
import com.ryfa.MVP.models.UserModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

    String BASE_URL = "http://engineerit93.ir/sample/";
    String MESSAGE_ERROR = "خطا در برقراری ارتباط";


    @FormUrlEncoded
    @POST("user/signIn")
    Call<UserModel> signIn(@Field("mobile") String mobile);

    @FormUrlEncoded
    @POST("user/verifyUser")
    Call<UserModel> verifyUser(@Field("mobile") String mobile, @Field("verifyCode") String verifyCode);

    @FormUrlEncoded
    @POST("user/updateUser")
    Call<UserModel> updateUser(@Field("userId") int userId,
                               @Field("name") String name,
                               @Field("address") String address,
                               @Field("state") int stateId,
                               @Field("city") int cityId);


    @FormUrlEncoded
    @POST("app/addApp")
    Call<AppModel> addApp(@Field("userId") int userId,
                          @Field("name") String name,
                          @Field("categoryId") int category,
                          @Field("price") String price,
                          @Field("des") String des);

    @FormUrlEncoded
    @POST("app/getApps")
    Call<AppModel> getApps(@Field("userId") int userId);

    @FormUrlEncoded
    @POST("app/getOneApp")
    Call<AppModel> getOneApp(@Field("appId") int appId, @Field("userId") int userId);

    @FormUrlEncoded
    @POST("app/payApp")
    Call<PayModel> payApp(@Field("appId") int appId, @Field("userId") int userId);

    @FormUrlEncoded
    @POST("app/getPaysApp")
    Call<PayModel> getPaysApp(@Field("from") long from,
                              @Field("to") long to,
                              @Field("appId") int appId);

    @FormUrlEncoded
    @POST("app/getAllPaysApp")
    Call<PayModel> getAllPaysApp(@Field("userId") int userId);

    @FormUrlEncoded
    @POST("app/getPaysUser")
    Call<PayModel> getPaysUser(@Field("from") long from,
                               @Field("to") long to,
                               @Field("userId") int userId);


}
