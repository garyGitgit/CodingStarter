package com.coconutlab.app.course1_1.course1_1_2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.coconutlab.app.R;
import com.coconutlab.app.helper.MyViewPager;
import com.coconutlab.app.helper.PageHelper;
import com.coconutlab.app.helper.ViewFactoryCS;

public class Course1_1_2Activity extends AppCompatActivity implements ViewFactoryCS.onGoNext, ViewFactoryCS.onGoPrevious {

    /** CourseX_X_XActivity 가 공통으로 공유 **/
    MyViewPager viewPager;
    RoundCornerProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_g_page_container);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //viewpager 설정
        viewPager = (MyViewPager) findViewById(R.id.page_container);
        viewPager.setAdapter(new Course1_1_2Activity.PagerAdapter(getSupportFragmentManager()));
        viewPager.setCurrentItem(0);
        viewPager.setPagingEnabled(false);

        //진행상황
        progressBar = (RoundCornerProgressBar) findViewById(R.id.course_round_progress);
        progressBar.setProgress(1.0f);
    }


    private class PagerAdapter extends FragmentStatePagerAdapter {
        public PagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            switch (position) {
                //이 부분이 dynamic 하게 fragment 를 생성을 해야함
                case 0:
                    return new Course1_1_2Step0();
                case 1:
                    return new Course1_1_2Step1();
                case 2:
                    return new Course1_1_2Step2();
                case 3:
                    return new Course1_1_2Step3();
                case 4:
                    return new Course1_1_2Step4();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return PageHelper.courseStepNum;
        }
    }


    /**
     * onPressNext 가 call 이 되었을 때 어떤 동작을 할지 구현
     */
    @Override
    public void onPressNext() {
        int thisPage = viewPager.getCurrentItem();

        //courseStepNum = 5
        //스텝별 페이지 넘기기
        if (thisPage < PageHelper.courseStepNum-1) {
            viewPager.setCurrentItem(++thisPage); //현재 페이지에서 다음 페이지로 넘어간다
            PageHelper.setProgressColor(progressBar, thisPage, getApplicationContext());
        }
        //액티비티 종료
        else {
            //finish();
        }
    }

    /**
     * onPressPrev 가 call 이 되었을 때 어떤 동작을 할지 구현
     */
    @Override
    public void onPressPrev() {
        int thisPage = viewPager.getCurrentItem();

        if (thisPage > 0) {
            viewPager.setCurrentItem(--thisPage); //현재 페이지에서 이전 페이지로 돌아간다
            PageHelper.setProgressColor(progressBar, thisPage, getApplicationContext());
        }
        //액티비티 종료
        else {
            finish();
        }
    }

    /**
     * 뒤로가기버튼이 눌리면 종료한다
     */
    @Override
    public void onBackPressed() {
        MaterialDialog.Builder confirmDialog = new MaterialDialog.Builder(this)
                .title("CodingStarter")
                .content("종료하시겠습니까?")
                .positiveText("예")
                .negativeText("아니오");

        confirmDialog.onPositive(
                new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        // TODO
                        finish();
                    }
                });
        confirmDialog.show();
    }

}
