package com.gachon.app;

/**
 * Created by garyNoh on 17. 1. 26..
 *
 * 초기화면
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class SplashActivity extends Activity {
    //상수
    public static final String TAG = "bisecu/Splash/" ; //디버깅용 태그 (최대23글자만 허용)
    public static final int SPLASH_PERIOD = 2000;

    //변수
    Handler mHandler;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        /* 17.1.16 : 혹시 모를 경우를 대비해서 초기화 */
//        delete();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //3초 후에 화면 종료
        mHandler = new Handler();
        mHandler.postDelayed(new SplashHandler(), SPLASH_PERIOD);
    }

    private class SplashHandler implements Runnable{
        public void run() {
            //TODO : go to main Activity for test
            //scan activity 실행하고, 초기 activity 종료
            startActivity(new Intent(getApplication(), MainActivity.class));
            SplashActivity.this.finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

}
