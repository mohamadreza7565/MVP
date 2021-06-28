package com.ryfa.MVP.general;

import android.annotation.SuppressLint;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SetLocale {

    @SuppressLint("DefaultLocale")
    public static String set(String st, String locale) {

        st = st.replace(",", "");
        st = st.replace("٬", "");

        try {
            st = String.format("%" + locale + "d", Long.parseLong(String.valueOf(new BigDecimal(st).toBigIntegerExact())));
        } catch (Exception e) {
            try {
                st = String.format("%" + locale + "d", Long.parseLong(String.valueOf(new BigDecimal(st).toBigIntegerExact())));
            } catch (Exception e1) {

            }
        }

        return st;
    }

    @SuppressLint("DefaultLocale")
    public static String set(int number, String locale) {

        String st = String.valueOf(number);
        st = st.replace(",", "");
        st = st.replace("٬", "");

        try {
            st = String.format("%" + locale + "d", Long.parseLong(String.valueOf(new BigDecimal(st).toBigIntegerExact())));
        } catch (Exception e) {
            try {
                st = String.format("%" + locale + "d", Long.parseLong(String.valueOf(new BigDecimal(st).toBigIntegerExact())));
            } catch (Exception e1) {

            }
        }

        return st;
    }

    public static String replace(String str) {
        Pattern p = Pattern.compile("(\\d+)");
        Matcher m = p.matcher(str);
        String code = "";
        while (m.find()) {
            code = code + m.group(0);
        }
        return code;
    }

    public static final void setLocaleEditText (final EditText editText){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                editText.removeTextChangedListener(this); // Remove listener
                editText.setText(set(editText.getText().toString(),","));                    // Set Text
                editText.setSelection(editText.getText().toString().length());  // Set selection
                editText.addTextChangedListener(this);
            }
        });
    }

}
