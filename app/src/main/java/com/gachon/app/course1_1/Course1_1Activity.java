package com.gachon.app.course1_1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.gachon.app.R;
import com.gachon.app.course1_1.course1_1_1.Course1_1_1Activity;
import com.gachon.app.course1_1.course1_1_2.Course1_1_2Activity;
import com.gachon.app.helper.PageHelper;
import com.gachon.app.helper.ViewFactoryCS;

/**
 * course 1_1 최상단 액티비티 : 다른 세부 액티비티로 들어가는 게이트웨이 역할을 한다
 *
 * 세부 액티비티
 *  1) 변수의 기초
 *  2) 변수의 활용
 */

public class Course1_1Activity extends AppCompatActivity {

    ViewFactoryCS viewFactory;
    LinearLayout card1, card2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_g_root);


        //int disabledColor = getResources().getColor(android.R.color.darker_gray);

        LinearLayout layout = (LinearLayout)findViewById(R.id.activity_course_g_root);
        viewFactory = new ViewFactoryCS(layout);


        //변수의 기초 카드
        card1 = viewFactory.createCard(0.0f, Color.WHITE, true, new int[]{0,0,0, PageHelper.defaultMargin});
        viewFactory.addSimpleText("변수의 기초", 30, card1);
        //누르면 연결
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Course1_1Activity.this, Course1_1_1Activity.class);
//                card1.setBackgroundColor(Color.WHITE);
                startActivity(intent);
            }
        });

        viewFactory.addSpace(1.0f);

        //변수의 활용 카드
        card2 = viewFactory.createCard(0.0f, Color.WHITE, true, new int[]{0,0,0, PageHelper.defaultMargin});
        viewFactory.addSimpleText("변수의 활용", 30, card2);

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Course1_1Activity.this, Course1_1_2Activity.class);
//                card2.setBackgroundColor(Color.WHITE);
                startActivity(intent);
            }
        });



        //폰트적용
//        Typekit.getInstance()
//                .addNormal(Typekit.createFromAsset(this, "font1.ttf"))
//                .addBold(Typekit.createFromAsset(this, "font1.ttf"));
    }

    //폰트 적용 : 작동안함
//    @Override
//    protected void attachBaseContext(Context newBase) {
//        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
//
//    }




}
