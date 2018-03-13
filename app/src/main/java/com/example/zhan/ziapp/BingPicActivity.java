package com.example.zhan.ziapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.zhan.ziapp.api.ApiServer;
import com.example.zhan.ziapp.api.BingPicApi;

import rx.Observer;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ZHan on 2018/3/14.
 */

public class BingPicActivity extends AppCompatActivity{
    private static final String TAG = "BingPicActivity";

    private ImageView bing_pic_img;
    private TextView bing_pic_txt;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bing_activity);
        bing_pic_img = findViewById(R.id.bing_pic_img);
        bing_pic_txt = findViewById(R.id.bing__pic_txt);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
        if (isConn(this)){
            init();
        }else {
            bing_pic_img.setImageResource(R.drawable.bixin);
            bing_pic_txt.setText("");
            Toast.makeText(this,"您没有网络连接哦！！！比心😘",Toast.LENGTH_LONG).show();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(5000);
                }catch (Exception e){
                    e.printStackTrace();
                }
                Intent intent = new Intent(BingPicActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        }).start();
    }

    private void init() {
        GankRetrofit.getRetrofit()
                .create(ApiServer.class)
                .getMessage()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BingPicApi>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BingPicApi bingPicApi) {
                        String txt = bingPicApi.getImages().get(0).getCopyright();
                        String url = "http://s.cn.bing.net" + bingPicApi.getImages().get(0).getUrl();
                        Glide.with(BingPicActivity.this).load(url).into(bing_pic_img);
                        bing_pic_txt.setText(txt);
                    }
                });
    }
    private static boolean isConn(Context context){
        boolean bisConnFlag = false;
        ConnectivityManager conManager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo network = conManager.getActiveNetworkInfo();
        if (network != null){
            bisConnFlag = conManager.getActiveNetworkInfo().isAvailable();
        }
        return bisConnFlag;
    }
}
