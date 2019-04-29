package com.hwt.babybag.network;

import com.hwt.babybag.adapter.MineItem;
import com.hwt.babybag.adapter.VideoItem;
import com.hwt.babybag.bean.BaseEntity;
import com.hwt.babybag.bean.ChildInfoBean;
import com.hwt.babybag.bean.MissionBean;
import com.hwt.babybag.bean.UserInfo;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface IHttpInterface {

    @POST("user/login")
    Observable<BaseEntity<UserInfo>> login2(@Body Map<String,Object> params);

    @POST("user/register")
    Observable<BaseEntity> register(@Body Map<String,Object> params);

    @POST("user/updateUser")
    Observable<BaseEntity> updateUser(@Body UserInfo params);

    @POST("user/modifyPassword")
    Observable<BaseEntity> modifyPassword(@Body Map<String,Object> params);

    @POST("user/findOne")
    Observable<BaseEntity<UserInfo>> findOne(@Body Map<String,Object> params);

    @POST("child/findChildById")
    Observable<BaseEntity<ChildInfoBean>> findChildById(@Body ChildInfoBean ChildInfoBean);

    @POST("child/addChild")
    Observable<BaseEntity> addChild(@Body ChildInfoBean params);

    @POST("child/deleteChildById")
    Observable<BaseEntity> deleteChildById(@Body ChildInfoBean params);

    @POST("application/commitApplication")
    Observable<BaseEntity> commitApplication(@Body Map<String,Object> params);

    @POST("mission/getAllMission")
    Observable<BaseEntity<List<MissionBean>>> getAllMission(@Body Map<String,Object> params);

    @POST("mission/modifyComplete")
    Observable<BaseEntity> modifyComplete(@Body Map<String,Object> params);

    @Multipart
    @POST("user/uploadImg")
    Observable<BaseEntity<String>> uploadImg(@Part MultipartBody.Part multipart);

    @POST("found/getAllFound")
    Observable<BaseEntity<List<MineItem>>> getAllFound(@Body Map<String,Object> params);

    @POST("found/addFound")
    Observable<BaseEntity> addFound(@Body MineItem params);

    @GET("videoList/getAllVideo")
    Observable<BaseEntity<List<VideoItem>>> getAllVideo();

    @POST("videoList/addVideo")
    Observable<BaseEntity> addVideo(@Body VideoItem params);

    @POST("videoList/getRtmpUrl")
    Observable<BaseEntity<String>> getRtmpUrl(@Body Map<String, Object> params);
}
