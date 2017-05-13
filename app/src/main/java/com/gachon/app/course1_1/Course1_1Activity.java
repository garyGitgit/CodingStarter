package com.gachon.app.course1_1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.gachon.app.R;
import com.gachon.app.course1_1.course1_1_1.Course1_1_1Activity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course1_1);

        LinearLayout layout = (LinearLayout)findViewById(R.id.activity_course1_1);
        viewFactory = new ViewFactoryCS(layout);

        //변수의 기초 카드
        LinearLayout card1 = viewFactory.createCard(1.0f, Color.WHITE, true, new int[]{0,0,0, PageHelper.defaultMargin});
        viewFactory.addSimpleText("변수의 기초", 30, card1);
        //누르면 연결
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Course1_1Activity.this, Course1_1_1Activity.class);
                startActivity(intent);
            }
        });

        //변수의 활용 카드
        LinearLayout card2 = viewFactory.createCard(1.0f, Color.WHITE, true, new int[]{0,0,0, PageHelper.defaultMargin});
        viewFactory.addSimpleText("변수의 활용", 30, card2);

    }


}
