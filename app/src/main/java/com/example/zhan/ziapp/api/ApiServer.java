package com.example.zhan.ziapp.api;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by ZHan on 2018/3/14.
 */

public interface ApiServer {
    @GET("HPImageArchive.aspx?format=js&idx=0&n=1")
    Observable<BingPicApi> getMessage();
}
