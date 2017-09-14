package com.github.onlynight.linechart.demo;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.onlynight.chart.Axis;
import com.github.onlynight.chart.ChartPoint;
import com.github.onlynight.chart.Line;
import com.github.onlynight.chart.LineChartView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CodeConfigActivity extends AppCompatActivity {

    private LineChartView mChartView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_config);

        mChartView = (LineChartView) findViewById(R.id.chart);
        initChartLine(this, mChartView);
        initChartData();
    }

    /**
     * init chart line
     *
     * @param context
     */
    private void initChartLine(Context context, LineChartView chartView) {

        Axis axisX = new Axis().setAxisHasScaleText(true).
                setAxisLineColor(context.getResources().
                        getColor(R.color.colorCommonTextBlack2)).
                setAxisLineWidth(context.getResources().
                        getDimension(R.dimen.line_chart_vertical_line_width)).
                setAxisScaleTextColor(context.getResources().
                        getColor(R.color.colorCommonTextBlack2)).
                setAxisScaleTextSize(context.getResources().
                        getDimension(R.dimen.chart_text_size)).
                setHasVerticalAxisLine(true).
                setVerticalAxisLineColor(context.getResources().
                        getColor(R.color.colorChartBaseColor)).
                setVerticalAxisLineWidth(context.getResources().
                        getDimension(R.dimen.line_chart_vertical_line_width)).
                setHasTitle(false).
                setMaxScaleNum(4).
                setTitleTextSize(context.getResources().
                        getDimension(R.dimen.chart_text_size)).
                setTitleTextColor(context.getResources().
                        getColor(R.color.colorChartBaseColor)).
                setAxisScaleTextPosition(
                        Axis.AXIS_SCALE_TEXT_POSITION_CENTER);

        String format = getFormatter();

        Axis axisY = new Axis().setAxisHasScaleText(true).
                setAxisLineColor(context.getResources().
                        getColor(R.color.colorCommonTextBlack2)).
                setAxisLineWidth(context.getResources().
                        getDimension(R.dimen.line_chart_vertical_line_width)).
                setAxisScaleTextColor(context.getResources().
                        getColor(R.color.colorCommonTextBlack2)).
                setAxisScaleTextSize(context.getResources().
                        getDimension(R.dimen.chart_text_size)).
                setHasVerticalAxisLine(true).
                setVerticalAxisLineColor(context.getResources().
                        getColor(R.color.colorChartBaseColor)).
                setVerticalAxisLineWidth(context.getResources().
                        getDimension(R.dimen.line_chart_vertical_line_width)).
                setHasTitle(false).
                setTitleTextSize(context.getResources().
                        getDimension(R.dimen.chart_text_size)).
                setTitleTextColor(context.getResources().
                        getColor(R.color.colorChartBaseColor)).
                setAxisScaleTextPosition(
                        Axis.AXIS_SCALE_TEXT_POSITION_CENTER).
                setScaleTextFormat(format);

        chartView.setAxis(axisX, axisY);
    }

    public String getFormatter() {
        return "0.00000000";
    }

    private void initChartData() {
//        Line line = new Line().setLineColor(
//                getResources().getColor(R.color.colorPrimary)).setLineWidth(5);
        Line line = new Line().setLineColor(Color.RED).setLineWidth(5).setCube(true);
        List<ChartPoint> chartPoints = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            ChartPoint cp = new ChartPoint(i, random.nextInt() * 100 % 5);
            cp.setValue(String.valueOf(i));
            chartPoints.add(cp);
        }
        line.setData(chartPoints);
        mChartView.setLine(line);
    }
}
