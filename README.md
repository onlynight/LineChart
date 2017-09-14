# LineChart
android line chart

follow [www.chbtc.com](http://www.chbtc.com) android app line chart view.

![demo_view](./images/line_chart_demo.png)

### How to use

Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Step 2. Add the dependency

```gradle
dependencies {
    compile 'com.github.onlynight:LineChart:1.0.0'
}
```

Step 3. in your xml layout file

```xml
<com.github.onlynight.chart.LineChartView
    android:id="@+id/chart"
    android:layout_width="match_parent"
    android:layout_height="200dp"
    app:axisXHasScaleText="true"
    app:axisXHasVerticalLine="true"
    app:axisXMaxScaleNum="4"
    app:axisXScalePosition="center"
    app:axisYHasScaleText="true"
    app:axisYHasVerticalLine="true"
    app:axisYMaxScaleNum="4"
    app:axisYScalePosition="center"
    app:axisYScaleTextSize="10sp"
    app:contentMargin="10dp"
    app:hasAxisX="true"
    app:hasAxisY="true"
    app:lineContentMargin="10dp"/>
```

Step 4. in your java controller file

```java
mLineChartView = (LineChartView) findViewById(R.id.chart);
initChartData();

private void initChartData() {
    Line line = new Line().setLineColor(Color.RED).setLineWidth(5).setCube(true);
    List<ChartPoint> chartPoints = new ArrayList<>();
    Random random = new Random();
    for (int i = 0; i < 100; i++) {
        ChartPoint cp = new ChartPoint(i, random.nextInt() * 100 % 5);
        cp.setValue(String.valueOf(i));
        chartPoints.add(cp);
    }
    line.setData(chartPoints);
    mLineChartView.setLine(line);
}
```
