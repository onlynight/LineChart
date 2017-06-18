package com.github.onlynight.linechart.demo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.onlynight.chart.ChartPoint;
import com.github.onlynight.chart.Line;
import com.github.onlynight.chart.LineChartView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class XmlConfigActivity extends AppCompatActivity {

    private LineChartView mLineChartView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml_config);
        mLineChartView = (LineChartView) findViewById(R.id.chart);
        initChartData();
    }

    private void initChartData() {
//        Line line = new Line().setLineColor(
//                getResources().getColor(R.color.colorPrimary)).setLineWidth(5);
        Line line = new Line().setLineColor(Color.RED).setLineWidth(5).setCube(true);
        List<ChartPoint> chartPoints = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 15; i++) {
            ChartPoint cp = new ChartPoint(i, random.nextInt() * 100 % 5);
            cp.setValue(String.valueOf(i));
            chartPoints.add(cp);
        }
        line.setData(chartPoints);
        mLineChartView.setLine(line);
    }

}
