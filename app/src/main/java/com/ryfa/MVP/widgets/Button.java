package com.ryfa.MVP.widgets;

import android.content.Context;
import android.util.AttributeSet;

import com.ryfa.MVP.general.AppConfig;
import com.ryfa.MVP.general.Fonts;


/**
 * Created by AMz on 7/21/2016.
 */
public class Button extends androidx.appcompat.widget.AppCompatButton {
    public Button(Context context) {
        super(context);
        setAllCaps(false);
        /*if(!isInEditMode())*/
        this.setTypeface(new Fonts(context, AppConfig.getLocale(context).equals("fa")
                ? Fonts.DEFAULT_FA_FONT : Fonts.DEFAULT_EN_FONT).getTypeface());
    }

    public Button(Context context, AttributeSet attrs) {
        super(context, attrs);
        setAllCaps(false);
        /*if(!isInEditMode())*/
        this.setTypeface(new Fonts(context, AppConfig.getLocale(context).equals("fa")
                ? Fonts.DEFAULT_FA_FONT : Fonts.DEFAULT_EN_FONT).getTypeface());
    }

    public Button(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setAllCaps(false);
        /*if(!isInEditMode())*/
        this.setTypeface(new Fonts(context, AppConfig.getLocale(context).equals("fa")
                ? Fonts.DEFAULT_FA_FONT : Fonts.DEFAULT_EN_FONT).getTypeface());
    }

}
