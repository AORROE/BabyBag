package com.hwt.babybag.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {
    private static RetrofitFactory retrofitFactory;
    private static IHttpInterface httpRequest;

    private RetrofitFactory(){
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.202.1.93:8080/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
        httpRequest = retrofit.create(IHttpInterface.class);
    }

    public static RetrofitFactory getRetrofiInstace(){
        if(retrofitFactory == null){
            synchronized (RetrofitFactory.class){
                if(retrofitFactory == null){
                    retrofitFactory = new RetrofitFactory();
                }
            }
        }
        return retrofitFactory;
    }

    public IHttpInterface Api(){
        return httpRequest;
    }
}
