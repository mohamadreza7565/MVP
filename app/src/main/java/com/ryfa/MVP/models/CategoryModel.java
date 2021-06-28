package com.ryfa.MVP.models;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ryfa.MVP.fragments.dialog.LoadingDialogFragment;
import com.ryfa.MVP.general.JsonReader;
import com.ryfa.MVP.interfaces.OnListResultCallBak;

import java.util.ArrayList;

public class CategoryModel {

    int id;
    String name;

    public int getId() {
        return id;
    }

    public CategoryModel setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CategoryModel setName(String name) {
        this.name = name;
        return this;
    }


    public static final void getCategories(Context context,OnListResultCallBak<CategoryModel> onListResultCallBak){
        LoadingDialogFragment loadingDialogFragment = LoadingDialogFragment.newInstance();

        onListResultCallBak.onStart(loadingDialogFragment);

        String json = JsonReader.fromAssets(context,"category");
        try {
            ArrayList<CategoryModel> list = new Gson().fromJson(json, new TypeToken<ArrayList<CategoryModel>>() {
            }.getType());
            onListResultCallBak.onResponse(list, loadingDialogFragment);
        } catch (Exception e) {
            onListResultCallBak.onFailure(0, loadingDialogFragment);
        }

    }
}
