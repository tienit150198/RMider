package com.example.rmider.config;

import com.example.rmider.R;

public class Constants {
    public static final String APP_NAME = AppConfig.getContext().getString(R.string.app_name);
    public static final String KEY_ACCOUNT      = "KEY_ACCOUNT";
    public static final String KEY_TYPE         = "KEY_TYPE";

    public static final int TYPE_REGISTER       = 1;
    public static final int TYPE_LOGIN          = 2;
    public static final int TYPE_MOTO           = 3;
    public static final int TYPE_OTO            = 4;

    public static final int TYPE_VEHICLE_INFORMATION = 5;
    public static final int TYPE_VEHICLE_MAINTENANCE = 6;
    public static final int TYPE_INSURRANCE = 7;
    public static final int TYPE_DIARY = 8;
    public static final int TYPE_TRAFFIC_LAWS = 9;
    public static final int TYPE_FUEL_INFORMATION = 10;
    public static final int TYPE_REPAIR_LOCATION = 11;
    public static final int TYPE_ASSISTANCE_HELP = 12;
}
