package com.github.onlynight.chart;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.github.onlynight.linechart.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by lion on 2017/5/4.
 */

public class LineChartView extends View {

    private static final int DEFAULT_COLOR = 0xffbababa;
    private static final String DEFAULT_AXIS_Y_FORMAT = "0.00000000";
    private static final String DEFAULT_AXIS_X_FORMAT = "0";
    private static final int DEFAULT_MARGIN = 10;

    private static final float DEFAULT_MAX_SCALE_HEIGHT = 4.0f;

    private static final int DEFAULT_LINE_CONTENT_MARGIN = 50;

    /**
     * axis line color
     */
    private int mAxisXLineColor = DEFAULT_COLOR;
    private int mAxisYLineColor = DEFAULT_COLOR;

    /**
     * axis line width
     */
    private int mAxisXLineWidth = 2;
    private int mAxisYLineWidth = 2;

    /**
     * axis scale text color
     */
    private int mAxisXScaleTextColor = DEFAULT_COLOR;
    private int mAxisYScaleTextColor = DEFAULT_COLOR;

    /**
     * axis scale text size
     */
    private float mAxisXScaleTextSize = -1;
    private float mAxisYScaleTextSize = -1;

    /**
     * set the max scale number,
     * if the use the default value -1, it will set the max scale num
     */
    private int mAxisXMaxScaleNum = 12;
    private int mAxisYMaxScaleNum = -1;

    /**
     * axis scale text position, center top bottom left right.
     */
    private int mAxisXScaleTextPosition = Axis.AXIS_SCALE_TEXT_POSITION_CENTER;
    private int mAxisYScaleTextPosition = Axis.AXIS_SCALE_TEXT_POSITION_CENTER;

    /**
     * axis title text
     */
    private String mAxisXTitle;
    private String mAxisYTitle;

    /**
     * axis title text size
     */
    private float mAxisXTitleTextSize = -1;
    private float mAxisYTitleTextSize = -1;

    private int mAxisXTitleTextColor = DEFAULT_COLOR;
    private int mAxisYTitleTextColor = DEFAULT_COLOR;

    /**
     * is the axis has the title
     */
    private boolean mAxisXHasTitle;
    private boolean mAxisYHasTitle;

    /**
     * is the axis has scale text
     */
    private boolean mAxisXHasScaleText;
    private boolean mAxisYHasScaleText;

    /**
     * has the vertical axis line
     */
    private boolean mAxisXHasVerticalLine;
    private boolean mAxisYHasVerticalLine;

    /**
     * vertical axis line color
     */
    private int mAxisXVerticalLineColor = DEFAULT_COLOR;
    private int mAxisYVerticalLineColor = DEFAULT_COLOR;

    /**
     * vertical axis line width
     */
    private float mAxisXVerticalLineWidth = 2;
    private float mAxisYVerticalLineWidth = 2;

    private float mLineContentMargin = DEFAULT_LINE_CONTENT_MARGIN;

    /**
     * chart content margin
     */
    private float mContentMargin = 0;

    /**
     * axis settings
     */
    private Axis mAxisY;
    private Axis mAxisX;

    private List<ChartPoint> mAxisXPoints;
    private List<ChartPoint> mAxisYPoints;

    private float mAxisYScaleLeftMargin = 0;
    private float mAxisXScaleBottomMargin = 0;

    private ExtremeValue mExtremeValue = null;

    /**
     * text paint
     */
    private Paint mPaint;
    private Paint mLinePaint;

    /**
     * mLines
     */
    private Line mLine;

    private Path mLinePath;

    public LineChartView(Context context) {
        super(context);
        initView(null);
    }

    public LineChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs,
                R.styleable.LineChartView);
        initView(array);
    }

    public LineChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.LineChartView,
                defStyleAttr, 0);
        initView(array);
    }

    @RequiresApi(21)
    public LineChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.LineChartView,
                defStyleAttr, defStyleRes);
        initView(array);
    }

    private void initView(TypedArray array) {
        if (array != null) {
            mAxisXLineColor = array.getColor(
                    R.styleable.LineChartView_axisXLineColor, DEFAULT_COLOR);
            mAxisXLineWidth = array.getInteger(
                    R.styleable.LineChartView_axisXLineWidth,
                    DisplayUtil.dip2px(getContext(), 1));
            mAxisXScaleTextColor = array.getColor(
                    R.styleable.LineChartView_axisXScaleTextColor, DEFAULT_COLOR);
            mAxisXScaleTextSize = array.getDimension(
                    R.styleable.LineChartView_axisXScaleTextSize,
                    DisplayUtil.sp2px(getContext(), 10));
            mAxisXMaxScaleNum = array.getInteger(
                    R.styleable.LineChartView_axisXMaxScaleNum, 12);
            mAxisXScaleTextPosition = array.getInt(
                    R.styleable.LineChartView_axisXScalePosition, 0);
            mAxisXTitle = array.getString(R.styleable.LineChartView_axisXTitle);
            mAxisXTitleTextSize = array.getDimension(
                    R.styleable.LineChartView_axisXTitleTextSize,
                    DisplayUtil.sp2px(getContext(), 10));
            mAxisXHasTitle = array.getBoolean(
                    R.styleable.LineChartView_axisXHasTitle,
                    false);
            mAxisXTitleTextColor = array.getColor(
                    R.styleable.LineChartView_axisXTitleTextColor,
                    DEFAULT_COLOR);
            mAxisXHasScaleText = array.getBoolean(
                    R.styleable.LineChartView_axisXHasScaleText,
                    false);
            mAxisXHasVerticalLine = array.getBoolean(
                    R.styleable.LineChartView_axisXHasVerticalLine,
                    false);
            mAxisXVerticalLineColor = array.getColor(
                    R.styleable.LineChartView_axisXVerticalLineColor,
                    DEFAULT_COLOR);
            mAxisXVerticalLineWidth = array.getDimension(
                    R.styleable.LineChartView_axisXVerticalLineWidth, 2);

            mAxisYLineColor = array.getColor(
                    R.styleable.LineChartView_axisYLineColor, DEFAULT_COLOR);
            mAxisYLineWidth = array.getInteger(
                    R.styleable.LineChartView_axisYLineWidth,
                    DisplayUtil.dip2px(getContext(), 1));
            mAxisYScaleTextColor = array.getColor(
                    R.styleable.LineChartView_axisYScaleTextColor, DEFAULT_COLOR);
            mAxisYScaleTextSize = array.getDimension(
                    R.styleable.LineChartView_axisYScaleTextSize,
                    DisplayUtil.sp2px(getContext(), 10));
            mAxisYMaxScaleNum = array.getInteger(
                    R.styleable.LineChartView_axisYMaxScaleNum, -1);
            mAxisYScaleTextPosition = array.getInt(
                    R.styleable.LineChartView_axisYScalePosition, 0);
            mAxisYTitle = array.getString(R.styleable.LineChartView_axisYTitle);
            mAxisYTitleTextSize = array.getDimension(
                    R.styleable.LineChartView_axisYTitleTextSize,
                    DisplayUtil.sp2px(getContext(), 10));
            mAxisYHasTitle = array.getBoolean(
                    R.styleable.LineChartView_axisYHasTitle,
                    false);
            mAxisYTitleTextColor = array.getColor(
                    R.styleable.LineChartView_axisYTitleTextColor,
                    DEFAULT_COLOR);
            mAxisYHasScaleText = array.getBoolean(
                    R.styleable.LineChartView_axisYHasScaleText,
                    false);
            mAxisYHasVerticalLine = array.getBoolean(
                    R.styleable.LineChartView_axisYHasVerticalLine,
                    false);
            mAxisYVerticalLineColor = array.getColor(
                    R.styleable.LineChartView_axisYVerticalLineColor,
                    DEFAULT_COLOR);
            mAxisYVerticalLineWidth = array.getDimension(
                    R.styleable.LineChartView_axisYVerticalLineWidth, 2);

            mContentMargin = array.getDimension(
                    R.styleable.LineChartView_contentMargin, 0);

            mLineContentMargin = array.getDimension(
                    R.styleable.LineChartView_lineContentMargin, DEFAULT_LINE_CONTENT_MARGIN);

            array.recycle();
        } else {
            mAxisXLineColor = DEFAULT_COLOR;
            mAxisXLineWidth = DisplayUtil.dip2px(getContext(), 1);
            mAxisXScaleTextColor = DEFAULT_COLOR;
            mAxisXScaleTextSize = DisplayUtil.sp2px(getContext(), 10);
            mAxisXMaxScaleNum = 12;
            mAxisXScaleTextPosition = 0;
            mAxisXTitleTextSize = DisplayUtil.sp2px(getContext(), 10);
            mAxisXHasTitle = false;
            mAxisXHasScaleText = false;
            mAxisXHasVerticalLine = false;
            mAxisXVerticalLineColor = DEFAULT_COLOR;
            mAxisXVerticalLineWidth = 2;

            mAxisYLineColor = DEFAULT_COLOR;
            mAxisYLineWidth = DisplayUtil.dip2px(getContext(), 1);
            mAxisYScaleTextColor = DEFAULT_COLOR;
            mAxisYScaleTextSize = DisplayUtil.sp2px(getContext(), 10);
            mAxisYMaxScaleNum = -1;
            mAxisYScaleTextPosition = 0;
            mAxisYTitleTextSize = DisplayUtil.sp2px(getContext(), 10);
            mAxisYHasTitle = false;
            mAxisYHasScaleText = false;
            mAxisYHasVerticalLine = false;
            mAxisYVerticalLineColor = DEFAULT_COLOR;
            mAxisYVerticalLineWidth = 2;

            mContentMargin = 0;
            mLineContentMargin = DEFAULT_LINE_CONTENT_MARGIN;
        }

        initProperty();
        initPaint();
//        initFakeData();
    }

    private void initProperty() {
        mAxisXPoints = new ArrayList<>();
        mAxisYPoints = new ArrayList<>();
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(mAxisXScaleTextColor);
        mPaint.setTextSize(mAxisXScaleTextSize);

        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePath = new Path();
    }

    private void initFakeData() {
        setLine(createFakeLine());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        innerDraw(canvas);
    }

    private void innerDraw(Canvas canvas) {
        mAxisYScaleLeftMargin = 0;
        mAxisXScaleBottomMargin = 0;
        mAxisXPoints.clear();
        mAxisYPoints.clear();
        mAxisYScaleLeftMargin = mContentMargin;
        drawAxisTitle(canvas);
        drawAxisScaleText(canvas);
        drawAxisScaleVerticalLine(canvas);
        drawAxis(canvas);
        drawLineChart(canvas);
    }

    private void drawLineChart(Canvas canvas) {
        if (mLine != null) {
            float totalHeight = getHeight() - mAxisXScaleBottomMargin - mContentMargin;
            if (mLine.getData() != null && mExtremeValue != null) {
                mLinePath.reset();
                List<ChartPoint> data = mLine.getData();
                float blank = (getWidth() - mAxisYScaleLeftMargin -
                        mLineContentMargin * 3) / data.size();
                for (int i = 0; i < data.size() - 1; i++) {
                    mLinePath.moveTo(mAxisYScaleLeftMargin + mLineContentMargin + blank * (i + 1),
                            (1 - (data.get(i).getY() - mExtremeValue.min) /
                                    (mExtremeValue.max - mExtremeValue.min))
                                    * totalHeight + mContentMargin);
                    mLinePath.lineTo(mAxisYScaleLeftMargin + mLineContentMargin + blank * (i + 2),
                            (1 - (data.get(i + 1).getY() - mExtremeValue.min) /
                                    (mExtremeValue.max - mExtremeValue.min))
                                    * totalHeight + mContentMargin);
                }

                mLinePaint.setColor(mLine.getLineColor());
                mLinePaint.setStrokeWidth(mLine.getLineWidth());
                mLinePaint.setStyle(Paint.Style.STROKE);
                canvas.drawPath(mLinePath, mLinePaint);
            }
        }
    }

    private void drawAxisTitle(Canvas canvas) {

        drawAxisYTitle(canvas);
        drawAxisXTitle(canvas);

    }

    private void drawAxisYTitle(Canvas canvas) {
        if (mAxisY != null) {
            if (mAxisY.isHasTitle() && !TextUtils.isEmpty(mAxisY.getTitle())) {
                drawAxisYTitle(canvas, mAxisY.getTitle(), mAxisY.getTitleTextColor(),
                        mAxisY.getTitleTextSize());
                mAxisYScaleLeftMargin += getFontHeight(mPaint) + DEFAULT_MARGIN * 2;
            }
        } else {
            if (mAxisYHasTitle && !TextUtils.isEmpty(mAxisYTitle)) {
                drawAxisYTitle(canvas, mAxisYTitle, mAxisYTitleTextColor,
                        mAxisYTitleTextSize);
                mAxisYScaleLeftMargin += getFontHeight(mPaint) + DEFAULT_MARGIN * 2;
            }
        }
    }

    private void drawAxisXTitle(Canvas canvas) {
        if (mAxisX != null) {
            if (mAxisX.isHasTitle() && !TextUtils.isEmpty(mAxisX.getTitle())) {
                drawAxisXTitle(canvas, mAxisX.getTitle(), mAxisX.getTitleTextColor(),
                        mAxisX.getTitleTextSize());
                mAxisXScaleBottomMargin += getFontHeight(mPaint) + DEFAULT_MARGIN * 2;
            }
        } else {
            if (mAxisXHasTitle && !TextUtils.isEmpty(mAxisXTitle)) {
                drawAxisXTitle(canvas, mAxisXTitle, mAxisXTitleTextColor,
                        mAxisXTitleTextSize);
                mAxisXScaleBottomMargin += getFontHeight(mPaint) + DEFAULT_MARGIN * 2;
            }
        }
    }

    private void drawAxisYTitle(Canvas canvas, String title, int color, float textSize) {
        mPaint.setColor(color);
        mPaint.setTextSize(textSize);
        float textLength = mPaint.measureText(title);
        int y = (int) ((getHeight() - textLength) / 2);
        drawText(canvas, title, DEFAULT_MARGIN, y, 90, mPaint);
    }

    private void drawAxisXTitle(Canvas canvas, String title, int color, float textSize) {
        mPaint.setColor(color);
        mPaint.setTextSize(textSize);
        float textLength = mPaint.measureText(title);
        int x = (int) ((getWidth() - textLength) / 2);
        drawText(canvas, title, x, getHeight() - DEFAULT_MARGIN, 0, mPaint);
    }

    private void drawAxisScaleText(Canvas canvas) {
        generateAxisScale();
        drawAxisXScaleText(canvas);
        drawAxisYScaleText(canvas);
    }

    private void drawAxis(Canvas canvas) {
//        drawAxisY(canvas);
        drawAxisX(canvas);
    }

    private void drawAxisY(Canvas canvas) {
        if (mAxisX != null) {
            drawAxisY(canvas, mAxisX.getAxisLineWidth(), mAxisX.getAxisLineColor());
        } else {
            drawAxisY(canvas, mAxisXLineWidth, mAxisXLineColor);
        }
    }

    private void drawAxisY(Canvas canvas, float width, int color) {
        mPaint.setColor(color);
        mPaint.setStrokeWidth(width);
        canvas.drawLine(mAxisYScaleLeftMargin, 0 + mContentMargin, mAxisYScaleLeftMargin,
                getHeight() - mAxisXScaleBottomMargin, mPaint);
    }

    private void drawAxisX(Canvas canvas) {
        if (mAxisY != null) {
            drawAxisX(canvas, mAxisY.getAxisLineWidth(), mAxisY.getAxisLineColor());
        } else {
            drawAxisX(canvas, mAxisYLineWidth, mAxisYLineColor);
        }
    }

    private void drawAxisX(Canvas canvas, float width, int color) {
        mPaint.setColor(color);
        mPaint.setStrokeWidth(width);
        canvas.drawLine(mAxisYScaleLeftMargin, getHeight() - mAxisXScaleBottomMargin,
                getWidth() - mContentMargin,
                getHeight() - mAxisXScaleBottomMargin, mPaint);
    }

    private void drawScaleYVerticalLine(Canvas canvas) {
        if (mAxisYPoints != null && mAxisYPoints.size() > 0) {
            if (mAxisY != null) {
                if (mAxisY.isHasVerticalAxisLine()) {
                    realDrawYScaleVerticalLine(canvas, mAxisY.getVerticalAxisLineWidth(),
                            mAxisY.getVerticalAxisLineColor());
                }
            } else {
                if (mAxisYHasVerticalLine) {
                    realDrawYScaleVerticalLine(canvas,
                            mAxisYVerticalLineWidth, mAxisYVerticalLineColor);
                }
            }
        }
    }

    private void realDrawYScaleVerticalLine(Canvas canvas, float width, int color) {
        mPaint.setStrokeWidth(width);
        mPaint.setColor(color);
//        for (ChartPoint point : mAxisYPoints) {
//            canvas.drawLine(mAxisYScaleLeftMargin, point.getY(), getWidth() -
//                    mContentMargin, point.getY(), mPaint);
//        }

        for (int i = 0; i < mAxisYPoints.size(); i++) {
            if (i == 0) {
                mPaint.setColor(mAxisXLineColor);
            } else {
                mPaint.setColor(color);
            }
            ChartPoint point = mAxisYPoints.get(i);
            canvas.drawLine(mAxisYScaleLeftMargin, point.getY(), getWidth() -
                    mContentMargin, point.getY(), mPaint);
        }
    }

    private void drawScaleXVerticalLine(Canvas canvas) {
        if (mAxisXPoints != null && mAxisXPoints.size() > 0) {
            if (mAxisX != null) {
                if (mAxisX.isHasVerticalAxisLine()) {
                    realDrawXScaleVerticalLine(canvas, mAxisX.getVerticalAxisLineWidth(),
                            mAxisX.getVerticalAxisLineColor());
                }
            } else {
                if (mAxisXHasVerticalLine) {
                    realDrawXScaleVerticalLine(canvas,
                            mAxisXVerticalLineWidth, mAxisXVerticalLineColor);
                }
            }
        }
    }

    private void realDrawXScaleVerticalLine(Canvas canvas, float width, int color) {
        mPaint.setStrokeWidth(width);
        mPaint.setColor(color);
        for (ChartPoint point : mAxisXPoints) {
            canvas.drawLine(point.getX(), mAxisXScaleBottomMargin, point.getX(),
                    getHeight() - mAxisXScaleBottomMargin, mPaint);
        }
    }

    private void drawAxisScaleVerticalLine(Canvas canvas) {
        drawScaleXVerticalLine(canvas);
        drawScaleYVerticalLine(canvas);
    }

    private void drawAxisYScaleText(Canvas canvas) {
        if (mAxisY != null) {
            if (mAxisY.isAxisHasScaleText()) {
                drawAxisYScaleText(canvas, mAxisY.getAxisScaleTextSize(),
                        mAxisY.getAxisScaleTextColor(), mAxisY.getmAxisScaleTextPosition());
            }
        } else {
            if (mAxisYHasScaleText) {
                drawAxisYScaleText(canvas, mAxisYScaleTextSize,
                        mAxisYScaleTextColor, mAxisYScaleTextPosition);
            }
        }
    }

    private void drawAxisXScaleText(Canvas canvas) {
        if (mAxisX != null) {
            if (mAxisX.isAxisHasScaleText()) {
                drawAxisXScaleText(canvas, mAxisX.getAxisScaleTextSize(),
                        mAxisX.getAxisScaleTextColor(), mAxisX.getmAxisScaleTextPosition());
            }
        } else {
            if (mAxisXHasScaleText) {
                drawAxisXScaleText(canvas, mAxisXScaleTextSize,
                        mAxisXScaleTextColor, mAxisXScaleTextPosition);
            }
        }
    }

    private void drawAxisYScaleText(Canvas canvas, float textSize, int textColor, int textPosition) {
        if (mAxisYPoints != null && mAxisYPoints.size() > 0) {
            mPaint.setTextSize(textSize);
            mPaint.setColor(textColor);

            int fontHeight = (int) getFontHeight(mPaint);
            int y = 0;
            for (ChartPoint point : mAxisYPoints) {
                switch (textPosition) {
                    case Axis.AXIS_SCALE_TEXT_POSITION_CENTER:
                        y = (int) (point.getY() + fontHeight / 2);
                        break;
                    case Axis.AXIS_SCALE_TEXT_POSITION_TOP:
                        y = (int) point.getY();
                        break;
                    case Axis.AXIS_SCALE_TEXT_POSITION_BOTTOM:
                        y = (int) (point.getY() + fontHeight);
                        break;
                }

                drawText(canvas, point.getValue(),
                        (int) point.getX(), y, 0, mPaint);
            }
        }
    }

    private void drawAxisXScaleText(Canvas canvas, float textSize, int textColor, int textPosition) {
        if (mAxisXPoints != null && mAxisXPoints.size() > 0) {
            mPaint.setTextSize(textSize);
            mPaint.setColor(textColor);

            float width = 0;
            int x = 0;

            for (ChartPoint point : mAxisXPoints) {
                width = mPaint.measureText(point.getValue());

                switch (textPosition) {
                    case Axis.AXIS_SCALE_TEXT_POSITION_CENTER:
                        x = (int) (point.getX() - width / 2);
                        break;
                    case Axis.AXIS_SCALE_TEXT_POSITION_LEFT:
                        x = (int) (point.getX() - width);
                        break;
                    case Axis.AXIS_SCALE_TEXT_POSITION_RIGHT:
                        x = (int) point.getX();
                        break;
                }

                drawText(canvas, point.getValue(),
                        x, (int) (point.getY() + DEFAULT_MARGIN), 0, mPaint);
            }
        }
    }

    private void generateAxisScale() {
        calScaleBottomMargin();

        if (mAxisY != null) {
            float scaleMaxWidth = generateAxisYScale(
                    mAxisY.getAxisScaleTextSize(), mAxisY.getScaleTextFormat(), mPaint);
            if (mAxisYHasScaleText) {
                mAxisYScaleLeftMargin += scaleMaxWidth + DEFAULT_MARGIN;
            }
        } else {
            float scaleMaxWidth = generateAxisYScale(
                    mAxisYScaleTextSize, DEFAULT_AXIS_Y_FORMAT, mPaint);
            if (mAxisYHasScaleText) {
                mAxisYScaleLeftMargin += scaleMaxWidth + DEFAULT_MARGIN;
            }
        }

        if (mAxisX != null) {
            generateAxisXScale(mAxisX.getAxisScaleTextSize(), mAxisX.getMaxScaleNum() != -1
                            ? mAxisX.getMaxScaleNum() : mAxisXMaxScaleNum,
                    mAxisX.getScaleTextFormat(), mPaint);
        } else {
            generateAxisXScale(mAxisXScaleTextSize, mAxisXMaxScaleNum,
                    DEFAULT_AXIS_X_FORMAT, mPaint);
        }
    }

    private void calScaleBottomMargin() {
        if (mAxisX != null) {
            if (mAxisX.isAxisHasScaleText()) {
                mPaint.setTextSize(mAxisX.getAxisScaleTextSize());
                mAxisXScaleBottomMargin += getFontHeight(mPaint) + DEFAULT_MARGIN;
            }
        } else {
            if (mAxisXHasScaleText) {
                mPaint.setTextSize(mAxisXScaleTextSize);
                mAxisXScaleBottomMargin += getFontHeight(mPaint) + DEFAULT_MARGIN;
            }
        }
    }

    private float generateAxisYScale(float textSize, String format, Paint paint) {
        mExtremeValue = getExtremeYValue();
        if (mExtremeValue != null) {
            paint.setTextSize(textSize);
            int fontHeight = (int) getFontHeight(paint);
            int maxScaleY;
            float totalHeight = getHeight() - mAxisXScaleBottomMargin - mContentMargin;
            if (mAxisYMaxScaleNum != -1) {
                maxScaleY = mAxisYMaxScaleNum;
            } else {
                maxScaleY = (int) (totalHeight /
                        ((int) (fontHeight * DEFAULT_MAX_SCALE_HEIGHT))) + 1;
            }
            float maxAxisYScaleWidth = 0;
            int height = (int) (totalHeight / maxScaleY);
            float range = mExtremeValue.max - mExtremeValue.min;
            for (int i = 0; i < maxScaleY; i++) {
                ChartPoint point = new ChartPoint(mAxisYScaleLeftMargin,
                        height * i + mContentMargin);
                point.setValue(getFormatString(format,
                        range - range / maxScaleY * i + mExtremeValue.min));
                float width = mPaint.measureText(point.getValue());
                if (width > maxAxisYScaleWidth) {
                    maxAxisYScaleWidth = width;
                }
                mAxisYPoints.add(point);
            }

            ChartPoint point = new ChartPoint(mAxisYScaleLeftMargin,
                    getHeight() - mAxisXScaleBottomMargin);
            point.setValue(getFormatString(format, mExtremeValue.min));
            mAxisYPoints.add(point);

            return maxAxisYScaleWidth;
        }

        return 0;
    }

    private void generateAxisXScale(float textSize, int maxScaleX, String format, Paint paint) {
        paint.setTextSize(textSize);
        int fontHeight = (int) getFontHeight(paint);
        int blank = 0;
        if (mLine != null && mLine.getData() != null) {
            try {
                blank = mLine.getData().size() / maxScaleX;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        int eachWidth = (int) ((getWidth() - mAxisYScaleLeftMargin - mLineContentMargin * 3) / maxScaleX);
        float maxAxisYScaleWidth = 0;
        mAxisXPoints = new ArrayList<>(8);

        float leftBlank = mAxisYScaleLeftMargin + mLineContentMargin;
        for (int i = maxScaleX; i > 0; i--) {
            ChartPoint point = new ChartPoint(leftBlank + eachWidth * (i - 1),
                    getHeight() - mAxisXScaleBottomMargin + fontHeight);
            if (blank != 0) {
                if ((i - 1) * blank > 0) {
                    point.setValue(mLine.getData().get((i - 1) * blank).getValue());
                } else {
                    point.setValue(mLine.getData().get(0).getValue());
                }
            } else {
                point.setValue(getFormatString(DEFAULT_AXIS_X_FORMAT, (i + 1) * 2)
                        + ":00");
            }
            float width = mPaint.measureText(point.getValue());
            if (width > maxAxisYScaleWidth) {
                maxAxisYScaleWidth = width;
            }
            mAxisXPoints.add(point);
        }

        if (blank != 0) {
            ChartPoint firstPt = new ChartPoint(leftBlank + eachWidth * maxScaleX,
                    getHeight() - mAxisXScaleBottomMargin + fontHeight);
            firstPt.setValue(mLine.getData().get(mLine.getData().size() - 1).getValue());
            mAxisXPoints.add(firstPt);
        }
//        for (int i = 0; i < maxScaleX; i++) {
//            ChartPoint point = new ChartPoint(mAxisYScaleLeftMargin +
//                    mLineContentMargin + eachWidth * (i + 1),
//                    getHeight() - mAxisXScaleBottomMargin + fontHeight);
//            if (blank != 0) {
//                if ((i + 1) * blank - 1 < mLine.getData().size()) {
//                    point.setValue(mLine.getData().get((i + 1) * blank - 1).getValue());
//                } else {
//                    point.setValue(mLine.getData().get(i * blank).getValue());
//                }
//            } else {
//                point.setValue(getFormatString(DEFAULT_AXIS_X_FORMAT, (i + 1) * 2)
//                        + ":00");
//            }
//            float width = mPaint.measureText(point.getValue());
//            if (width > maxAxisYScaleWidth) {
//                maxAxisYScaleWidth = width;
//            }
//            mAxisXPoints.add(point);
//        }
    }

    private ExtremeValue getExtremeYValue() {
        if (mLine != null) {
            ExtremeValue value = new ExtremeValue();
            float max = 0;
            float min = Float.MAX_VALUE;
            if (mLine.getData() != null) {
                for (ChartPoint point : mLine.getData()) {
                    if (point.getY() > max) {
                        max = point.getY();
                    }

                    if (point.getY() < min) {
                        min = point.getY();
                    }
                }
            }

            value.max = max;
            value.min = min;

            if (value.max == value.min) {
                value.max += 0.002;
            }

            return value;
        }
        return null;
    }

    private void drawText(Canvas canvas, String content, int x, int y, float angle, Paint paint) {
        canvas.rotate(angle, x, y);
        canvas.drawText(content, x, y, paint);
        canvas.rotate(-angle, x, y);
    }

    public void setAxis(Axis axisX, Axis axisY) {
        this.mAxisX = axisX;
        this.mAxisY = axisY;

        if (axisX != null) {
            this.mAxisXLineColor = axisX.getAxisLineColor();
            this.mAxisXLineWidth = (int) axisX.getAxisLineWidth();
        }

        if (axisY != null) {
            this.mAxisYLineColor = axisY.getAxisLineColor();
            this.mAxisYLineWidth = (int) axisY.getAxisLineWidth();
        }

        invalidate();
    }

    public void setLine(Line line) {
        this.mLine = line;
        invalidate();
    }

    public static Line createFakeLine() {
        Random random = new Random();
        float maxValue = 0.00209124f;
        Line line = new Line();
        List<ChartPoint> points = new ArrayList<>();
        int max = 120;
        long time = new Date().getTime();
        for (int i = 0; i < max; i++) {
            ChartPoint cp = new ChartPoint(generateTimeX(time, i, max), random.nextFloat() % maxValue);
            points.add(cp);
        }
        line.setData(points);
        line.setLineColor(Color.RED);
        line.setLineWidth(3);
        return line;
    }

    public static float generateTimeX(long time, int index, int max) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(time));
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        float blank = max / 24f;
        int tempIndex = (int) ((index + 1) / blank - 24 + currentHour);
        if (tempIndex < 0) {
            tempIndex += 24;
        }

        return tempIndex;
    }

    public static String generateTimeXName(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(time));
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        int currentMin = calendar.get(Calendar.MINUTE);

        int left = currentMin % 5;
        if (left != 0) {
            currentMin -= left;
        }

        String codeFormat = "%02d";
        String min = String.format(codeFormat, currentMin);
        String hour = String.format(codeFormat, currentHour);

        return String.valueOf(hour + ":" + min);
//        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
//        return sdf.format(new Date(time * 1000));
    }

    private String getFormatString(String format, float value) {
        try {
            DecimalFormat decimalFormat = new DecimalFormat(format);
            return decimalFormat.format(value);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return String.valueOf(value);
    }

    /**
     * @return get font height
     */
    public float getFontHeight(Paint paint) {
        Paint.FontMetrics fm = paint.getFontMetrics();
        return (fm.descent - fm.ascent) / 3 * 2;
    }

    public int getAxisXLineColor() {
        return mAxisXLineColor;
    }

    public void setAxisXLineColor(int mAxisXLineColor) {
        this.mAxisXLineColor = mAxisXLineColor;
    }

    public int getAxisYLineColor() {
        return mAxisYLineColor;
    }

    public void setAxisYLineColor(int mAxisYLineColor) {
        this.mAxisYLineColor = mAxisYLineColor;
    }

    public int getAxisXLineWidth() {
        return mAxisXLineWidth;
    }

    public void setAxisXLineWidth(int mAxisXLineWidth) {
        this.mAxisXLineWidth = mAxisXLineWidth;
    }

    public int getAxisYLineWidth() {
        return mAxisYLineWidth;
    }

    public void setAxisYLineWidth(int mAxisYLineWidth) {
        this.mAxisYLineWidth = mAxisYLineWidth;
    }

    public int getAxisXScaleTextColor() {
        return mAxisXScaleTextColor;
    }

    public void setAxisXScaleTextColor(int mAxisXScaleTextColor) {
        this.mAxisXScaleTextColor = mAxisXScaleTextColor;
    }

    public int getAxisYScaleTextColor() {
        return mAxisYScaleTextColor;
    }

    public void setAxisYScaleTextColor(int mAxisYScaleTextColor) {
        this.mAxisYScaleTextColor = mAxisYScaleTextColor;
    }

    public float getAxisXScaleTextSize() {
        return mAxisXScaleTextSize;
    }

    public void setAxisXScaleTextSize(float mAxisXScaleTextSize) {
        this.mAxisXScaleTextSize = mAxisXScaleTextSize;
    }

    public float getAxisYScaleTextSize() {
        return mAxisYScaleTextSize;
    }

    public void setAxisYScaleTextSize(float mAxisYScaleTextSize) {
        this.mAxisYScaleTextSize = mAxisYScaleTextSize;
    }

    public int getAxisXMaxScaleNum() {
        return mAxisXMaxScaleNum;
    }

    public void setAxisXMaxScaleNum(int mAxisXMaxScaleNum) {
        this.mAxisXMaxScaleNum = mAxisXMaxScaleNum;
    }

    public int getAxisYMaxScaleNum() {
        return mAxisYMaxScaleNum;
    }

    public void setAxisYMaxScaleNum(int mAxisYMaxScaleNum) {
        this.mAxisYMaxScaleNum = mAxisYMaxScaleNum;
    }

    public int getAxisXScaleTextPosition() {
        return mAxisXScaleTextPosition;
    }

    public void setAxisXScaleTextPosition(int mAxisXScaleTextPosition) {
        this.mAxisXScaleTextPosition = mAxisXScaleTextPosition;
    }

    public int getAxisYScaleTextPosition() {
        return mAxisYScaleTextPosition;
    }

    public void setAxisYScaleTextPosition(int mAxisYScaleTextPosition) {
        this.mAxisYScaleTextPosition = mAxisYScaleTextPosition;
    }

    public String getAxisXTitle() {
        return mAxisXTitle;
    }

    public void setAxisXTitle(String mAxisXTitle) {
        this.mAxisXTitle = mAxisXTitle;
    }

    public String getAxisYTitle() {
        return mAxisYTitle;
    }

    public void setAxisYTitle(String mAxisYTitle) {
        this.mAxisYTitle = mAxisYTitle;
    }

    public float getAxisXTitleTextSize() {
        return mAxisXTitleTextSize;
    }

    public void setAxisXTitleTextSize(float mAxisXTitleTextSize) {
        this.mAxisXTitleTextSize = mAxisXTitleTextSize;
    }

    public float getAxisYTitleTextSize() {
        return mAxisYTitleTextSize;
    }

    public void setAxisYTitleTextSize(float mAxisYTitleTextSize) {
        this.mAxisYTitleTextSize = mAxisYTitleTextSize;
    }

    public int getAxisXTitleTextColor() {
        return mAxisXTitleTextColor;
    }

    public void setAxisXTitleTextColor(int mAxisXTitleTextColor) {
        this.mAxisXTitleTextColor = mAxisXTitleTextColor;
    }

    public int getAxisYTitleTextColor() {
        return mAxisYTitleTextColor;
    }

    public void setAxisYTitleTextColor(int mAxisYTitleTextColor) {
        this.mAxisYTitleTextColor = mAxisYTitleTextColor;
    }

    public boolean isAxisXHasTitle() {
        return mAxisXHasTitle;
    }

    public void setAxisXHasTitle(boolean mAxisXHasTitle) {
        this.mAxisXHasTitle = mAxisXHasTitle;
    }

    public boolean isAxisYHasTitle() {
        return mAxisYHasTitle;
    }

    public void setAxisYHasTitle(boolean mAxisYHasTitle) {
        this.mAxisYHasTitle = mAxisYHasTitle;
    }

    public boolean isAxisXHasScaleText() {
        return mAxisXHasScaleText;
    }

    public void setAxisXHasScaleText(boolean mAxisXHasScaleText) {
        this.mAxisXHasScaleText = mAxisXHasScaleText;
    }

    public boolean isAxisYHasScaleText() {
        return mAxisYHasScaleText;
    }

    public void setAxisYHasScaleText(boolean mAxisYHasScaleText) {
        this.mAxisYHasScaleText = mAxisYHasScaleText;
    }

    public boolean isAxisXHasVerticalLine() {
        return mAxisXHasVerticalLine;
    }

    public void setAxisXHasVerticalLine(boolean mAxisXHasVerticalLine) {
        this.mAxisXHasVerticalLine = mAxisXHasVerticalLine;
    }

    public boolean isAxisYHasVerticalLine() {
        return mAxisYHasVerticalLine;
    }

    public void setAxisYHasVerticalLine(boolean mAxisYHasVerticalLine) {
        this.mAxisYHasVerticalLine = mAxisYHasVerticalLine;
    }

    public int getAxisXVerticalLineColor() {
        return mAxisXVerticalLineColor;
    }

    public void setAxisXVerticalLineColor(int mAxisXVerticalLineColor) {
        this.mAxisXVerticalLineColor = mAxisXVerticalLineColor;
    }

    public int getAxisYVerticalLineColor() {
        return mAxisYVerticalLineColor;
    }

    public void setAxisYVerticalLineColor(int mAxisYVerticalLineColor) {
        this.mAxisYVerticalLineColor = mAxisYVerticalLineColor;
    }

    public float getAxisXVerticalLineWidth() {
        return mAxisXVerticalLineWidth;
    }

    public void setAxisXVerticalLineWidth(float mAxisXVerticalLineWidth) {
        this.mAxisXVerticalLineWidth = mAxisXVerticalLineWidth;
    }

    public float getAxisYVerticalLineWidth() {
        return mAxisYVerticalLineWidth;
    }

    public void setAxisYVerticalLineWidth(float mAxisYVerticalLineWidth) {
        this.mAxisYVerticalLineWidth = mAxisYVerticalLineWidth;
    }

    public float getContentMargin() {
        return mContentMargin;
    }

    public void setContentMargin(float mContentMargin) {
        this.mContentMargin = mContentMargin;
    }

    private static class ExtremeValue {
        public float max;
        public float min;
    }
}
