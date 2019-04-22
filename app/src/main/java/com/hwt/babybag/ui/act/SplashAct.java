package com.hwt.babybag.ui.act;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hwt.babybag.MainActivity;
import com.hwt.babybag.R;

import java.lang.ref.WeakReference;

public class SplashAct extends AppCompatActivity {
    private long waitTime = 3;

    private TextView countDown;

    private Handler handler = new MyHandler(this);

    private Runnable mTimker = new Runnable() {
        @Override
        public void run() {
            final Intent intent;
            intent = new Intent(SplashAct.this, LoginAct.class);
            if(waitTime == 0){
                waitTime = 3;
                handler.removeCallbacks(mTimker);

                startActivity(intent);
                finish();
            }else {
                handler.postDelayed(this,1000);
                countDown.setText("跳过"+(waitTime--) + "秒");
                countDown.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        handler.removeCallbacks(mTimker);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        countDown = findViewById(R.id.countDown);
        start();
    }

    private void start () {
        handler.postDelayed(mTimker,0);
    }


    private static class MyHandler extends Handler{
        private final WeakReference<SplashAct> splashActWeakReference;
        private MyHandler(SplashAct splashActWeakReference) {
            this.splashActWeakReference = new WeakReference<SplashAct>(splashActWeakReference);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    }
}
