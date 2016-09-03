package com.bihu.xiyi;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.bihu.xiyi.cache.ImageCache;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by SteveCaesar on 2016/8/8.
 */
public class MyApplication extends Application {
    private SharedPreferences preferences;
    private static final String CONFIG_NAME = "config";
    private RequestQueue requestQueue;
    private  ImageLoader imageLoader;
    @Override
    public void onCreate() {
        requestQueue= Volley.newRequestQueue(this);
        imageLoader=new ImageLoader(requestQueue,new ImageCache());
        preferences = getSharedPreferences(CONFIG_NAME, Context.MODE_PRIVATE);
        super.onCreate();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }
    public RequestQueue getRequestQueues() {
        return requestQueue;
    }
    public  ImageLoader getImageLoader(){
        return imageLoader;
    }
    public SharedPreferences getSharedPreferences() {
        return preferences;
    }

    public static class ConfigKey {
        public static final String is_one_start = "is_one_start";
    }
    public static class WebApi {
        public static final String header_X_Bmob_Application_Id = "64f3b4d3d4ffe149b42372daa05ea4f2";
        public static final String header_X_Bmob_REST_API_Key = "d0657aeda8d646ee199d4c8bc3b1234d";
        /**查询的 对象或Class 不存在 或者 登录接口的用户名或密码不正确*/
        public static final int ERRORCODE_101 = 101;
        /**验证码错误*/
        public static final int ERRORCODE_207 =207;
    }
}
