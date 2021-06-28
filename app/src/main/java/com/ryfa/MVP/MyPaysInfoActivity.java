package com.ryfa.MVP;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;
import com.ryfa.MVP.adapters.rv.MyPaysInfoRvAdapter;
import com.ryfa.MVP.databinding.ActivityMyPaysInfoBinding;
import com.ryfa.MVP.fragments.dialog.LoadingDialogFragment;
import com.ryfa.MVP.general.PersianCalendar;
import com.ryfa.MVP.general.SharedPrefHandler;
import com.ryfa.MVP.interfaces.OnObjectResultCallBak;
import com.ryfa.MVP.models.PayModel;
import com.ryfa.MVP.models.UserModel;

import java.util.ArrayList;

public class MyPaysInfoActivity extends AppCompatActivity {

    ActivityMyPaysInfoBinding binding;
    Context context = this;
    Activity activity = this;
    ArrayList<Integer> list = new ArrayList<>();
    UserModel.User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyPaysInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        user = new Gson().fromJson(SharedPrefHandler.getStringPreference(context, "USER", ""), UserModel.User.class);

        for (int i = 0; i < 7; i++) {
            list.add(0);
        }

        PayModel.getAllPaysApp(user.getId(), new OnObjectResultCallBak<PayModel>() {
            @Override
            public void onStart(LoadingDialogFragment loadingDialog) {
                loadingDialog.show(getSupportFragmentManager(), LoadingDialogFragment.TAG);
            }

            @Override
            public void onResponse(PayModel object, LoadingDialogFragment loadingDialog) {
                loadingDialog.dismiss();
                if (object.getStatus() == 200) {
                    if (object.getPays().size() < 1) {

                    } else {
                        for (int i = 0; i < object.getPays().size(); i++) {

                            PayModel.Pay pay = object.getPays().get(i);
                            int day = PersianCalendar.getInstance(pay.getDate()).getPersianNumWeekDayStr();
                            list.set(day, list.get(day) + 1);

                            binding.rvPays.setLayoutManager(new LinearLayoutManager(context));
                            binding.rvPays.setAdapter(new MyPaysInfoRvAdapter(context,object.getPays()));

                        }

                    }
                }
            }

            @Override
            public void onFailure(int statusCode, LoadingDialogFragment loadingDialog) {
                loadingDialog.dismiss();
            }
        });

        binding.imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}