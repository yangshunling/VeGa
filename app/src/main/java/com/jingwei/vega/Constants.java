package com.jingwei.vega;

import android.os.Environment;

public class Constants {

    /******************************** 服务器地址 **************************/
    public static String host = "http://game.lkfcni.cn";//外网测试


    /******************************** 搜索条件 **************************/
    public static Integer DEMOACTIVITY = 0;
    public static Integer HOMEFRAGMENT = 1;
    public static Integer GOODSLIBACTIVITY = 2;
    public static Integer CLASSIFICATIONFRAGMENT = 3;
    public static Integer FOCUSFRAGMENT = 4;


    /******************************** 图片路径 **************************/
    public static String IMAGEPATH = Environment.getExternalStorageDirectory().getPath() + "/vega";

}
