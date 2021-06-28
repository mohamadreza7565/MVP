package com.ryfa.MVP.fragments.main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.ryfa.MVP.adapters.rv.AppsRvAdapter;
import com.ryfa.MVP.contracts.MainContract;
import com.ryfa.MVP.databinding.FragmentHomeBinding;
import com.ryfa.MVP.fragments.dialog.LoadingDialogFragment;
import com.ryfa.MVP.general.SharedPrefHandler;
import com.ryfa.MVP.models.AppModel;
import com.ryfa.MVP.models.UserModel;
import com.ryfa.MVP.presenters.MainPresenter;
import com.ryfa.MVP.widgets.Toast;

import java.util.ArrayList;


public class HomeFragment extends Fragment implements MainContract.View {

    FragmentHomeBinding binding;
    UserModel.User user;
    AppsRvAdapter adapter;
    ArrayList<AppModel.App> list = new ArrayList<>();
    MainContract.Presenter presenter;

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onClick();
        user = new Gson().fromJson(SharedPrefHandler.getStringPreference(getContext(), "USER", ""), UserModel.User.class);

        presenter = new MainPresenter(getContext(), user);
        presenter.onAttach(this);
        binding.rvApps.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new AppsRvAdapter(getContext(), list).setOnItemClickListener(new AppsRvAdapter.OnItemClickListener() {
            @Override
            public void onPayClick(int position, AppModel.App app) {
                presenter.onAppClickItem(position, app);
            }
        });
        binding.rvApps.setAdapter(adapter);

        presenter.getApps(true);


    }

    @SuppressLint("ClickableViewAccessibility")
    private void onClick() {


    }

    @Override
    public void showApps(ArrayList<AppModel.App> apps) {

        if (apps.isEmpty()) {
            setEmptyApp(true);
            return;
        } else {
            setEmptyApp(false);
        }

        list.clear();
        list.addAll(apps);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void getApps(boolean showLoading) {
        presenter.getApps(showLoading);
    }

    @Override
    public void addApp(AppModel.App app) {
        list.add(app);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void updateApp(int position, AppModel.App app) {
        list.set(position, app);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setEmptyApp(boolean visible) {
        if (visible) {
            Toast.makeTEXT(getContext(), "خالی");
        }
    }

    @Override
    public void showError(String message) {
        Toast.makeTEXT(getContext(), message);
    }


    @Override
    public void showLoading(LoadingDialogFragment loading) {
        binding.pbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissLoading(LoadingDialogFragment loading) {
        binding.pbLoading.setVisibility(View.GONE);
    }
}
