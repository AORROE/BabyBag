package com.hwt.babybag.ui.act;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hwt.babybag.R;
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

        String rtmpUrl = "rtmp://47170.livepush.myqcloud.com/live/12351238?txSecret=83fc4d1774e44cdde496e1ab93ce43f9&txTime=5CC47C7F";
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
}
