package com.gachon.app.main;

/**
 * Created by garyNoh on 17. 4. 22..
 *
 * 초기화면
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.gachon.app.R;

/*
첫 화면
 */
public class SplashActivity extends Activity {
    public static final String TAG = "CodingStarter" ;
    public static final int SPLASH_PERIOD = 3000;

    Handler mHandler;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //3초 후에 화면 종료
        mHandler = new Handler();
        mHandler.postDelayed(new SplashHandler(), SPLASH_PERIOD);
    }

    private class SplashHandler implements Runnable{
        public void run() {
            //startActivity(new Intent(getApplication(), MainActivity.class));
            startActivity(new Intent(getApplication(), LoginActivity.class));
            SplashActivity.this.finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

}
