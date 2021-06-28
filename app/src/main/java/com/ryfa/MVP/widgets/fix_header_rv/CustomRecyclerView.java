package com.ryfa.MVP.widgets.fix_header_rv;


import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;


public class CustomRecyclerView extends RecyclerView {
    public CustomRecyclerView(@NonNull Context context) {
        super(context);
    }

    public CustomRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setAdapter(@Nullable Adapter adapter) {
        super.setAdapter(adapter);
        if (getAdapter() instanceof StickyListener) {
            setStickyItemDecoration();
        }
    }

    private void setStickyItemDecoration() {
        HeaderItemDecoration itemDecoration = new HeaderItemDecoration((StickyListener) getAdapter());
        this.addItemDecoration(itemDecoration);
    }

}