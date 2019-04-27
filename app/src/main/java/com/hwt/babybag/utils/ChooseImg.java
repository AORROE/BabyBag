package com.hwt.babybag.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.ViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.hwt.babybag.MyApplication;
import com.hwt.babybag.R;
import com.hwt.babybag.bean.BaseEntity;
import com.hwt.babybag.bean.UserInfo;
import com.hwt.babybag.network.RetrofitFactory;
import com.hwt.babybag.ui.IImagePicker;
import com.qingmei2.rximagepicker.core.RxImagePicker;
import com.qingmei2.rximagepicker.entity.Result;

import java.io.File;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;

public class ChooseImg {

    private static ChooseImg chooseImg;

    public static ChooseImg getInstance(){
        if(chooseImg == null){
            return new ChooseImg();
        }
        return chooseImg;
    }


    /**
     * 选择图片
     * @param view
     */
    @SuppressLint("CheckResult")
    public void chooserImg(View view, Context context,MyCallBack callBack){
        RxImagePicker.INSTANCE.create(IImagePicker.class)
                .openGallery(context)
                .subscribe(new Consumer<Result>() {
                    @Override
                    public void accept(Result result) throws Exception {
                        Uri uri = result.getUri();
                        switch (view.getId()){
                            case R.id.header_img:
                            case R.id.user_avatar:
                            case R.id.cover_img:
                            case R.id.child_avatar:
                            case R.id.found_img:
                                Glide.with(context).load(uri).into((ImageView) view);
                                uploadImg(uri,callBack);
                                break;
                            case R.id.header_back:
                                SharedPreferences sp = MyApplication.getContextObj().getSharedPreferences("UserPreferences",Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putString("imageUrl", String.valueOf(uri));
                                editor.commit();


                                Glide.with(context).load(uri)
                                        .into(new ViewTarget<View,Drawable>(view) {
                                            @Override
                                            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                                                this.view.setBackground(resource.getCurrent());
                                            }
                                        });
                                break;
                        }
                        Log.i(MyApplication.TAG, "accept: "+uri);
                    }
                });
    }

    public interface MyCallBack{
        void SuccessCallBack(String imgUrl);
        void failCallBack(int code);
    }

    private void uploadImg(Uri uri,MyCallBack callBack){
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        File file = new File(GetPathFromUri.getPath(MyApplication.getContextObj(),uri));
        RequestBody body= RequestBody.create(MediaType.parse("multipart/form-data"),file);
//        builder.addFormDataPart(key, value);//传入服务器需要的key，和相应value值
        builder.addFormDataPart("file",file.getName(),body); //添加图片数据，body创建的请求体
        RetrofitFactory.getRetrofiInstace().Api()
                .uploadImg(builder.build().part(0))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseEntity<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseEntity<String> stringBaseEntity) {
                        Log.i(MyApplication.TAG, "onNext: "+stringBaseEntity.getResult());
                        if(stringBaseEntity.getStatus() == 1){
                            callBack.SuccessCallBack(stringBaseEntity.getResult());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
