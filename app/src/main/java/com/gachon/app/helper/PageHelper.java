package com.gachon.app.helper;

import android.content.Context;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;

/**
 * Created by garyNoh on 2017. 5. 10..
 */

public class PageHelper {

    public static final int courseStepNum = 5;
    public static int defaultMargin = 20;
    public static int headerCardMargin = 10;
    public static int questionTextSize = 15;
    public static int cardOpenDelay = 1500;
    public static String endingString = "{ ; }";

    /**
     *
//     * @param progressImageViewList
     * @param finish
     */
    public static void setProgressColor(RoundCornerProgressBar progressBar, int finish, Context context){
        progressBar.setProgress(finish+1);
//        for(int i = 0; i <= finish; i++){
//            progressImageViewList[i].setImageDrawable(context.getResources().getDrawable(R.drawable.course_progress_check_blue));
//        }
//        for(int i = finish+1; i < courseStepNum; i++){
//            //progressImageViewList[i].setBackgroundColor(Color.WHITE);
//            progressImageViewList[i].setImageDrawable(context.getResources().getDrawable(R.drawable.course_progress_check_gray));
//        }
    }
}
