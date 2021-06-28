package com.ryfa.MVP;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;

import com.ryfa.MVP.databinding.ActivitySplashBinding;
import com.ryfa.MVP.general.SetFullScreen;
import com.ryfa.MVP.general.SharedPrefHandler;

public class SplashActivity extends AppCompatActivity {

    ActivitySplashBinding binding;
    Context context = this;
    Activity activity=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SetFullScreen.newInstance(activity).removeNavigationBar();
        SetFullScreen.newInstance(activity).removeStatusBar();
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        new CountDownTimer(3000, 3000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                binding.pbLoading.setVisibility(View.VISIBLE);

            }
        }.start();
        new CountDownTimer(7000, 3000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Intent intent;
                if (SharedPrefHandler.getIntegerPreference(context, "USER_ID", 0) == 0)
                    intent = new Intent(context, InputMobileNumberActivity.class);
                else
                    intent = new Intent(context, MainActivity.class);

                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        }.start();

    }
}