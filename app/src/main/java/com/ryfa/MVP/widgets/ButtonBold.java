package com.ryfa.MVP.widgets;

import android.content.Context;
import android.util.AttributeSet;

import com.ryfa.MVP.general.AppConfig;
import com.ryfa.MVP.general.Fonts;


/**
 * Created by AMz on 7/21/2016.
 */
public class ButtonBold extends androidx.appcompat.widget.AppCompatButton {
    public ButtonBold(Context context) {
        super(context);
        setAllCaps(false);
        this.setTypeface(new Fonts(context,   AppConfig.getLocale(context).equals("fa")
                ? Fonts.BOLD_FA_FONT : Fonts.BOLD_EN_FONT).getTypeface());
    }

    public ButtonBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        setAllCaps(false);
        this.setTypeface(new Fonts(context,   AppConfig.getLocale(context).equals("fa")
                ? Fonts.BOLD_FA_FONT : Fonts.BOLD_EN_FONT).getTypeface());
    }

    public ButtonBold(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setAllCaps(false);
        this.setTypeface(new Fonts(context,   AppConfig.getLocale(context).equals("fa")
                ? Fonts.BOLD_FA_FONT : Fonts.BOLD_EN_FONT).getTypeface());
    }

}
