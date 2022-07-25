package com.mpandroidchart.sample2;

import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

public class MyMarkerView extends MarkerView {
    private TextView mContentTv;

    public MyMarkerView(Context context, int layoutResource) {
        super(context, layoutResource);
        mContentTv = (TextView) findViewById(R.id.tv_content_marker_view);
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        mContentTv.setText("" + e.getY());
    }

    @Override
    public MPPointF getOffset() {
        MPPointF point = super.getOffset();
        point.x = -getWidth()/2;
        point.y = -getHeight();
        //super.getOffset();
        return point;
    }

//    @Override
//    public int getXOffset(float xpos) {
//        return -(getWidth() / 2);
//    }
//
//    @Override
//    public int getYOffset(float ypos) {
//        return -getHeight();
//    }
}