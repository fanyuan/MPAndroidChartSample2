package com.mpandroidchart.sample2;

import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.ChartHighlighter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LineChartManager2 {
    private LineChart lineChart;
    private YAxis leftAxis;   //左边Y轴
    private YAxis rightAxis;  //右边Y轴
    private XAxis xAxis;      //X轴

    public LineChartManager2(LineChart mLineChart) {
        this.lineChart = mLineChart;
        leftAxis = lineChart.getAxisLeft();
        rightAxis = lineChart.getAxisRight();
        xAxis = lineChart.getXAxis();

    }

    /**
     * 初始化LineChart
     */
    private void initLineChart(boolean legendShow) {
        lineChart.setDrawGridBackground(false);
        //显示边界
        lineChart.setDrawBorders(false);

        //设置动画效果
        lineChart.animateX(1000);

        lineChart.setTouchEnabled(true); // 所有触摸事件,默认true
        lineChart.setDragEnabled(false);    // 可拖动,默认true
        lineChart.setScaleEnabled(false);   // 两个轴上的缩放,X,Y分别默认为true
        lineChart.setScaleXEnabled(false);  // X轴上的缩放,默认true
        lineChart.setScaleYEnabled(false);  // Y轴上的缩放,默认true
        //lineChart.setPinchZoom(true);  // X,Y轴同时缩放，false则X,Y轴单独缩放,默认false
        lineChart.setDoubleTapToZoomEnabled(false); // 双击缩放,默认true
        lineChart.setDragDecelerationEnabled(true);    // 抬起手指，继续滑动,默认true

        //折线图例 标签 设置
        Legend legend = lineChart.getLegend();
        if (legendShow) {
            legend.setDrawInside(false);
            legend.setFormSize(8);
            legend.setXEntrySpace(7f);
            legend.setYEntrySpace(0f);
            legend.setYOffset(0f);
            // legend.setForm(Legend.LegendForm.SQUARE);
            // 文字的大小
            legend.setTextSize(12);
            //显示位置
            legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
            legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
            legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);

        } else {
            legend.setForm(Legend.LegendForm.NONE);
        }

        //XY轴的设置
        //X轴设置显示位置在底部
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        //xAxis.setGranularity(1f);
        // 不绘制网格线

        xAxis.setAxisMaximum(150);

        xAxis.setDrawGridLines(true);
        xAxis.setGridColor(Color.parseColor("#d8d8d8"));
        //设置最后和第一个标签不超出x轴
        xAxis.setAvoidFirstLastClipping(true);
//        设置线的宽度
        xAxis.setAxisLineWidth(1.0f);
        xAxis.setAxisLineColor(Color.parseColor("#d5d5d5"));
        //xAxis.setDrawLabels(true);
        xAxis.setGridColor(lineChart.getContext().getResources().getColor(R.color.teal_700));
        //设置横轴字体颜色
        xAxis.setTextColor(lineChart.getContext().getResources().getColor(R.color.teal_200));

        //保证Y轴从0开始，不然会上移一点
        leftAxis.setAxisMinimum(0f);
        // 显示数字但不显示线
        //leftAxis.setDrawAxisLine(true);
        leftAxis.setTextColor(Color.parseColor("#000000"));

        leftAxis.setDrawGridLines(true);//false
//        设置网格为虚线
        //leftAxis.enableGridDashedLine(10f, 10f, 0f);
        //Color.parseColor("#000000") //"#d8d8d8"
        leftAxis.setGridColor(lineChart.getContext().getResources().getColor(R.color.teal_700));
        leftAxis.setAxisLineColor(Color.parseColor("#d5d5d5"));
        rightAxis.setAxisMinimum(0f);
        //设置纵轴字体颜色
        leftAxis.setTextColor(lineChart.getContext().getResources().getColor(R.color.teal_200));

        //.setColor(lineChart.getContext().getResources().getColor(R.color.teal_200));
        Paint p = lineChart.getPaint(Chart.PAINT_LEGEND_LABEL);

        Logger.d(lineChart.getContext()," paint = " + p);

        //leftAxis.setDrawLabels(true);
        //rightAxis.setDrawLabels(true);
        // 线跟数据都不显示
        rightAxis.setEnabled(false); //右侧Y轴不显示


    }

    /**
     * 初始化曲线 每一个LineDataSet代表一条线
     *
     * @param lineDataSet
     * @param color
     * @param mode        折线图是否填充
     */
    private void initLineDataSet(LineDataSet lineDataSet, int color, boolean mode) {
        lineDataSet.setColor(color);
        //color
        lineDataSet.setCircleColor(Color.parseColor("#00ff0000"));
        lineDataSet.setLineWidth(2f);
        lineDataSet.setCircleRadius(3f);
        //设置曲线值的圆点是实心还是空心
        //true
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setValueTextSize(9f);

        // 不显示具体值
        lineDataSet.setDrawValues(false);

//        lineDataSet.setHighlightEnabled(false);
        //设置折线图填充
        lineDataSet.setDrawFilled(mode);
        //设置填充颜色
        lineDataSet.setFillColor(color);
//        lineDataSet.setFormLineWidth(2f);
//        lineDataSet.setFormSize(15.f);
        //线模式为圆滑曲线（默认折线）
        lineDataSet.setMode(LineDataSet.Mode.LINEAR);

        lineDataSet.setHighlightEnabled(true);
        //lineChart.getContext().getResources().getColor(R.color.black)
        lineDataSet.setHighLightColor(Color.parseColor("#00ff0000"));
//        lineDataSet.setDrawHighlightIndicators(true);

        //lineChart.setHighlighter(new ChartHighlighter());
    }

    /**
     * 跟showLineCharDouble配对
     *
     * @param lineDataSet
     * @param color
     * @param mode
     */
    private void initLineCusDataSet(LineDataSet lineDataSet, int color, boolean mode) {
        initLineDataSet(lineDataSet, color, mode);
        lineDataSet.setDrawValues(false);
        lineDataSet.setDrawCircles(false);
    }

    /**
     * 设置描述信息
     *
     * @param str
     */
    public void setDescription(String str) {
        Description description = new Description();
        description.setText(str);
        lineChart.setDescription(description);
        lineChart.invalidate();
    }

    /**
     * 展示线性图(多条)
     *
     * @param xAxisValues
     * @param yAxisValues 多条曲线Y轴数据集合的集合
     * @param colours
     */
    public void showLineChartNoFill(List<Float> xAxisValues, List<List<Float>> yAxisValues, List<Integer> colours) {
        initLineChart(false);
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        for (int i = 0; i < yAxisValues.size(); i++) {
            ArrayList<Entry> entries = new ArrayList<>();
            for (int j = 0; j < yAxisValues.get(i).size(); j++) {
                if (j >= xAxisValues.size()) {
                    j = xAxisValues.size() - 1;
                }
                entries.add(new Entry(xAxisValues.get(j), yAxisValues.get(i).get(j)));
            }
            LineDataSet lineDataSet = new LineDataSet(entries, null);//labels.get(i)


            initLineDataSet(lineDataSet, colours.get(i), false);
            lineDataSet.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
            dataSets.add(lineDataSet);
        }
        LineData data = new LineData(dataSets);
        String[] xValues = {"6:00", "9:00", "12:00", "15:00", "18:00", "21:00", "00:00", "03:00"};
        //xAxis.setLabelCount(xAxisValues.size(), true);
        xAxis.setLabelCount(xValues.length, true);
        xAxis.setValueFormatter(new XAxisValueFormatter(xValues));

        //xAxis.setDrawLabels(true);

        lineChart.setData(data);
    }
    /**
     * 展示线性图(多条)
     *
     * @param xAxisValues
     * @param yAxisValues 多条曲线Y轴数据集合的集合
     * @param colours
     */
    public void showLineChartNoFill2(ArrayList<ArrayList<Float>> xAxisValues, List<List<Float>> yAxisValues, List<Integer> colours) {
        initLineChart(false);
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        for (int i = 0; i < yAxisValues.size(); i++) {
            ArrayList<Entry> entries = new ArrayList<>();
            ArrayList<Float> xvalue = xAxisValues.get(i);
            List<Float> yvalue = yAxisValues.get(i);
            for (int j = 0; j < yvalue.size(); j++) {
                entries.add(new Entry(xvalue.get(j), yvalue.get(j)));
            }
            LineDataSet lineDataSet = new LineDataSet(entries, null);//labels.get(i)


            initLineDataSet(lineDataSet, colours.get(i), false);
            lineDataSet.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
            dataSets.add(lineDataSet);
        }
        Log.d("tag","lineDataSet.size = " + dataSets.size());
        LineData data = new LineData(dataSets);
        String[] xValues = {"6:00", "9:00", "12:00", "15:00", "18:00", "21:00", "00:00", "03:00"};
        //xAxis.setLabelCount(xAxisValues.size(), true);
        xAxis.setLabelCount(xValues.length, true);
        xAxis.setValueFormatter(new XAxisValueFormatter(xValues));
        lineChart.getXAxis().setGranularityEnabled(true);
        lineChart.getXAxis().setGranularity(30f);

        //xAxis.setDrawLabels(true);

        lineChart.getAxisLeft().setValueFormatter(new YAxisValueFormatter());
//        leftAxis.setGranularity(20f);
//        rightAxis.setGranularity(20f);
        //设置 Y Left 值标签间隔
        lineChart.getAxisLeft().setGranularity(35f);
        //设置 Y Right 值标签间隔
        lineChart.getAxisRight().setGranularity(35f);
//        leftAxis.setValueFormatter(new YAxisValueFormatter());
//        rightAxis.setValueFormatter(new YAxisValueFormatter());

        lineChart.setData(data);
    }

    /**
     * 展示折线图(一条)
     *
     * @param xAxisValues
     * @param yAxisValues
     * @param label
     * @param color
     */
    public void showLineChart(List<Float> xAxisValues, List<Float> yAxisValues, String label, int color) {
        initLineChart(false);
        ArrayList<Entry> entries = new ArrayList<>();
        for (int i = 0; i < xAxisValues.size(); i++) {
            entries.add(new Entry(xAxisValues.get(i), yAxisValues.get(i)));
        }
        // 每一个LineDataSet代表一条线
        LineDataSet lineDataSet = new LineDataSet(entries, label);
        initLineDataSet(lineDataSet, color, true);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);
        LineData data = new LineData(dataSets);
        //设置X轴的刻度数
//        xAxis.setLabelCount(xAxisValues.size(), false);
        xAxis.setTextColor(Color.parseColor("#a1a1a1"));
        //文字倾斜度
//        xAxis.setLabelRotationAngle(-45);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                if (value == 0) {
                    return "阜成门";
                }
                if (value == 2) {
                    return "国贸";
                }
                if (value == 3) {
                    return "积水潭";
                }
                if (value == 4) {
                    return "三元桥";
                }
                if (value == 5) {
                    return "西直门";
                }
                return "西直门";
            }
        });

        lineChart.setData(data);

    }


    /**
     * 展示折线图(一条)
     *
     * @param xAxisValues
     * @param yAxisValues
     * @param label
     * @param color
     */
    public void showAirLineChart(List<Float> xAxisValues, List<Float> yAxisValues, String label, int color) {
        initLineChart(false);
        ArrayList<Entry> entries = new ArrayList<>();
        for (int i = 0; i < xAxisValues.size(); i++) {
            entries.add(new Entry(xAxisValues.get(i), yAxisValues.get(i)));
        }
        // 每一个LineDataSet代表一条线
        LineDataSet lineDataSet = new LineDataSet(entries, label);
        initLineDataSet(lineDataSet, color, true);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);
        LineData data = new LineData(dataSets);
        //设置X轴的刻度数
//        xAxis.setLabelCount(xAxisValues.size(), false);
        xAxis.setTextColor(Color.parseColor("#a1a1a1"));
        //文字倾斜度
//        xAxis.setLabelRotationAngle(-45);
        lineChart.setData(data);

    }


    /**
     * 展示线性图(多条)
     *
     * @param xAxisValues
     * @param yAxisValues 多条曲线Y轴数据集合的集合
     * @param labels
     * @param colours
     */
    public void showLineChart(List<Float> xAxisValues, List<List<Float>> yAxisValues, List<String> labels, List<Integer> colours) {
        initLineChart(true);
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        for (int i = 0; i < yAxisValues.size(); i++) {
            ArrayList<Entry> entries = new ArrayList<>();
            for (int j = 0; j < yAxisValues.get(i).size(); j++) {
                if (j >= xAxisValues.size()) {
                    j = xAxisValues.size() - 1;
                }
                entries.add(new Entry(xAxisValues.get(j), yAxisValues.get(i).get(j)));
            }
            LineDataSet lineDataSet = new LineDataSet(entries, labels.get(i));

            initLineDataSet(lineDataSet, colours.get(i), true);
            lineDataSet.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
            dataSets.add(lineDataSet);
        }
        LineData data = new LineData(dataSets);
        xAxis.setLabelCount(xAxisValues.size(), true);
        String[] xValues = {"6:00", "9:00", "12:00", "15:00", "18:00", "21:00", "00:00"};
        xAxis.setValueFormatter(new XAxisValueFormatter(xValues));

        lineChart.setData(data);
    }

    /**
     * 展示线性图(多条)
     *
     * @param xAxisValues
     * @param yAxisValues 多条曲线Y轴数据集合的集合
     * @param colours
     */
    public void showLineChart(List<Float> xAxisValues, List<List<Float>> yAxisValues,List<Integer> colours) {
        initLineChart(true);
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        for (int i = 0; i < yAxisValues.size(); i++) {
            ArrayList<Entry> entries = new ArrayList<>();
            for (int j = 0; j < yAxisValues.get(i).size(); j++) {
                if (j >= xAxisValues.size()) {
                    j = xAxisValues.size() - 1;
                }
                entries.add(new Entry(xAxisValues.get(j), yAxisValues.get(i).get(j)));
            }
            LineDataSet lineDataSet = new LineDataSet(entries, "");

            initLineDataSet(lineDataSet, colours.get(i), true);
            lineDataSet.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
            dataSets.add(lineDataSet);
        }
        LineData data = new LineData(dataSets);
        xAxis.setLabelCount(xAxisValues.size(), true);
        String[] xValues = {"6:00", "9:00", "12:00", "15:00", "18:00","21:00"};
        xAxis.setValueFormatter(new XAxisValueFormatter(xValues));


        lineChart.setData(data);
    }
    public class YAxisValueFormatter implements IAxisValueFormatter {

        private Float[] values = {0f,40f,80f,120f,160f,200f};

        public YAxisValueFormatter() {}

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            //Log.d("yyyy","yyyy getFormattedValue  --- value = " + value);
            ArrayList<Float> list = new ArrayList<Float>(Arrays.asList(values));
            String str = value + "";
//            if(list.contains(value)){
//                str = value + "";
//            }
            Log.d("yyyy","yyyy getFormattedValue  --- value = " + value + "--- str = " + str);
            return str;
        }

    }
    public class XAxisValueFormatter implements IAxisValueFormatter {

        private String[] xValues;

        public XAxisValueFormatter(String[] xValues) {
            this.xValues = xValues;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            Log.d("getFormattedValue","getFormattedValue  --- value = " + value);
            int index = (int) value;
//            if(index >= xValues.length){
//                return "";
//            }
//            String str = xValues[index];
//            Log.d("getFormattedValue","getFormattedValue  --- str = " + str);
            return index + "";
        }

    }

    /**
     * 只显示两条 其中一条是影 定制化
     *
     * @param xAxisValues
     * @param yAxisValues 只有两条数据
     * @param labels
     * @param colours
     */
    public void showLineCharDouble(List<Float> xAxisValues, List<List<Float>> yAxisValues, List<String> labels, List<Integer> colours) {
        initLineChart(true);
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        //
        for (int i = 0; i < 2; i++) {
            ArrayList<Entry> entries = new ArrayList<>();
            for (int j = 0; j < yAxisValues.get(i).size(); j++) {
                if (j >= xAxisValues.size()) {
                    j = xAxisValues.size() - 1;
                }
                entries.add(new Entry(xAxisValues.get(j), yAxisValues.get(i).get(j)));
            }
            LineDataSet lineDataSet = new LineDataSet(entries, labels.get(i));

            initLineCusDataSet(lineDataSet, colours.get(i), false);
//            if (i == 1) {
//                initLineCusDataSet(lineDataSet, colours.get(i), false);
//            } else {
//                initLineDataSet(lineDataSet, colours.get(i), false);
//            }

            dataSets.add(lineDataSet);
        }
        LineData data = new LineData(dataSets);
        xAxis.setLabelCount(xAxisValues.size(), true);
        lineChart.setData(data);
    }

    /**
     * 设置Y轴值
     *
     * @param max
     * @param min
     * @param labelCount
     */
    public void setYAxis(float max, float min, int labelCount) {
        if (max < min) {
            return;
        }
        leftAxis.setAxisMaximum(max);
        leftAxis.setAxisMinimum(min);
        leftAxis.setLabelCount(labelCount, false);

        rightAxis.setAxisMaximum(max);
        rightAxis.setAxisMinimum(min);
        rightAxis.setLabelCount(labelCount, false);
        lineChart.invalidate();
    }

    /**
     * 设置X轴的值
     *
     * @param max
     * @param min
     * @param labelCount
     */
    public void setXAxis(float max, float min, int labelCount) {
        xAxis.setAxisMaximum(max);
        xAxis.setAxisMinimum(min);
        xAxis.setLabelCount(labelCount, true);

        lineChart.invalidate();
    }

    /**
     * 设置高限制线
     *
     * @param high
     * @param name
     */
    public void setHightLimitLine(float high, String name, int color) {
        if (name == null) {
            name = "高限制线";
        }
        LimitLine hightLimit = new LimitLine(high, name);
        hightLimit.setLineWidth(2f);
        hightLimit.setTextSize(10f);
        hightLimit.setLineColor(color);
        hightLimit.setTextColor(color);
        leftAxis.addLimitLine(hightLimit);
        lineChart.invalidate();
    }

    /**
     * 设置低限制线
     *
     * @param low
     * @param name
     */
    public void setLowLimitLine(int low, String name) {
        if (name == null) {
            name = "低限制线";
        }
        LimitLine hightLimit = new LimitLine(low, name);
        hightLimit.setLineWidth(4f);
        hightLimit.setTextSize(10f);
        leftAxis.addLimitLine(hightLimit);
        lineChart.invalidate();
    }


}



