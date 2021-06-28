package com.ryfa.MVP;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.ryfa.MVP.api.ApiService;
import com.ryfa.MVP.databinding.ActivityEditUserAccountBinding;
import com.ryfa.MVP.fragments.dialog.LoadingDialogFragment;
import com.ryfa.MVP.general.SharedPrefHandler;
import com.ryfa.MVP.interfaces.OnObjectResultCallBak;
import com.ryfa.MVP.models.UserModel;
import com.ryfa.MVP.widgets.SnackBar;


public class EditUserAccountActivity extends AppCompatActivity {

    Context context = this;
    Activity activity = this;
    ActivityEditUserAccountBinding binding;
    UserModel.User user;
    UserModel.User newUser = new UserModel().new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditUserAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        user = new Gson().fromJson(SharedPrefHandler.getStringPreference(context, "USER", ""), UserModel.User.class);
        newUser.setId(user.getId());
        binding.tvMobile.setText(user.getMobile());
        binding.etFullName.setText(user.getName());
        binding.etAddress.setText(user.getAddress());

        binding.btnEditUserAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkValue()) {
                    UserModel.updateUser(newUser, new OnObjectResultCallBak<UserModel>() {
                        @Override
                        public void onStart(LoadingDialogFragment loadingDialog) {
                            loadingDialog.show(getSupportFragmentManager(), LoadingDialogFragment.TAG);
                        }

                        @Override
                        public void onResponse(UserModel object, LoadingDialogFragment loadingDialog) {
                            loadingDialog.dismiss();
                            if (object.getStatus() == 200) {
                                SharedPrefHandler.setStringPreference(context, "USER", new Gson().toJson(object.getUser()));
                                SnackBar.make(context, binding.layout, "با موفقیت ویرایش شد").showShort();
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

        binding.imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private boolean checkValue() {


        if (binding.etFullName.getText().toString().isEmpty()) {
            SnackBar.make(context, binding.layout, "نام خود را وارد نکرده اید").showShort();
            return false;
        } else {
            newUser.setName(binding.etFullName.getText().toString());
        }

        newUser.setMobile(binding.tvMobile.getText().toString());
        if (!binding.etAddress.getText().toString().isEmpty()) {
            newUser.setAddress(binding.etAddress.getText().toString());
        }

        return true;
    }
}