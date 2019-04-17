package com.hwt.babybag.ui.act;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.hwt.babybag.R;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

public class VideoAct extends AppCompatActivity {
    private JzvdStd jzvdStd;
    private ImageView noCompleteImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setStatusBarFullTransparent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        videoView();
    }

    public void videoView(){
        jzvdStd = findViewById(R.id.video_player);
        noCompleteImg = findViewById(R.id.nocomplete_img);

        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(VideoAct.this).load(R.drawable.no_complete)
                .apply(options).into(noCompleteImg);

        jzvdStd.setUp("http://www.jmzsjy.com/UploadFile/微课/地方风味小吃——宫廷香酥牛肉饼.mp4",
                "地方风味小吃——宫廷香酥牛肉饼",Jzvd.SCROLL_AXIS_NONE);
        jzvdStd.thumbImageView.setImageResource(R.drawable.icon_header);
        jzvdStd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JzvdStd.startFullscreen(VideoAct.this,JzvdStd.class,
                        "http://www.jmzsjy.com/UploadFile/微课/地方风味小吃——宫廷香酥牛肉饼.mp4",
                        "地方风味小吃——宫廷香酥牛肉饼");
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(Jzvd.backPress()){
            finish();
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        jzvdStd.startVideo();
        JzvdStd.SAVE_PROGRESS =false;
        JzvdStd.goOnPlayOnResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
        JzvdStd.goOnPlayOnPause();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 全透明
     */
    protected void setStatusBarFullTransparent(){
        if(Build.VERSION.SDK_INT >= 21){
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }else if(Build.VERSION.SDK_INT >= 19){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

}