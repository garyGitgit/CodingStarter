package com.coconutlab.app.helper;

import android.app.Application;

import com.coconutlab.app.R;
import com.parse.Parse;

/**
 * Created by garyNoh on 2017. 6. 5..
 */

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(getApplicationContext());

        //Parse 초기화
        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId(getString(R.string.parse_app_id))
                .clientKey(null)
                .server(getString(R.string.parse_server_url))   // '/' important after 'parse'
                .build());
    }


}
