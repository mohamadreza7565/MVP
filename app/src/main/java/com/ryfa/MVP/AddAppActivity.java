package com.ryfa.MVP;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;
import com.ryfa.MVP.api.ApiService;
import com.ryfa.MVP.databinding.ActivityAddAppBinding;
import com.ryfa.MVP.fragments.dialog.CategoryDialogFragment;
import com.ryfa.MVP.fragments.dialog.LoadingDialogFragment;
import com.ryfa.MVP.general.SharedPrefHandler;
import com.ryfa.MVP.interfaces.OnObjectResultCallBak;
import com.ryfa.MVP.models.AppModel;
import com.ryfa.MVP.models.CategoryModel;
import com.ryfa.MVP.models.UserModel;
import com.ryfa.MVP.widgets.SnackBar;

public class AddAppActivity extends AppCompatActivity {

    ActivityAddAppBinding binding;
    Activity activity = this;
    Context context = this;
    UserModel.User user;
    AppModel.App newApp = new AppModel().new App();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddAppBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        user = new Gson().fromJson(SharedPrefHandler.getStringPreference(context, "USER", ""), UserModel.User.class);

        binding.imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.tvCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CategoryDialogFragment.newInstance().setOnItemClickListener(new CategoryDialogFragment.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position, CategoryModel category, CategoryDialogFragment dialogFragment) {
                        dialogFragment.dismiss();
                        binding.tvCategory.setText(category.getName());
                        newApp.setCategory(category.getId());
                    }
                }).show(getSupportFragmentManager(), CategoryDialogFragment.TAG);
            }
        });

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValue()) {
                    AppModel.addApp(newApp, new OnObjectResultCallBak<AppModel>() {
                        @Override
                        public void onStart(LoadingDialogFragment loadingDialog) {
                            loadingDialog.show(getSupportFragmentManager(), LoadingDialogFragment.TAG);
                        }

                        @Override
                        public void onResponse(AppModel object, LoadingDialogFragment loadingDialog) {
                            loadingDialog.dismiss();
                            if (object.getStatus() == 200) {
                                Intent intent = new Intent();
                                setResult(Activity.RESULT_OK, intent);
                                finish();
                            } else {
                                SnackBar.make(context, binding.layout, object.getMessage()).showShort();
                            }
                        }

                        @Override
                        public void onFailure(int statusCode, LoadingDialogFragment loadingDialog) {
                            loadingDialog.dismiss();
                            SnackBar.make(context, binding.layout, ApiService.MESSAGE_ERROR).showShort();

                        }
                    });
                }
            }
        });

    }

    private boolean checkValue() {

        if (binding.etName.getText().toString().isEmpty()) {
            SnackBar.make(context, binding.layout, "نام برنامه را وارد نکرده اید");
            return false;
        }
        if (binding.etDes.getText().toString().isEmpty()) {
            SnackBar.make(context, binding.layout, "توضیحات برنامه را وارد نکرده اید");
            return false;
        }
        if (binding.tvCategory.getText().toString().isEmpty()) {
            SnackBar.make(context, binding.layout, "دسته بندی برنامه را وارد نکرده اید");
            return false;
        }
        if (binding.etPrice.getText().toString().isEmpty()) {
            SnackBar.make(context, binding.layout, "قیمت برنامه را وارد نکرده اید");
            return false;
        }

        newApp.setPrice(binding.etPrice.getText().toString());
        newApp.setDescription(binding.etDes.getText().toString());
        newApp.setName(binding.etName.getText().toString());
        newApp.setUserId(user.getId());

        return true;
    }
}