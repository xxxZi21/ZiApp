package com.example.zhan.ziapp.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by ZHan on 2018/3/13.
 */

public class HttpUtil {
    public static void sendOkhttpRequest(String address ,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }
}
