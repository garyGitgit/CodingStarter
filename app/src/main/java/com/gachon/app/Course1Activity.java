package com.gachon.app;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.Toast;

public class Course1Activity extends AppCompatActivity implements OnGoNextPageInterface{
    ViewPager course1ViewPager;
    static int courseStepNum = 5;
    ImageView[] progressImageViewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course1);

        //progress 상태 표시
        progressImageViewList = new ImageView[5];
        progressImageViewList[0] = (ImageView)findViewById(R.id.course_progress1);
        progressImageViewList[1] = (ImageView)findViewById(R.id.course_progress2);
        progressImageViewList[2] = (ImageView)findViewById(R.id.course_progress3);
        progressImageViewList[3] = (ImageView)findViewById(R.id.course_progress4);
        progressImageViewList[4] = (ImageView)findViewById(R.id.course_progress5);


        //초기 시작은 첫번째 progress 이미지 초기화
        progressImageViewList[0].setBackgroundColor(Color.GREEN);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                int thisPage = course1ViewPager.getCurrentItem();
//
//                if (thisPage < 4) {
//                    Toast.makeText(Course1Activity.this, "성공!", Toast.LENGTH_SHORT).show();
//                    course1ViewPager.setCurrentItem(thisPage + 1);
//                    //지금 페이지 번호에 맞게 progress 배경색을 색칠해준다. 추후에는 색깔을 칠하던가 색깔있는 아이콘을 쓰던가 해야지
//                    for (int i = 0 ; i <= thisPage+1; i++){
//                        progressImageViewList[i].setBackgroundColor(Color.GREEN);
//                    }
//                }
//                else
//                    Toast.makeText(Course1Activity.this, "마지막 단계입니다", Toast.LENGTH_SHORT).show();
//            }
//        });


        course1ViewPager = (ViewPager) findViewById(R.id.course1ViewPager);
        course1ViewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        course1ViewPager.setCurrentItem(0);

    }




    private class PagerAdapter extends FragmentStatePagerAdapter {
        public PagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new Course1Step1Fragment();
                case 1:
                    return new Course1Step2Fragment();
                case 2:
                    return new Course1Step3Fragment();
                case 3:
                    return new Course1Step4Fragment();
                case 4:
                    return new Course1Step5Fragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return courseStepNum;
        }
    }

    //프래그먼트에서 발생한 다음으로 가기 버튼 이벤트 처리
    @Override
    public void onPressGoNext() {
        int thisPage = course1ViewPager.getCurrentItem();

        if (thisPage < 4) {
            Toast.makeText(Course1Activity.this, "성공!", Toast.LENGTH_SHORT).show();
            course1ViewPager.setCurrentItem(thisPage + 1);
            //지금 페이지 번호에 맞게 progress 배경색을 색칠해준다. 추후에는 색깔을 칠하던가 색깔있는 아이콘을 쓰던가 해야지
            for (int i = 0 ; i <= thisPage+1; i++){
                progressImageViewList[i].setBackgroundColor(Color.GREEN);
            }
        }
        else
            Toast.makeText(Course1Activity.this, "마지막 단계입니다", Toast.LENGTH_SHORT).show();

    }

}
