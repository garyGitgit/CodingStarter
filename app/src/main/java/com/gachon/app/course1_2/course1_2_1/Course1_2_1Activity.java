package com.gachon.app.course1_2.course1_2_1;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.ImageView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.gachon.app.R;
import com.gachon.app.helper.MyViewPager;
import com.gachon.app.helper.PageHelper;
import com.gachon.app.helper.ViewFactoryCS;

public class Course1_2_1Activity extends AppCompatActivity implements ViewFactoryCS.onGoNext, ViewFactoryCS.onGoPrevious {
    MyViewPager viewPager;
    ImageView[] progressImageViewList;
    Button buttonGoNext;
    RoundCornerProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_g_page_container);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (MyViewPager) findViewById(R.id.page_container);
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        viewPager.setCurrentItem(0);
        viewPager.setPagingEnabled(false);

        progressBar = (RoundCornerProgressBar)findViewById(R.id.course_round_progress);
        progressBar.setProgress(1.0f);
    }


    @Override
    public void onPressNext() {
        int thisPage = viewPager.getCurrentItem();

        if (thisPage < PageHelper.courseStepNum-1) {
            viewPager.setCurrentItem(++thisPage);

            PageHelper.setProgressColor(progressBar, thisPage, getApplicationContext());
        }
        //액티비티 종료
        else {
            //finish();
        }
    }

    @Override
    public void onPressPrev() {
        int thisPage = viewPager.getCurrentItem();

        if (thisPage > 0) {
            viewPager.setCurrentItem(--thisPage);
            PageHelper.setProgressColor(progressBar, thisPage, getApplicationContext());
        }
        //액티비티 종료
        else {
            finish();
        }
    }

    private class PagerAdapter extends FragmentStatePagerAdapter {
        public PagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new Course1_2_1Step0();
                case 1:
                    return new Course1_2_1Step1();
                case 2:
                    return new Course1_2_1Step2();
                case 3:
                    return new Course1_2_1Step3();
                case 4:
                    return new Course1_2_1Step4();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return PageHelper.courseStepNum;
        }
    }
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
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
