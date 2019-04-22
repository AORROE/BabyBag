package com.hwt.babybag.ui;

import android.content.Context;

import com.qingmei2.rximagepicker.entity.Result;
import com.qingmei2.rximagepicker.entity.sources.Camera;
import com.qingmei2.rximagepicker.entity.sources.Gallery;


import io.reactivex.Observable;

public interface IImagePicker {
    @Gallery
    Observable<Result> openGallery(Context context);

    @Camera
        // 打开相机拍照
    Observable<Result> openCamera(Context context);
}
