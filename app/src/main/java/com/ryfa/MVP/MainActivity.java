package com.ryfa.MVP;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import com.ryfa.MVP.adapters.rv.BottomNavigationRvAdapter;
import com.ryfa.MVP.adapters.vp.ViewPagerAdapter;
import com.ryfa.MVP.databinding.ActivityMainBinding;
import com.ryfa.MVP.fragments.main.HomeFragment;
import com.ryfa.MVP.fragments.main.UserAccountFragment;
import com.ryfa.MVP.general.SetFullScreen;
import com.ryfa.MVP.models.BottomNavigationModel;
import com.ryfa.MVP.widgets.SnackBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    Context context = this;
    Activity activity = this;
    ArrayList<Fragment> fragments;
    HomeFragment homeFragment = HomeFragment.newInstance();
    UserAccountFragment userAccountFragment = UserAccountFragment.newInstance();
    ViewPagerAdapter viewPagerAdapter;
    ArrayList<BottomNavigationModel> bottomNavigationList;
    BottomNavigationRvAdapter bottomNavigationRvAdapter;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initViewPager();
    }

    private void initViewPager() {

        fragments = new ArrayList<>();
        fragments.add(homeFragment);
        fragments.add(userAccountFragment);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments);
        binding.viewPager.setAdapter(viewPagerAdapter);
        binding.viewPager.setOffscreenPageLimit(2);
        if (bottomNavigationList != null)
            if (bottomNavigationList.get(0).isSelect()) {
                binding.viewPager.setCurrentItem(0);
            } else {
                binding.viewPager.setCurrentItem(1);
            }
        initBottomNav();
    }

    private void initBottomNav() {

        bottomNavigationList = new ArrayList<>();

        getBottomNavItem();

        binding.rvBtnNav.setLayoutManager(new GridLayoutManager(context, 3) {

            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        bottomNavigationRvAdapter = new BottomNavigationRvAdapter(context, bottomNavigationList);
        binding.rvBtnNav.setAdapter(bottomNavigationRvAdapter);

        bottomNavigationRvAdapter.setOnItemClickListener(new BottomNavigationRvAdapter.OnItemClickListener() {
            @Override
            public void ItemClick(int position, BottomNavigationModel bottomNavigation) {

                if (bottomNavigation.getTab() == 3) {
                    SetFullScreen.newInstance(activity).changeStatusBarColor(getResources().getColor(R.color.colorIndigoDark));
                    SetFullScreen.newInstance(activity).setStatusBarIconDark();
                } else if (bottomNavigation.getTab() == 1) {
                    SetFullScreen.newInstance(activity).changeStatusBarColor(getResources().getColor(R.color.white));
                    SetFullScreen.newInstance(activity).setStatusBarIconLight();
                }

                if (bottomNavigation.getTab() == 1 || bottomNavigation.getTab() == 3) {
                    binding.viewPager.setCurrentItem(position, false);
                    if (!bottomNavigation.isSelect()) {
                        for (int i = 0; i < bottomNavigationList.size(); i++) {
                            bottomNavigationList.get(i).setSelect(false);
                        }

                        bottomNavigationList.get(position).setSelect(true);
                        bottomNavigationRvAdapter.notifyDataSetChanged();
                    }
                } else {
                    // add app
                    startActivityForResult(new Intent(context, AddAppActivity.class), 1001);
                }

            }
        });

    }

    private void getBottomNavItem() {
        bottomNavigationList.add(new BottomNavigationModel(R.drawable.ic_home, binding.viewPager.getCurrentItem() == 0 ? true : false, 1, homeFragment, "خانه"));
        bottomNavigationList.add(new BottomNavigationModel(R.drawable.ic_plus, false, 2, null, "افزودن برنامه"));
        bottomNavigationList.add(new BottomNavigationModel(R.drawable.ic_user, binding.viewPager.getCurrentItem() == 1 ? true : false, 3, userAccountFragment, "حساب کاربری"));
    }


    /**
     * Click the Back button
     */
    @Override
    public void onBackPressed() {
        // If the return button is clicked twice
        if (doubleBackToExitPressedOnce) {
            finishAffinity(); //Close the app
            return;
        }

        //If the return button is clicked once
        this.doubleBackToExitPressedOnce = true;

        //Build snackBar to display messages
        SnackBar.make(context, binding.layout, getString(R.string.press_twice_exit)).showLong();

        //If more than two seconds have passed since the user clicked
        new CountDownTimer(2000, 2000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                doubleBackToExitPressedOnce = false;
            }
        }.start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 1001) {
            homeFragment.getApps(false);
        }
    }

}