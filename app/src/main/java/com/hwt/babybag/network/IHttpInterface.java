package com.hwt.babybag.network;

import com.hwt.babybag.bean.BaseEntity;
import com.hwt.babybag.bean.UserInfo;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IHttpInterface {

    @POST("user/login")
    Observable<BaseEntity<UserInfo>> login2(@Body Map<String,Object> params);

    @POST("user/register")
    Observable<BaseEntity> register(@Body Map<String,Object> params);

    @POST("user/updateUser")
    Observable<BaseEntity> updateUser(@Body Map<String,Object> params);

}
