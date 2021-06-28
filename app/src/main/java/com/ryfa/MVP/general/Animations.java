package com.ryfa.MVP.general;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.view.animation.AlphaAnimation;

public class Animations {

    public static Animations newInstance() {
        return new Animations();
    }

    public static AlphaAnimation alphaAnimation(float fromAlpha, float toAlpha, int duration, int startOffset, boolean fillAfter) {

        AlphaAnimation alphaAnimation = new AlphaAnimation(fromAlpha, toAlpha);
        alphaAnimation.setDuration(duration);
        alphaAnimation.setStartOffset(startOffset);
        alphaAnimation.setFillAfter(fillAfter);

        return alphaAnimation;
    }

    @SuppressLint("ObjectAnimatorBinding")
    public static void VibrateAnimation(Object target){
        ObjectAnimator
                .ofFloat(target, "translationX", 0, 25, -25, 25, -25, 15, -15, 6, -6, 0)
                .setDuration(1000)
                .start();
    }

}
