package com.mpandroidchart.sample2;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LineChartActivity3 extends AppCompatActivity {
    private static final String TAG = "ddebug";
    LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart_activity2);
        lineChart =  findViewById(R.id.line_chart);
        showLineChart(lineChart);

        lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Log.d(TAG,"---onValueSelected---getDataIndex" + h.getDataIndex() + " --- e = " + e.toString());
                Log.d(TAG,"---onValueSelected---e.getX() = " + e.getX() + " --- e.getY() = " + e.getY());
            }

            @Override
            public void onNothingSelected() {
                Log.d(TAG,"---onNothingSelected---");
            }
        });

        Logger.d(this,"dot_width---" + getResources().getDimension(R.dimen.dot_width));
    }
    private void showLineChart(LineChart lineChart) {

        LineChartManager2 lineChartManager=new LineChartManager2(lineChart);
        //设置x轴的数据
        ArrayList<ArrayList<Float>> xValues = new ArrayList<>();
        Float[] x1 = {0f,10f,30f,80f,90f};
        ArrayList<Float> xValues1 = new ArrayList<>(Arrays.asList(x1));

        Float[] x2 = {1f,20f,60f,70f,89f};
        ArrayList<Float> xValues2 = new ArrayList<>(Arrays.asList(x2));
        xValues.add(xValues1);
        xValues.add(xValues2);
        Logger.d(this,"xValues = " + xValues.toString());

        //设置y轴的数据()
        List<List<Float>> yValues = new ArrayList<>();

        List<Float> y1Value = new ArrayList<>();

        for (int j = 0; j <= 4; j++) {
            float value = (float) (Math.random() * 80);
            if(j%2 == 1){
                value = value * -3;
            }
            value = Math.abs(value);
            Log.d("tag","y1value = " + value);
            y1Value.add(value);
        }
        yValues.add(y1Value);
        Float[] y2 = {10f,27f,60f,10.6f,11f};
        yValues.add(new ArrayList<>(Arrays.asList(y2)));
        Logger.d(this,"yValues = " + y1Value.toString());

        Collections.reverse(y1Value);
        Logger.d(this,"yValues 123 = " + yValues.toString());

        List<Integer> colorList = new ArrayList<>();
        colorList.add(Color.parseColor("#ff0000"));
        colorList.add(Color.parseColor("#ffff00"));

        Log.d("tag",xValues.toString());
        lineChartManager.showLineChartNoFill2(xValues, yValues, colorList);
        lineChartManager.setDescription("这是一个描述");
        lineChart.getDescription().setEnabled(false);

    }

}