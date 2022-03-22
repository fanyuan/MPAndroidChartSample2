package com.mpandroidchart.sample2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LineChartActivity extends AppCompatActivity {
    LineChart chart1,chart2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);
        chart1 = findViewById(R.id.line1);
        chart2 = findViewById(R.id.line2);

        initChart(chart1,false);
        initChart(chart2,true);
    }

    private void initChart(LineChart chart,boolean legendEnabled) {
        ArrayList<Entry> list = new ArrayList<>();
        //其中两个数字对应的分别是   X轴   Y轴
//        list.add(new Entry(0,7));
//        list.add(new Entry(1,10));
//        list.add(new Entry(2,12));
//        list.add(new Entry(3,6));
//        list.add(new Entry(4,3));
        Random random = new Random();

        list.add(new Entry(0,random.nextInt(15)));
        list.add(new Entry(1,random.nextInt(15)));
        list.add(new Entry(2,random.nextInt(15)));
        list.add(new Entry(3,random.nextInt(15)));
        list.add(new Entry(4,random.nextInt(15)));
        list.add(new Entry(5,random.nextInt(15)));
        list.add(new Entry(6,random.nextInt(15)));
        list.add(new Entry(7,random.nextInt(15)));
        //list.add(new Entry(5,random.nextInt(15)));

        //是否绘制X轴上的网格线（背景里面的竖线）
        chart.getXAxis().setDrawGridLines(true);
        //是否绘制Y轴上的网格线（背景里面的横线）
        chart.getAxisLeft().setDrawGridLines(true);

        //list是你这条线的数据  "语文" 是你对这条线的描述（也就是图例上的文字）
        LineDataSet lineDataSet=new LineDataSet(list,"语文");
        LineData lineData=new LineData(lineDataSet);
        chart.setData(lineData);


        //简单美化

        //   X轴所在位置   默认为上面
        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        //隐藏右边的Y轴
        chart.getAxisRight().setEnabled(false);

        chart.getLegend().setEnabled(legendEnabled);
    }

    public void showChart2(View view) {
        initChart(chart2,true);

        chart2.notifyDataSetChanged();
        chart2.invalidate();
        chart2.animateX(3000);
        chart2.animateY(1000);
    }
}