package com.ryfa.MVP.widgets.fix_header_rv;

import android.view.View;

public interface StickyListener {
    Integer getHeaderPositionForItem(Integer itemPosition);

    Integer getHeaderLayout(Integer headerPosition);

    void bindHeaderData(View header, Integer headerPosition);

    Boolean isHeader(Integer itemPosition);
}