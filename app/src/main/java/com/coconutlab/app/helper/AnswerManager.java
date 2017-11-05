package com.coconutlab.app.helper;

import android.content.Context;
import android.os.Vibrator;

/**
 * Created by garyNoh on 2017. 6. 5..
 */

public class AnswerManager {

    final int delay = 500;
    Context context;
    public AnswerManager(Context context){
        this.context = context;
    }

    public void vibrate(){
        Vibrator vibe = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(delay);
    }
}
