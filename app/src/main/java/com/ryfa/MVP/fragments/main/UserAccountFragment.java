package com.ryfa.MVP.fragments.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.ryfa.MVP.EditUserAccountActivity;
import com.ryfa.MVP.InputMobileNumberActivity;
import com.ryfa.MVP.ChartMyInfoPaysActivity;
import com.ryfa.MVP.MyPaysInfoActivity;
import com.ryfa.MVP.databinding.FragmentUserAccountBinding;
import com.ryfa.MVP.fragments.dialog.ConfirmDialogFragment;
import com.ryfa.MVP.general.SharedPrefHandler;
import com.ryfa.MVP.models.UserModel;


public class UserAccountFragment extends Fragment {

    FragmentUserAccountBinding binding;
    UserModel.User user;

    public static UserAccountFragment newInstance() {
        Bundle args = new Bundle();
        UserAccountFragment fragment = new UserAccountFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentUserAccountBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnChartInfoMyPays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ChartMyInfoPaysActivity.class));
            }
        });

        binding.btnInfoMyPays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MyPaysInfoActivity.class));
            }
        });

        binding.btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfirmDialogFragment.newInstance("خروج از حساب", "با اطمینان از خساب خود خارج می شوید؟", "بله", "خیر").setOnItemClickListener(new ConfirmDialogFragment.OnItemClickListener() {
                    @Override
                    public void onConfirmClick(ConfirmDialogFragment dialogFragment) {
                        dialogFragment.dismiss();
                        SharedPrefHandler.setStringPreference(getContext(), "USER", "");
                        Intent intent = new Intent(getContext(), InputMobileNumberActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelClick(ConfirmDialogFragment dialogFragment) {
                        dialogFragment.dismiss();
                    }
                }).show(getChildFragmentManager(), ConfirmDialogFragment.TAG);
            }
        });

        binding.btnUserAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), EditUserAccountActivity.class));
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        user = new Gson().fromJson(SharedPrefHandler.getStringPreference(getContext(), "USER", ""), UserModel.User.class);
        binding.tvName.setText(user.getName());
        binding.tvMobile.setText(user.getMobile());
    }


}
