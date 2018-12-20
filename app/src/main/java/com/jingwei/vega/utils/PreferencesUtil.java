package com.jingwei.vega.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jingwei.vega.Constants;
import com.jingwei.vega.moudle.bean.BannerListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 全局信息的保存和取出
 */
public class PreferencesUtil {

    private static SharedPreferences pref;
    private static SharedPreferences.Editor editor;

    private static Boolean isShowNewGuide;

    private static Boolean isLogin;
    private static String token;
    private static int appVersionCode;

    private static Gson gson = new Gson();
    private static List<String> mRecordList = new ArrayList<>();
    private static List<BannerListBean.ListBean> mBannerList = new ArrayList<>();

    /**
     * 保存App版本号
     *
     * @param context
     * @param appVersionCode
     */
    public static void saveAppVersionCode(Context context, int appVersionCode) {
        editor = context.getSharedPreferences("appVersionCode", Context.MODE_PRIVATE)
                .edit();
        editor.putInt("appVersionCode", appVersionCode);
        editor.commit();
    }

    /**
     * 获取App版本号
     *
     * @param context
     * @return
     */
    public static int getAppVersionCode(Context context) {
        pref = context.getSharedPreferences("appVersionCode", context.MODE_PRIVATE);
        appVersionCode = pref.getInt("appVersionCode", 0);
        return appVersionCode;
    }

    /**
     * 保存用户Token
     *
     * @param context
     * @param token
     */
    public static void saveToken(Context context, String token) {
        editor = context.getSharedPreferences("token", Context.MODE_PRIVATE)
                .edit();
        editor.putString("token", token);//token
        editor.commit();
    }

    /**
     * 获取用户Token
     *
     * @param context
     * @return
     */
    public static String getToken(Context context) {
        pref = context.getSharedPreferences("token", context.MODE_PRIVATE);
        token = pref.getString("token", "");
        return token;
    }

    /**
     * 登录成功之后保存登录状态
     *
     * @param isLogin
     */
    public static void saveLoginState(Context context, Boolean isLogin) {
        editor = context.getSharedPreferences("isLogin", Context.MODE_PRIVATE)
                .edit();
        editor.putBoolean("isLogin", isLogin);//登录状态
        editor.commit();
    }

    /**
     * 获取到用户的登录状态
     *
     * @param context
     * @return
     */
    public static Boolean getLoginState(Context context) {
        pref = context.getSharedPreferences("isLogin", context.MODE_PRIVATE);
        isLogin = pref.getBoolean("isLogin", false);
        return isLogin;
    }

    /**
     * 保存是否第一次显示新手引导
     *
     * @param context
     * @param isShowNewGuide
     */
    public static void saveIsShowNewGuide(Context context, Boolean isShowNewGuide) {
        editor = context.getSharedPreferences("isShowNewGuide", Context.MODE_PRIVATE)
                .edit();
        editor.putBoolean("isShowNewGuide", isShowNewGuide);
        editor.commit();
    }

    /**
     * 获取是否第一次展示新手引导页
     *
     * @param context
     * @return
     */
    public static Boolean getIsShowNewGuide(Context context) {
        pref = context.getSharedPreferences("isShowNewGuide", context.MODE_PRIVATE);
        isShowNewGuide = pref.getBoolean("isShowNewGuide", true);
        return isShowNewGuide;
    }

    /**
     * 保存历史记录
     */
    public static void saveSearchRecord(Context context, String recordStr) {
        mRecordList = getSearchRecordList(context);
        try {
            mRecordList.add(0, recordStr);
        } catch (Exception e) {
            mRecordList.add(recordStr);
        }
        editor = context.getSharedPreferences("record", Context.MODE_PRIVATE)
                .edit();
        editor.putString("record", gson.toJson(mRecordList));
        editor.commit();
    }

    /**
     * 获取历史记录
     *
     * @param context
     * @return
     */
    public static List<String> getSearchRecordList(Context context) {
        pref = context.getSharedPreferences("record", context.MODE_PRIVATE);
        String mRecord = pref.getString("record", "");
        if (!mRecord.equals("")) {
            mRecordList = gson.fromJson(mRecord, new TypeToken<List<String>>() {
            }.getType());
        }
        return mRecordList;
    }

    /**
     * 清空历史记录
     *
     * @param context
     * @return
     */
    public static void cleanSearchRecord(Context context) {
        editor = context.getSharedPreferences(
                "record", context.MODE_PRIVATE).edit();
        editor.clear();
        editor.commit();
    }

    /**
     * 保存banner列表
     */
    public static void saveBannerList(Context context, List<BannerListBean.ListBean> listBeans) {
        editor = context.getSharedPreferences("banner", Context.MODE_PRIVATE)
                .edit();
        editor.putString("banner", gson.toJson(listBeans));
        editor.commit();
    }

    /**
     * 获取banner列表
     *
     * @param context
     * @return
     */
    public static List<BannerListBean.ListBean> getBannerList(Context context) {
        pref = context.getSharedPreferences("banner", context.MODE_PRIVATE);
        String banner = pref.getString("banner", "");
        if (!banner.equals("")) {
            mBannerList = gson.fromJson(banner, new TypeToken<List<BannerListBean.ListBean>>() {
            }.getType());
        }
        return mBannerList;
    }

}
