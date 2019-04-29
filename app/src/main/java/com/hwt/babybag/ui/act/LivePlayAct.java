package com.hwt.babybag.ui.act;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.view.View;
import android.widget.Button;

import com.hwt.babybag.MyApplication;
import com.hwt.babybag.R;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXLivePushConfig;
import com.tencent.rtmp.TXLivePusher;
import com.tencent.rtmp.ui.TXCloudVideoView;

public class LivePlayAct extends AppCompatActivity implements View.OnClickListener {
    private TXLivePusher txLivePusher;
    private TXLivePushConfig txLivePushConfig;
    private TXCloudVideoView txCloudVideoView;

    private Button switchCamera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_play);
        txCloudVideoView = findViewById(R.id.video_view);
        txLivePusher = new TXLivePusher(this);
        txLivePushConfig = new TXLivePushConfig();

        txLivePushConfig.enableScreenCaptureAutoRotate(true);
        txLivePusher.setConfig(txLivePushConfig);
        Intent intent = getIntent();

//        String rtmpUrl = "rtmp://47170.livepush.myqcloud.com/live/12351238?txSecret=83fc4d1774e44cdde496e1ab93ce43f9&txTime=5CC47C7F";
//        String rtmpUrl = "rtmp://48162.livepush.myqcloud.com/live/64674994?txSecret=c539ab8ec0e7d1f914f09cdb3d2a7297&txTime=5CC71D49";
        String rtmpUrl = intent.getStringExtra("rtmpUrl");
        Log.i(MyApplication.TAG, "onCreate: "+rtmpUrl);
        txLivePusher.startPusher(rtmpUrl);
        txLivePusher.startCameraPreview(txCloudVideoView);

        // 竖屏状态，本地渲染相对正方向的角度为0。
        txLivePusher.setRenderRotation(0);

        switchCamera = findViewById(R.id.Camera);
        switchCamera.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Camera:
                txLivePusher.switchCamera();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        txLivePusher.resumePusher();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // 自动旋转打开，Activity随手机方向旋转之后，只需要改变推流方向
        int mobileRotation = this.getWindowManager().getDefaultDisplay().getRotation();
        int pushRotation = TXLiveConstants.VIDEO_ANGLE_HOME_DOWN;
        switch (mobileRotation) {
            case Surface.ROTATION_0:
                pushRotation = TXLiveConstants.VIDEO_ANGLE_HOME_DOWN;
                break;
            case Surface.ROTATION_90:
                pushRotation = TXLiveConstants.VIDEO_ANGLE_HOME_RIGHT;
                break;
            case Surface.ROTATION_270:
                pushRotation = TXLiveConstants.VIDEO_ANGLE_HOME_LEFT;
                break;
            default:
                break;
        }
        //通过设置config是设置生效（可以不用重新推流，腾讯云是少数支持直播中热切换分辨率的云商之一）
        txLivePusher.setRenderRotation(0);
        txLivePushConfig.setHomeOrientation(pushRotation);
        txLivePusher.setConfig(txLivePushConfig);
    }
}
