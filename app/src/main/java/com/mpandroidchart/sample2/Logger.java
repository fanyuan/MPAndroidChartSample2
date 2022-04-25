package com.mpandroidchart.sample2;

import android.content.Context;
import android.util.Log;

public class Logger {
    private static final String TAG = "log_tag";
    public static void d(Context context,String msg){
        Log.e(TAG,msg);
    }
}
