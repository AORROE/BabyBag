package com.hwt.babybag.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.ViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.hwt.babybag.R;
import com.hwt.babybag.ui.IImagePicker;
import com.qingmei2.rximagepicker.core.RxImagePicker;
import com.qingmei2.rximagepicker.entity.Result;

import io.reactivex.functions.Consumer;

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
                                Glide.with(context).load(uri).into((ImageView) view);
                                callBack.SuccessCallBack(1);
                                break;
                            case R.id.header_back:
                                Glide.with(context).load(uri)
                                        .into(new ViewTarget<View,Drawable>(view) {
                                            @Override
                                            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                                                this.view.setBackground(resource.getCurrent());
                                            }
                                        });
                                break;
                        }
                    }
                });
    }

    public interface MyCallBack{
        void SuccessCallBack(int code);
        void failCallBack(int code);
    }
}
