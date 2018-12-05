package com.jingwei.vega.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jingwei.vega.Constants;

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
    private static String userName;
    private static String userId;
    private static String token;
    private static int appVersionCode;

    private static Gson gson = new Gson();
    private static List<String> mRecordList = new ArrayList<>();

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
     * 登录成功之后保存用户的登录状态和用户信息
     *
     * @param context
     * @param isLogin
     */
    public static void saveUserInfo(Context context, Boolean isLogin, String username, String userid, String imPassword, String role_id,
                                    String branch, String phone, String role_state) {
        editor = context.getSharedPreferences("userinfo", Context.MODE_PRIVATE)
                .edit();
        editor.putBoolean("isLogin", isLogin);//登录状态
        editor.putString("username", username);//用户名
        editor.putString("userid", userid);//用户ID
        editor.putString("imPassword", imPassword);//IM登录密码
        editor.putString("role_id", role_id);//角色id，大于0表示管理员
        editor.putString("branch", branch);//所属党支部
        editor.putString("phone", phone);//用户手机号
        editor.putString("role_state", role_state);//用户身份  1-超级管理员
        editor.commit();
    }

    /**
     * 更换用户名
     *
     * @param context
     * @param username
     */
    public static void setUserName(Context context, String username) {
        editor = context.getSharedPreferences("userinfo", Context.MODE_PRIVATE)
                .edit();
        editor.putString("username", username);//用户名
        editor.commit();
    }

    /**
     * 获取到用户的登录状态
     *
     * @param context
     * @return
     */
    public static Boolean getLoginState(Context context) {
        pref = context.getSharedPreferences("userinfo", context.MODE_PRIVATE);
        isLogin = pref.getBoolean("isLogin", false);
        return isLogin;
    }

    /**
     * 获取到用户名
     *
     * @param context
     * @return
     */
    public static String getUserName(Context context) {
        pref = context.getSharedPreferences("userinfo", context.MODE_PRIVATE);
        userName = pref.getString("username", "");
        return userName;
    }

    /**
     * 获取到用户ID
     *
     * @param context
     * @return
     */
    public static String getUserID(Context context) {
        pref = context.getSharedPreferences("userinfo", context.MODE_PRIVATE);
        userId = pref.getString("userid", "");
        return userId;
    }

    /**
     * 注销用户,清空所有数据
     *
     * @param context
     */
    public static void clean(Context context) {
        /**
         * 清空SharedPreferences中的数值，来进行实现注销登录
         */
        editor = context.getSharedPreferences(
                "userinfo", context.MODE_PRIVATE).edit();
        editor.clear();
        editor.commit();
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

}
