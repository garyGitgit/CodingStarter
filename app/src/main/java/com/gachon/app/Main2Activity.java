package com.gachon.app;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main2);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//    }
ViewPager course1ViewPager;
    static int courseStepNum = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                int thisPage = course1ViewPager.getCurrentItem();
                if(thisPage < 4) {
                    Toast.makeText(Main2Activity.this, "성공!", Toast.LENGTH_SHORT).show();
                    course1ViewPager.setCurrentItem(thisPage + 1);
                }
                else
                    Toast.makeText(Main2Activity.this, "마지막 단계입니다", Toast.LENGTH_SHORT).show();
            }
        });


        course1ViewPager = (ViewPager)findViewById(R.id.course1ViewPager);
        course1ViewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        course1ViewPager.setCurrentItem(0);

    }


    private class PagerAdapter extends FragmentStatePagerAdapter
    {
        public PagerAdapter(android.support.v4.app.FragmentManager fm)
        {
            super(fm);
        }
        @Override
        public android.support.v4.app.Fragment getItem(int position)
        {
            switch(position)
            {
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
        public int getCount()
        {
            return courseStepNum;
        }
    }

}
