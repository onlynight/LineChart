package com.github.onlynight.chart;

/**
 * Created by lion on 2017/5/4.
 * chart point value
 */

public class ChartPoint {

    private float x;
    private float y;

    private String value;
    private long time;

    public ChartPoint() {
    }

    public ChartPoint(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
