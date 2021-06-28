package com.ryfa.MVP.widgets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.ryfa.MVP.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by AMz on 11/4/2016.
 */

public class Toast {

    String text;
    Context context;

    public static final int TOAST_TYPE_SUCCESS = 1;
    public static final int TOAST_TYPE_DEFAULT = 2;
    public static final int TOAST_TYPE_ERROR = 3;

    public static Toast makeTEXT(Context context, String text, int toastType) {
        return new Toast(context, text, toastType);
    }

    public static Toast makeTEXT(Context context, String text) {
        return new Toast(context, text, TOAST_TYPE_DEFAULT);
    }

    public Toast(Context context, String text, int type) {
        this.text = text;
        this.context = context;
        try {
            android.widget.Toast toast = new android.widget.Toast(context);
            View toastView = LayoutInflater.from(context).inflate(R.layout.layout_custom_toast, null);
            CardView cv_toast = toastView.findViewById(R.id.cv_toast);
            cv_toast.setCardBackgroundColor(context.getResources().getColor(toastColor(type)));
            TextView tv_toast = toastView.findViewById(R.id.tv_toast);
            tv_toast.setText(text);
            toast.setView(toastView);
            toast.setDuration(android.widget.Toast.LENGTH_LONG);
            toast.show();
        } catch (Exception e) {

        }
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private int toastColor(int type) {
        Map<Integer, Integer> toastColors = new HashMap<>();
        toastColors.put(TOAST_TYPE_SUCCESS, R.color.colorGreen);
        toastColors.put(TOAST_TYPE_DEFAULT, R.color.colorIndigoDark);
        toastColors.put(TOAST_TYPE_ERROR, R.color.colorRed);
        return toastColors.get(type);
    }
}
