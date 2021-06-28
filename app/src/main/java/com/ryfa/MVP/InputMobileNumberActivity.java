package com.ryfa.MVP;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.ryfa.MVP.api.ApiService;
import com.ryfa.MVP.databinding.ActivityInputMobileNumberBinding;
import com.ryfa.MVP.fragments.dialog.LoadingDialogFragment;
import com.ryfa.MVP.general.SharedPrefHandler;
import com.ryfa.MVP.interfaces.OnObjectResultCallBak;
import com.ryfa.MVP.models.UserModel;
import com.ryfa.MVP.widgets.SnackBar;
import com.ryfa.MVP.widgets.Toast;

public class InputMobileNumberActivity extends AppCompatActivity {

    public static final String START_ACTIVITY_TYPE = "TYPE";
    Context context = this;
    Activity activity = this;
    ActivityInputMobileNumberBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInputMobileNumberBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        onClick();
        changeUi();

    }

    private void onClick() {


        binding.llWrongNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.expVerifyCode.collapse(true);
                binding.etVerifyCode.setText("");
                changeUi();
            }
        });

        binding.imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!binding.etMobile.getText().toString().isEmpty() ||
                        binding.etMobile.getText().toString().startsWith("09") ||
                        binding.etMobile.getText().toString().length() == 11) {
                    if (!binding.expVerifyCode.isExpanded()) {
                        UserModel.login(binding.etMobile.getText().toString(), new OnObjectResultCallBak<UserModel>() {
                            @Override
                            public void onStart(LoadingDialogFragment loadingDialog) {
                                loadingDialog.show(getSupportFragmentManager(), LoadingDialogFragment.TAG);
                            }

                            @Override
                            public void onResponse(UserModel object, LoadingDialogFragment loadingDialog) {
                                loadingDialog.dismiss();
                                if (object.getStatus() == 200) {
                                    binding.expVerifyCode.expand(true);
                                    Toast.makeTEXT(context, "کد فعالسازی : " + object.getUser().getVerifyCode());
                                    changeUi();
                                } else {
                                    SnackBar.make(context, binding.layout, ApiService.MESSAGE_ERROR).showShort();
                                }
                            }

                            @Override
                            public void onFailure(int statusCode, LoadingDialogFragment loadingDialog) {
                                loadingDialog.dismiss();
                                SnackBar.make(context, binding.layout, ApiService.MESSAGE_ERROR).showShort();
                            }
                        });
                    } else {
                        if (!binding.etVerifyCode.getText().toString().isEmpty()) {
                            UserModel.verifyUser(binding.etMobile.getText().toString(), binding.etVerifyCode.getText().toString(), new OnObjectResultCallBak<UserModel>() {
                                @Override
                                public void onStart(LoadingDialogFragment loadingDialog) {
                                    loadingDialog.show(getSupportFragmentManager(), LoadingDialogFragment.TAG);
                                }

                                @Override
                                public void onResponse(UserModel object, LoadingDialogFragment loadingDialog) {
                                    loadingDialog.dismiss();
                                    switch (object.getStatus()) {
                                        case 200:
                                            SharedPrefHandler.setIntegerPreference(context,"USER_ID",object.getUser().getId());
                                            SharedPrefHandler.setStringPreference(context,"USER",new Gson().toJson(object.getUser()));
                                            Intent intent = new Intent(context, MainActivity.class);
                                            intent.putExtra("MOBILE", binding.etMobile.getText().toString());
                                            startActivity(intent);
                                            break;
                                    }
                                }

                                @Override
                                public void onFailure(int statusCode, LoadingDialogFragment loadingDialog) {
                                    loadingDialog.dismiss();
                                    SnackBar.make(context, binding.layout, ApiService.MESSAGE_ERROR).showShort();
                                }
                            });
                        } else {
                            SnackBar.make(context, binding.layout, "کد فعالسازی را وارد کنید").showLong();
                        }
                    }
                } else {
                    SnackBar.make(context, binding.layout, "فرمت شماره وارد شده اشتباه است").showLong();
                }
            }
        });
    }

    private void changeUi() {

        binding.tvTitle.setText("ورود به حساب کاربری");
        binding.tvSubmit.setText(binding.expVerifyCode.isExpanded() ? "ورود" : "دریافت کد فعالسازی");
        binding.llWrongNumber.setVisibility(binding.expVerifyCode.isExpanded() ? View.VISIBLE : View.GONE);
        binding.llSignup.setVisibility(View.VISIBLE);

    }


    @Override
    public void onBackPressed() {
        if (binding.expVerifyCode.isExpanded()) {
            binding.expVerifyCode.collapse(true);
            binding.etVerifyCode.setText("");
            changeUi();
        } else {
            finish();
        }
    }
}