package com.example.zhan.ziapp.util;

import com.example.zhan.ziapp.api.BingPicApi;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by ZHan on 2018/3/13.
 */

public class Utility {

    //解析和处理服务器返回的bingpic数据
    public static BingPicApi handlePicResponse(String response){
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("images");
            String picContent = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(picContent,BingPicApi.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
