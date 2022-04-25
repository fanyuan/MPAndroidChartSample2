package com.mpandroidchart.sample2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toLineChart(View view) {
        startActivity(new Intent(this,LineChartActivity.class));
    }

    public void toRadarChart(View view) {
        startActivity(new Intent(this,RadarChartActivity.class));
    }

    public void toLineChartActivity3(View view) {
        startActivity(new Intent(this,LineChartActivity3.class));
    }
}