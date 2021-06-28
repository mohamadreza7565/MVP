package com.ryfa.MVP.widgets;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.EditText;

import com.ryfa.MVP.general.AppConfig;
import com.ryfa.MVP.general.SharedPrefHandler;

import java.util.ArrayList;

public class ChangeTheme {

    Context context;
    ViewGroup view;
    public static final String KEY_THEME = "KEY_THEME";
    public static final int DARK_THEME = 1;
    public static final int LIGHT_THEME = 2;

    public static void setView(Context context, ViewGroup view) {
        if (SharedPrefHandler.getIntegerPreference(context, KEY_THEME, DARK_THEME) == DARK_THEME)
            new ChangeTheme(context, view).setDarkTheme();
        else
            new ChangeTheme(context, view).setLightTheme();
    }

    protected ChangeTheme(Context context, ViewGroup view) {
        this.context = context;
        this.view = view;
    }

    protected void setDarkTheme() {
        setDarkEditText();
    }

    protected void setLightTheme() {
        setLightEditText();
    }

    protected void setLightEditText() {
        ArrayList<EditText> list = AppConfig.getAllEditText(view, new ArrayList<EditText>());
        for (int i = 0; i < list.size(); i++) {

        }
    }

    protected void setDarkEditText() {
        ArrayList<EditText> list = AppConfig.getAllEditText(view, new ArrayList<EditText>());
        for (int i = 0; i < list.size(); i++) {

        }
    }

//        if (ImageViewCompat.getImageTintList(imv_adType).getDefaultColor() ==
//    getContext().getResources().getColor(R.color.white)) {
//        Toast.makeTEXT(getContext(), "سفید است");
//    } else {
//        Toast.makeTEXT(getContext(), "سفید نیست");
//    }


}
