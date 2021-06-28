package com.ryfa.MVP.widgets;

import android.content.Context;
import android.util.AttributeSet;

import com.ryfa.MVP.general.AppConfig;
import com.ryfa.MVP.general.Fonts;


/**
 * Created by AMz on 7/21/2016.
 */
public class TextViewBold extends androidx.appcompat.widget.AppCompatTextView {
    public TextViewBold(Context context) {
        super(context);
        /*if(!isInEditMode())*/this.setTypeface(new Fonts(context,   AppConfig.getLocale(context).equals("fa")
                ? Fonts.BOLD_FA_FONT : Fonts.BOLD_EN_FONT).getTypeface());

    }

    public TextViewBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        /*if(!isInEditMode())*/this.setTypeface(new Fonts(context,   AppConfig.getLocale(context).equals("fa")
                ? Fonts.BOLD_FA_FONT : Fonts.BOLD_EN_FONT).getTypeface());
    }

    public TextViewBold(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        /*if(!isInEditMode())*/this.setTypeface(new Fonts(context,  AppConfig.getLocale(context).equals("fa")
                ? Fonts.BOLD_FA_FONT : Fonts.BOLD_EN_FONT).getTypeface());
    }

}
