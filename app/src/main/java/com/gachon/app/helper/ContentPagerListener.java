package com.gachon.app.helper;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import com.gachon.app.R;

/**
 * Created by garyNoh on 2017. 6. 6..
 */

public class ContentPagerListener implements View.OnClickListener{
    Activity activity;
    MyViewPager[] viewPagers = null;
    MainPagerAdapter[] pagerAdapters = null;
    int numOfCards = 0;
    int currentCardNum = 0;


    public ContentPagerListener(Activity activity){
        //나머지는 null
        this.activity = activity;
    }

    //카드가 있을 때
    public ContentPagerListener(MyViewPager[] viewPagers, MainPagerAdapter[] pagerAdapters, Activity activity){
        //this.type = type;
        this.viewPagers = viewPagers;
        this.pagerAdapters = pagerAdapters;
        this.activity = activity;
        this.numOfCards = viewPagers.length;

    }


    @Override
    public void onClick(View v) {
        int thisPage;
        int pageNum;

        ViewFactoryCS.onGoPrevious onGoPrev= (ViewFactoryCS.onGoPrevious)activity;
        ViewFactoryCS.onGoNext onGoNext = (ViewFactoryCS.onGoNext)activity;


        switch (v.getId()){
            case R.id.goPrevious:
                //카드가 하나도 없을 떄, 이전 페이지로 넘어감
                if(viewPagers == null){ onGoPrev.onPressPrev(); return;}


                thisPage = viewPagers[currentCardNum].getCurrentItem();
                Log.e("gary", "current card num : " + Integer.toString(currentCardNum) + "this page num : " + Integer.toString(thisPage));

                if (thisPage == 0) {
                    //가장 첫번째 카드면 go previous
                    if(currentCardNum == 0){
                        //ViewFactoryCS.onGoPrevious onGoPrev= (ViewFactoryCS.onGoPrevious)activity;
                        onGoPrev.onPressPrev();
                    }
                    //아니면 이전 카드로 이동
                    else{
                        currentCardNum--;
                    }
                }
                //0 페이지 이상일 떄는 하나씩 페이지를 뒤로 돌리고
                else{
                    viewPagers[currentCardNum].setCurrentItem(--thisPage);
                }
                break;
            case R.id.goNext :
                //카드가 하나도 없을 떄, 다음페이지로 넘어감
                if(viewPagers == null){ onGoNext.onPressNext(); return;}


                thisPage = viewPagers[currentCardNum].getCurrentItem();
                Log.e("gary", "current card num : " + Integer.toString(currentCardNum) + "this page num : " + Integer.toString(thisPage));
                //pageradapter 를 동해서 page 의 갯수를 가져온다
                pageNum = pagerAdapters[currentCardNum].getCount();
                if (thisPage < pageNum-1) {
                    viewPagers[currentCardNum].setCurrentItem(++thisPage);
                }
                else{

                    //만약에 마지막 카드라면 다음 페이지로 넘어감
                    if(currentCardNum == numOfCards-1){
                        //Toast.makeText(activity.getApplicationContext(), "next", Toast.LENGTH_SHORT).show();
                        //ViewFactoryCS.onGoNext onGoNext = (ViewFactoryCS.onGoNext)activity;
                        onGoNext.onPressNext();
                    }
                    //그렇지 않으면 다음 카드로 넘어감
                    else currentCardNum++;
                }
                break;
        }


//        switch (type){
//            // 0 은 prev일 떄
//            case 0:
//                thisPage = viewPager.getCurrentItem();
//
//                if (thisPage > 0) {
//                    viewPager.setCurrentItem(--thisPage);
//                } else {
//                    ViewFactoryCS.onGoPrevious onGoPrev = (ViewFactoryCS.onGoPrevious) activity;
//                    onGoPrev.onPressPrev();
//                }
//                break;
//            // 1은 next 일 때
//            case 1:
//                thisPage = viewPager.getCurrentItem();
//                pageNum = pagerAdapter.getCount();
//                if (thisPage < pageNum - 1) {
//                    viewPager.setCurrentItem(++thisPage);
//                } else {
//                    Toast.makeText(activity.getApplicationContext(), "next", Toast.LENGTH_SHORT).show();
//                    ViewFactoryCS.onGoNext onGoNext = (ViewFactoryCS.onGoNext) activity;
//                    onGoNext.onPressNext();
//                }
//                break;
//            //여러개가 들어왔을 떄 prev
//            case 2:
//                thisPage = viewPagers[currentCardNum].getCurrentItem();
//                Log.e("gary", "current card num : " + Integer.toString(currentCardNum) + "this page num : " + Integer.toString(thisPage));
//
//                if (thisPage == 0) {
//                    //가장 첫번째 카드면 go previous
//                    if(currentCardNum == 0){
//                        ViewFactoryCS.onGoPrevious onGoPrev= (ViewFactoryCS.onGoPrevious)activity;
//                        onGoPrev.onPressPrev();
//                    }
//                    //아니면 이전 카드로 이동
//                    else{
//                        currentCardNum--;
//                    }
//                }
//                //0 페이지 이상일 떄는 하나씩 페이지를 뒤로 돌리고
//                else{
//                    viewPagers[currentCardNum].setCurrentItem(--thisPage);
//                }
//                break;
//            //여러개가 들어왔을 때 next
//            case 3:
//                thisPage = viewPagers[currentCardNum].getCurrentItem();
//                Log.e("gary", "current card num : " + Integer.toString(currentCardNum) + "this page num : " + Integer.toString(thisPage));
//                //pageradapter 를 동해서 page 의 갯수를 가져온다
//                pageNum = pagerAdapters[currentCardNum].getCount();
//                int numCards = viewPagers.length;
//                if (thisPage < pageNum-1) {
//                    viewPagers[currentCardNum].setCurrentItem(++thisPage);
//                }
//                else{
//
//                    //만약에 마지막 카드라면 다음 페이지로 넘어감
//                    if(currentCardNum == numCards-1){
//                        //Toast.makeText(activity.getApplicationContext(), "next", Toast.LENGTH_SHORT).show();
//                        ViewFactoryCS.onGoNext onGoNext = (ViewFactoryCS.onGoNext)activity;
//                        onGoNext.onPressNext();
//                    }
//                    //그렇지 않으면 다음 카드로 넘어감
//                    else currentCardNum++;
//                }
//                break;
//            //슬라이드 카드가 없을 때 prev
//            case 4:
//                ViewFactoryCS.onGoPrevious onGoPrev= (ViewFactoryCS.onGoPrevious)activity;
//                onGoPrev.onPressPrev();
//                break;
//            //슬라이드 카드가 없을 때 next
//            case 5:
//                //Toast.makeText(activity.getApplicationContext(), "next", Toast.LENGTH_SHORT).show();
//                ViewFactoryCS.onGoNext onGoNext = (ViewFactoryCS.onGoNext)activity;
//                onGoNext.onPressNext();
//                break;
//        }
    }
}
