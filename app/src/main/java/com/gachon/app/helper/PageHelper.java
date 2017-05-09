package com.gachon.app.helper;

import android.graphics.Color;
import android.widget.ImageView;

/**
 * Created by garyNoh on 2017. 5. 10..
 */

public class PageHelper {

    public static final int courseStepNum = 4;
    public static int defaultMargin = 20;

    public static void setProgressColor(ImageView[] progressImageViewList, int finish){
        for(int i = 0; i <= finish; i++){
            progressImageViewList[i].setBackgroundColor(Color.GREEN);
        }
        for(int i = finish+1; i < courseStepNum; i++){
            progressImageViewList[i].setBackgroundColor(Color.WHITE);
        }
    }
}
