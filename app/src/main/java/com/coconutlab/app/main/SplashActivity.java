package com.coconutlab.app.main;

/**
 * Created by garyNoh on 17. 4. 22..
 *
 * 초기화면
 */

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import com.coconutlab.app.R;
import com.coconutlab.app.helper.UserManager;
import com.coconutlab.app.helper.WidgetSet;

/*
첫 화면
 */
public class SplashActivity extends Activity {
    public static final String TAG = "CodingStarter" ;
    public static final int SPLASH_PERIOD = 3000;

    Handler mHandler;
    UserManager userManager;
    Bitmap bitmap;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.codingstarter_logo_burned);

        ImageView logo = (ImageView)findViewById(R.id.codingstarter_logo);
        logo.setImageBitmap(bitmap);



        //3초 후에 화면 종료
        mHandler = new Handler();
        mHandler.postDelayed(new SplashHandler(), SPLASH_PERIOD);
        userManager = UserManager.getIntance();
        userManager.init(getApplicationContext());


        //dp 설정
        WidgetSet.setScale(getResources().getDisplayMetrics().density);
    }

    private class SplashHandler implements Runnable{
        public void run() {
            boolean isAutoLogin = userManager.isAutoLogin();
            //자동 로그인인지 확인
            Log.e("gary", Boolean.toString(isAutoLogin));

            //자동로그인이면 바로 main 으로 아니면 login 페이지
            if(isAutoLogin)
                startActivity(new Intent(getApplication(), MainActivity.class));
            else
                startActivity(new Intent(getApplication(), LoginActivity.class));
            SplashActivity.this.finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bitmap.recycle();

    }

}
