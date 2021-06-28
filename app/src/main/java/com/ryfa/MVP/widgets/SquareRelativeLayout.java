package com.ryfa.MVP.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class SquareRelativeLayout extends RelativeLayout {

public SquareRelativeLayout(Context context) {
    super(context);
}

public SquareRelativeLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
}

 @Override
protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

    int width = MeasureSpec.getSize(widthMeasureSpec);
    int height = MeasureSpec.getSize(heightMeasureSpec);
    int mScale = 1;

    if (width < (int)(mScale * height + 0.5)) {
        width = (int)(mScale * height + 0.5);
    } else {
        height = (int)(width / mScale + 0.5);
    }

    super.onMeasure(
            MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY),
            MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY)
    );
}
}