package com.jingwei.vega.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.jingwei.vega.Constants;

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
    private static String imPassword;

    private static String roleId;

    private static String branch;

    private static String phone;

    private static String role_state;

    private static int notifyCount;
    private static int appVersionCode;

    private static String arrayJson;
    private static String arrayJson1;
    private static String arrayJson2;

    private static int TAG;

    //敏感词
    private static String sensitiveWordsJson;

    private static String host = "";

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
        editor.putString("role_id",role_id);//角色id，大于0表示管理员
        editor.putString("branch",branch);//所属党支部
        editor.putString("phone",phone);//用户手机号
        editor.putString("role_state",role_state);//用户身份  1-超级管理员
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
     * 获取到用户IM登录密码
     *
     * @param context
     * @return
     */
    public static String getUserIMPassword(Context context) {
        pref = context.getSharedPreferences("userinfo", context.MODE_PRIVATE);
        imPassword = pref.getString("imPassword", "");
        return imPassword;
    }

    /**
     * 获取到用户角色，role_id大于0为管理员，可以发起会议
     *
     * @param context
     * @return
     */
    public static String getRoleId(Context context) {
        pref = context.getSharedPreferences("userinfo", context.MODE_PRIVATE);
        roleId = pref.getString("role_id","");
        return roleId;
    }


    /**
     * 获取到用户角色所在党支部
     *
     * @param context
     * @return
     */
    public static String getBranch(Context context) {
        pref = context.getSharedPreferences("userinfo", context.MODE_PRIVATE);
        branch = pref.getString("branch","");
        return branch;
    }

    /**
     * 获取到用户角色电话
     *
     * @param context
     * @return
     */
    public static String getPhone(Context context) {
        pref = context.getSharedPreferences("userinfo", context.MODE_PRIVATE);
        phone = pref.getString("phone","");
        return phone;
    }

    /**
     * 获取到用户角色,1-超级管理员,可查看学习统计以及上传下达等
     *
     * @param context
     * @return
     */
    public static String getRoleState(Context context) {
        pref = context.getSharedPreferences("userinfo", context.MODE_PRIVATE);
        role_state = pref.getString("role_state","");
        return role_state;
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

    /********************************************************* 集合 **************************************************/

    /**
     * 必选人员
     * 保存Array集合Json
     *
     * @param context
     * @param arrayJson
     */
    public static void saveBiXuanJson(Context context, String arrayJson) {
        editor = context.getSharedPreferences("bixuan", Context.MODE_PRIVATE)
                .edit();
        editor.putString("bixuan", arrayJson);
        editor.commit();
    }

    /**
     * 必选人员
     * 获取Array集合Json
     *
     * @param context
     * @return
     */
    public static String getBiXuanJson(Context context) {
        pref = context.getSharedPreferences("bixuan", context.MODE_PRIVATE);
        arrayJson1 = pref.getString("bixuan", "");
        return arrayJson1;
    }

    /**
     * 可选人员
     * 保存Array集合Json
     *
     * @param context
     * @param arrayJson
     */
    public static void saveKeXuanJson(Context context, String arrayJson) {
        editor = context.getSharedPreferences("kexuan", Context.MODE_PRIVATE)
                .edit();
        editor.putString("kexuan", arrayJson);
        editor.commit();
    }

    /**
     * 可选人员
     * 获取Array集合Json
     *
     * @param context
     * @return
     */
    public static String getKeXuanJson(Context context) {
        pref = context.getSharedPreferences("kexuan", context.MODE_PRIVATE);
        arrayJson = pref.getString("kexuan", "");
        return arrayJson;
    }

    /**
     * 讨论组人员
     * 保存Array集合Json
     *
     * @param context
     * @param arrayJson
     */
    public static void saveGroupJson(Context context, String arrayJson) {
        editor = context.getSharedPreferences("group", Context.MODE_PRIVATE)
                .edit();
        editor.putString("group", arrayJson);
        editor.commit();
    }

    /**
     * 讨论组人员
     * 获取Array集合Json
     *
     * @param context
     * @return
     */
    public static String getGroupJson(Context context) {
        pref = context.getSharedPreferences("group", context.MODE_PRIVATE);
        arrayJson = pref.getString("group", "");
        return arrayJson;
    }

    /**
     * 清空选中的用户的数据
     *
     * @param context
     */
    public static void cleanArrayJson(Context context) {
        editor = context.getSharedPreferences(
                "bixuan", context.MODE_PRIVATE).edit();
        editor.clear();
        editor.commit();
        editor = context.getSharedPreferences(
                "kexuan", context.MODE_PRIVATE).edit();
        editor.clear();
        editor = context.getSharedPreferences(
                "group", context.MODE_PRIVATE).edit();
        editor.clear();
        editor.commit();
    }

    /**
     * 保存当前是否为第一次登陆
     *
     * @param context
     * @param tag 0-表示第一次登陆，1表示第二次登陆
     */
    public static void saveTAG(Context context, int tag) {
        editor = context.getSharedPreferences("tag", Context.MODE_PRIVATE)
                .edit();
        editor.putInt("tag", tag);
        editor.commit();
    }

    /**
     * 获取标记值，判断当前是否是第一次登陆
     *
     * @param context
     * @return
     */
    public static int getTAG(Context context) {
        pref = context.getSharedPreferences("tag", context.MODE_PRIVATE);
        TAG = pref.getInt("tag", 0);
        return TAG;
    }

    /**
     * 敏感词
     * 保存Array集合Json
     *
     * @param context
     * @param sensitiveWordsJson
     */
    public static void saveSensitiveWordsJson(Context context, String sensitiveWordsJson) {
        editor = context.getSharedPreferences("sensitiveWordsJson", Context.MODE_PRIVATE)
                .edit();
        editor.putString("sensitiveWordsJson", sensitiveWordsJson);
        editor.commit();
    }

    /**
     * 敏感词
     * 获取Array集合Json
     *
     * @param context
     * @return
     */
    public static String getSensitiveWordsJson(Context context) {
        pref = context.getSharedPreferences("sensitiveWordsJson", context.MODE_PRIVATE);
        sensitiveWordsJson = pref.getString("sensitiveWordsJson", "");
        return sensitiveWordsJson;
    }


    /**
     * 保存host
     *
     * @param context
     * @param host
     */
    public static void saveHost(Context context, String host) {
        editor = context.getSharedPreferences("host", Context.MODE_PRIVATE)
                .edit();
        editor.putString("host", host);//token
        editor.commit();
    }

    /**
     * 获取host
     *
     * @param context
     * @return
     */
    public static String getHost(Context context) {
        pref = context.getSharedPreferences("host", context.MODE_PRIVATE);
        host = pref.getString("host", Constants.host);
        return host;
    }
}
