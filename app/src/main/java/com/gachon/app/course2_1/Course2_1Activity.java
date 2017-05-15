package com.gachon.app.course2_1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.gachon.app.R;
import com.gachon.app.course2_1.course2_1_1.Course2_1_1Activity;
import com.gachon.app.course2_1.course2_1_2.Course2_1_2Activity;
import com.gachon.app.course2_1.course2_1_3.Course2_1_3Activity;
import com.gachon.app.helper.PageHelper;
import com.gachon.app.helper.ViewFactoryCS;

/**
 * course 1_1 최상단 액티비티 : 다른 세부 액티비티로 들어가는 게이트웨이 역할을 한다
 *
 * 세부 액티비티
 *  1) 변수의 기초
 *  2) 변수의 활용
 */

public class Course2_1Activity extends AppCompatActivity {

    ViewFactoryCS viewFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_g_root);

        LinearLayout layout = (LinearLayout)findViewById(R.id.activity_course_g_root);
        viewFactory = new ViewFactoryCS(layout);

        //연산자의 기초 카드
        LinearLayout card1 = viewFactory.createCard(1.0f, Color.WHITE, true, new int[]{0,0,0, PageHelper.defaultMargin});
        viewFactory.addSimpleText("조건문 시작하기", 30, card1);
        //누르면 연결
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "touch", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Course2_1Activity.this, Course2_1_1Activity.class);
                startActivity(intent);
            }
        });

        LinearLayout card2 = viewFactory.createCard(1.0f, Color.WHITE, true, new int[]{0,0,0, PageHelper.defaultMargin});
        viewFactory.addSimpleText("조건문 if", 30, card2);
        //누르면 연결
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Course2_1Activity.this, Course2_1_2Activity.class);
                startActivity(intent);
            }
        });

        LinearLayout card3 = viewFactory.createCard(1.0f, Color.WHITE, true, new int[]{0,0,0, PageHelper.defaultMargin});
        viewFactory.addSimpleText("조건문 if-else 체인", 30, card3);
        //누르면 연결
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Course2_1Activity.this, Course2_1_3Activity.class);
                startActivity(intent);
            }
        });



    }


}
