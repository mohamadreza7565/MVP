package com.ryfa.MVP.widgets;

import android.content.Context;
import android.util.AttributeSet;

import com.ryfa.MVP.general.AppConfig;
import com.ryfa.MVP.general.Fonts;


/**
 * Created by AMz on 7/21/2016.
 */
public class TextView extends androidx.appcompat.widget.AppCompatTextView {
    public TextView(Context context) {
        super(context);
        /*if(!isInEditMode())*/this.setTypeface(new Fonts(context,  AppConfig.getLocale(context).equals("fa")
                ? Fonts.DEFAULT_FA_FONT : Fonts.DEFAULT_EN_FONT).getTypeface());

    }

    public TextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        /*if(!isInEditMode())*/this.setTypeface(new Fonts(context,   AppConfig.getLocale(context).equals("fa")
                ? Fonts.DEFAULT_FA_FONT : Fonts.DEFAULT_EN_FONT).getTypeface());
    }

    public TextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        /*if(!isInEditMode())*/this.setTypeface(new Fonts(context,   AppConfig.getLocale(context).equals("fa")
                ? Fonts.DEFAULT_FA_FONT : Fonts.DEFAULT_EN_FONT).getTypeface());
    }

}
