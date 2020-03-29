package com.example.rmider.utils;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;

public class DateUtils {

    @SuppressLint("SimpleDateFormat")
    public static String getDate(long time){
        return new SimpleDateFormat().format(time);
    }
}
