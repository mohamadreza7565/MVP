package com.ryfa.MVP.general;

import android.content.Context;
import android.graphics.Typeface;

public class Fonts {

    private Typeface typeface;
    public static final String DIRECTORY_FONT = "fonts";
    public static final String DEFAULT_FA_FONT = "iran_sans.ttf";
    public static final String DEFAULT_EN_FONT = "tahoma.ttf";
    public static final String BOLD_FA_FONT = "iran_sans_bold.ttf";
    public static final String BOLD_EN_FONT = "tahoma_bold.ttf";
    public static final String LIGHT_FONT = "iran_sans_light.ttf";
    public static final String MOBILE_LIGHT_FONT = "IRANSansMobile_Light.ttf";
    public static final String MOBILE_MEDIUM_FONT = "IRANSansMobile_Medium.ttf";

    public Fonts(Context context, String font) {
        // TODO Auto-generated constructor stubzz
        typeface = Typeface.createFromAsset(context.getAssets(), DIRECTORY_FONT + "/" + font);
    }

    public Typeface getTypeface() {
        return typeface;
    }
}
