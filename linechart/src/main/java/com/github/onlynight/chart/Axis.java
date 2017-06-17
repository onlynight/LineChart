package com.github.onlynight.chart;

/**
 * Created by lion on 2017/5/4.
 * chart axis config
 */

public class Axis {

    public static final int DEFAULT_COLOR = 0xffbababa;
    public static final String DEFAULT_AXIS_Y_FORMAT = "0.00000000";
    public static final String DEFAULT_AXIS_X_FORMAT = "0";

    public static final int AXIS_SCALE_TEXT_POSITION_CENTER = 0;
    public static final int AXIS_SCALE_TEXT_POSITION_LEFT = 1;
    public static final int AXIS_SCALE_TEXT_POSITION_RIGHT = 2;
    public static final int AXIS_SCALE_TEXT_POSITION_TOP = 3;
    public static final int AXIS_SCALE_TEXT_POSITION_BOTTOM = 4;

    /**
     * axis line color
     */
    private int mAxisLineColor = DEFAULT_COLOR;

    /**
     * axis line width
     */
    private float mAxisLineWidth = 2;

    /**
     * axis has scale text
     */
    private boolean mAxisHasScaleText;

    /**
     * axis scale text color
     */
    private int mAxisScaleTextColor = DEFAULT_COLOR;

    /**
     * axis scale text size
     */
    private float mAxisScaleTextSize = -1;

    /**
     * set the max scale number,
     * if the use the default value -1, it will set the max scale num
     */
    private int mMaxScaleNum = -1;

    /**
     * axis scale text position, center top bottom left right.
     */
    private int mAxisScaleTextPosition = AXIS_SCALE_TEXT_POSITION_CENTER;

    /**
     * axis title text
     */
    private String mTitle;

    /**
     * axis title text size
     */
    private float mTitleTextSize = -1;

    /**
     * is the axis has the title
     */
    private boolean mHasTitle;

    /**
     * title text color
     */
    private int mTitleTextColor = DEFAULT_COLOR;

    /**
     * has the vertical axis line
     */
    private boolean mHasVerticalAxisLine;

    /**
     * vertical axis line color
     */
    private int mVerticalAxisLineColor = DEFAULT_COLOR;

    /**
     * vertical axis line width
     */
    private float mVerticalAxisLineWidth = 2;

    public int getTitleTextColor() {
        return mTitleTextColor;
    }

    public Axis setTitleTextColor(int titleTextColor) {
        this.mTitleTextColor = titleTextColor;
        return this;
    }

    private String mScaleTextFormat = DEFAULT_AXIS_X_FORMAT;

    public String getScaleTextFormat() {
        return mScaleTextFormat;
    }

    public Axis setScaleTextFormat(String scaleTextFormat) {
        this.mScaleTextFormat = scaleTextFormat;
        return this;
    }

    public int getVerticalAxisLineColor() {
        return mVerticalAxisLineColor;
    }

    public Axis setVerticalAxisLineColor(int lineColor) {
        this.mVerticalAxisLineColor = lineColor;
        return this;
    }

    public float getVerticalAxisLineWidth() {
        return mVerticalAxisLineWidth;
    }

    public Axis setVerticalAxisLineWidth(float mVerticalAxisLineWidth) {
        this.mVerticalAxisLineWidth = mVerticalAxisLineWidth;
        return this;
    }

    public boolean isHasVerticalAxisLine() {
        return mHasVerticalAxisLine;
    }

    public Axis setHasVerticalAxisLine(boolean hasLine) {
        this.mHasVerticalAxisLine = hasLine;
        return this;
    }

    public boolean isHasTitle() {
        return mHasTitle;
    }

    public Axis setHasTitle(boolean hasTitle) {
        this.mHasTitle = hasTitle;
        return this;
    }

    public String getTitle() {
        return mTitle;
    }

    public Axis setTitle(String title) {
        this.mTitle = title;
        return this;
    }

    public int getAxisLineColor() {
        return mAxisLineColor;
    }

    public Axis setAxisLineColor(int mAxisLineColor) {
        this.mAxisLineColor = mAxisLineColor;
        return this;
    }

    public int getAxisScaleTextColor() {
        return mAxisScaleTextColor;
    }

    public Axis setAxisScaleTextColor(int mAxisScaleTextColor) {
        this.mAxisScaleTextColor = mAxisScaleTextColor;
        return this;
    }

    public float getAxisScaleTextSize() {
        return mAxisScaleTextSize;
    }

    public Axis setAxisScaleTextSize(float mAxisScaleTextSize) {
        this.mAxisScaleTextSize = mAxisScaleTextSize;
        return this;
    }

    public int getmAxisScaleTextPosition() {
        return mAxisScaleTextPosition;
    }

    public Axis setAxisScaleTextPosition(int mAxisScaleTextPosition) {
        this.mAxisScaleTextPosition = mAxisScaleTextPosition;
        return this;
    }

    public float getAxisLineWidth() {
        return mAxisLineWidth;
    }

    public Axis setAxisLineWidth(float mAxisLineWidth) {
        this.mAxisLineWidth = mAxisLineWidth;
        return this;
    }

    public int getMaxScaleNum() {
        return mMaxScaleNum;
    }

    public Axis setMaxScaleNum(int maxScaleNum) {
        this.mMaxScaleNum = maxScaleNum;
        return this;
    }

    public float getTitleTextSize() {
        return mTitleTextSize;
    }

    public Axis setTitleTextSize(float mTitleTextSize) {
        this.mTitleTextSize = mTitleTextSize;
        return this;
    }

    public boolean isAxisHasScaleText() {
        return mAxisHasScaleText;
    }

    public Axis setAxisHasScaleText(boolean mAxisHasScaleText) {
        this.mAxisHasScaleText = mAxisHasScaleText;
        return this;
    }
}
