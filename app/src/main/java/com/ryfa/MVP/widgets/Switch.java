package com.ryfa.MVP.widgets;

import android.content.Context;
import android.util.AttributeSet;

import com.ryfa.MVP.general.AppConfig;
import com.ryfa.MVP.general.Fonts;


/**
 * Created by AMz on 7/21/2016.
 */
public class Switch extends androidx.appcompat.widget.SwitchCompat {
    public Switch(Context context) {
        super(context);
        this.setTypeface(new Fonts(context,  AppConfig.getLocale(context).equals("fa")
                ? Fonts.BOLD_FA_FONT : Fonts.BOLD_EN_FONT).getTypeface());
    }

    public Switch(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(new Fonts(context,  AppConfig.getLocale(context).equals("fa")
                ? Fonts.BOLD_FA_FONT : Fonts.BOLD_EN_FONT).getTypeface());
    }

    public Switch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setTypeface(new Fonts(context,  AppConfig.getLocale(context).equals("fa")
                ? Fonts.BOLD_FA_FONT : Fonts.BOLD_EN_FONT).getTypeface());
    }

}
