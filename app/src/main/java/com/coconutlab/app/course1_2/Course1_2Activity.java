package com.coconutlab.app.course1_2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.coconutlab.app.R;
import com.coconutlab.app.course1_2.course1_2_1.Course1_2_1Activity;
import com.coconutlab.app.helper.PageHelper;
import com.coconutlab.app.helper.ViewFactoryCS;

/**
 * course 1_1 최상단 액티비티 : 다른 세부 액티비티로 들어가는 게이트웨이 역할을 한다
 *
 * 세부 액티비티
 *  1) 변수의 기초
 *  2) 변수의 활용
 */

public class Course1_2Activity extends AppCompatActivity {

    ViewFactoryCS viewFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_course1_1);
        setContentView(R.layout.activity_course_g_root);

        LinearLayout layout = (LinearLayout)findViewById(R.id.activity_course_g_root);
        viewFactory = new ViewFactoryCS(layout);

        //연산자의 기초 카드
        LinearLayout card1 = viewFactory.createCard(1.0f, Color.WHITE, true, new int[]{0,0,0, PageHelper.defaultMargin});
        viewFactory.addSimpleText("연산자 기초", 30, card1);
        //누르면 연결
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Course1_2Activity.this, Course1_2_1Activity.class);
                startActivity(intent);
            }
        });


    }


}
