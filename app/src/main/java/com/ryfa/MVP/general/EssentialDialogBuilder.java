package com.ryfa.MVP.general;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;


public class EssentialDialogBuilder {

    public static final int DEFAULT_BG = -1;
    public static final int WITHOUT_BG = -2;
    public static final int WITHOUT_INPUT_TYPE = -1;

    public static void setBgDialog(int newBg, View dialogView) {
        switch (newBg) {
            case DEFAULT_BG:
                break;
            case WITHOUT_BG:
                break;
            default:
                try {
                    dialogView.setBackgroundResource(newBg);
                } catch (Exception e) {
                }
        }
    }

    public static void setBgBtn(int newBg, int defaultBg, Button btn) {

        switch (newBg) {
            case DEFAULT_BG:
                btn.setBackgroundResource(defaultBg);
                break;
            case WITHOUT_BG:
                break;
            default:
                try {
                    btn.setBackgroundResource(newBg);
                } catch (Exception e) {
                }
        }
    }

    public static void setBgEditText(int newBg, int defaultBg, EditText editText) {

        switch (newBg) {
            case DEFAULT_BG:
                editText.setBackgroundResource(defaultBg);
                break;
            case WITHOUT_BG:
                break;
            default:
                try {
                    editText.setBackgroundResource(newBg);
                } catch (Exception e) {
                }
        }
    }


    public static void setBgRadioPicker(int newBg, int defaultBg, RadioButton radioButton) {

        switch (newBg) {
            case DEFAULT_BG:
                radioButton.setBackgroundResource(defaultBg);
                break;
            case WITHOUT_BG:
                break;
            default:
                try {
                    radioButton.setBackgroundResource(newBg);
                } catch (Exception e) {
                }
        }
    }

    public static void setBgCheckPicker(int newBg, int defaultBg, CheckBox checkBox) {

        switch (newBg) {
            case DEFAULT_BG:
                checkBox.setBackgroundResource(defaultBg);
                break;
            case WITHOUT_BG:
                break;
            default:
                try {
                    checkBox.setBackgroundResource(newBg);
                } catch (Exception e) {
                }
        }
    }

    public static void setTextColorEditText(Context context, int newColor, int defaultColor, EditText editText) {

        switch (newColor) {
            case DEFAULT_BG:
                editText.setTextColor(context.getResources().getColor(defaultColor));
                break;
            case WITHOUT_BG:
                break;
            default:
                try {
                    editText.setTextColor(context.getResources().getColor(newColor));
                } catch (Exception e) {
                }
        }
    }


    public static void setTextColorBtn(Context context, int newColor, int defaultColor, Button btn) {

        switch (newColor) {
            case DEFAULT_BG:
                btn.setTextColor(context.getResources().getColor(defaultColor));
                break;
            case WITHOUT_BG:
                break;
            default:
                try {
                    btn.setTextColor(context.getResources().getColor(newColor));
                } catch (Exception e) {
                }
        }
    }

    public static void setTextColorTextView(Context context, int newColor, int defaultColor, TextView textView) {

        switch (newColor) {
            case DEFAULT_BG:
                textView.setTextColor(context.getResources().getColor(defaultColor));
                break;
            case WITHOUT_BG:
                break;
            default:
                try {
                    textView.setTextColor(context.getResources().getColor(newColor));
                } catch (Exception e) {
                }
        }
    }

    public static void setTextColorRadioPicker(Context context, int newColor, int defaultColor, RadioButton radioButton) {

        switch (newColor) {
            case DEFAULT_BG:
                radioButton.setTextColor(context.getResources().getColor(defaultColor));
                break;
            case WITHOUT_BG:
                break;
            default:
                try {
                    radioButton.setTextColor(context.getResources().getColor(newColor));
                } catch (Exception e) {
                }
        }
    }

    public static void setTextColorCheckPicker(Context context, int newColor, int defaultColor, CheckBox checkBox) {

        switch (newColor) {
            case DEFAULT_BG:
                checkBox.setTextColor(context.getResources().getColor(defaultColor));
                break;
            case WITHOUT_BG:
                break;
            default:
                try {
                    checkBox.setTextColor(context.getResources().getColor(newColor));
                } catch (Exception e) {
                }
        }
    }
}
