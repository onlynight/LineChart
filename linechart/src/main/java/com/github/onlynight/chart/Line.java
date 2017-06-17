package com.github.onlynight.chart;

import java.util.List;

/**
 * Created by lion on 2017/5/4.
 * <p>
 * line chart line config and data
 */

public class Line {

    /**
     * line color
     */
    private int mLineColor = 0xfafafa;

    /**
     * has the cube link
     */
    private boolean mIsCube;

    /**
     * line width
     */
    private float mLineWidth = 2;

    /**
     * line data
     */
    private List<ChartPoint> mData;

    public Line() {
    }

    public Line(List<ChartPoint> mData) {
        this.mData = mData;
    }

    public List<ChartPoint> getData() {
        return mData;
    }

    public Line setData(List<ChartPoint> data) {
        this.mData = data;
        return this;
    }

    public int getLineColor() {
        return mLineColor;
    }

    public Line setLineColor(int mLineColor) {
        this.mLineColor = mLineColor;
        return this;
    }

    public boolean isCube() {
        return mIsCube;
    }

    public Line setCube(boolean mIsCube) {
        this.mIsCube = mIsCube;
        return this;
    }

    public float getLineWidth() {
        return mLineWidth;
    }

    public Line setLineWidth(float mLineWidth) {
        this.mLineWidth = mLineWidth;
        return this;
    }
}
