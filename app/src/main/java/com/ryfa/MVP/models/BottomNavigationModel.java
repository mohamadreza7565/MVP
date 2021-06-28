package com.ryfa.MVP.models;


import androidx.fragment.app.Fragment;

public class BottomNavigationModel {

    int icon;
    int iconSelected;
    boolean select;
    int tab;
    Fragment fragment;
    String text;

    public BottomNavigationModel() {
    }

    public BottomNavigationModel(int icon, int iconSelected, boolean select, int tab, Fragment fragment, String text) {
        this.icon = icon;
        this.iconSelected = iconSelected;
        this.select = select;
        this.tab = tab;
        this.fragment = fragment;
        this.text = text;
    }

    public BottomNavigationModel(int icon, boolean select, int tab, Fragment fragment, String text) {
        this.icon = icon;
        this.iconSelected = 0;
        this.select = select;
        this.tab = tab;
        this.fragment = fragment;
        this.text = text;
    }

    public int getIconSelected() {
        return iconSelected;
    }

    public BottomNavigationModel setIconSelected(int iconSelected) {
        this.iconSelected = iconSelected;
        return this;
    }

    public String getText() {
        return text;
    }

    public BottomNavigationModel setText(String text) {
        this.text = text;
        return this;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public int getIcon() {
        return icon;
    }

    public BottomNavigationModel setIcon(int icon) {
        this.icon = icon;
        return this;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public int getTab() {
        return tab;
    }

    public void setTab(int tab) {
        this.tab = tab;
    }
}
