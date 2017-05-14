package com.gachon.app.course1_1.course1_1_2;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.gachon.app.R;
import com.gachon.app.helper.PageHelper;
import com.gachon.app.helper.ViewFactoryCS;


/**
 * course 1-1 데이터 타입 / 변수 / 초기화
 * step 1 : 신발장을 통한 비유
 */
public class Course1_1_2Step1 extends Fragment {
    //항상 추가
    View root; // 부모 액티비티
    ViewFactoryCS viewFactory;

    // Required empty public constructor
    public Course1_1_2Step1() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //항상 추가
        root = inflater.inflate(R.layout.fragment_g_step1, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //최상단 루트 레이아웃
        LinearLayout layout = (LinearLayout) root.findViewById(R.id.fragment_g_step1);
        viewFactory = new ViewFactoryCS(layout);


        LinearLayout imageCard = viewFactory.createCard(1.0f, Color.WHITE, false, new int[]{0,0,0, PageHelper.defaultMargin});

        LinearLayout textCard1 = viewFactory.createCard(1.0f, Color.WHITE, true, new int[]{0,0,0, PageHelper.defaultMargin});
        LinearLayout textCard2 = viewFactory.createCard(1.0f, Color.WHITE, true, new int[]{0,0,0, PageHelper.defaultMargin});
        LinearLayout textCard3 = viewFactory.createCard(1.0f, Color.WHITE, true, new int[]{0,0,0, PageHelper.defaultMargin});

        //이미지 자료 추가
        //viewFactory.addImage(getResources().getDrawable(R.drawable.shoebox), imageCard);
        viewFactory.addImage(getResources().getDrawable(R.drawable.shoe), imageCard);

        viewFactory.addSimpleText("할당이란\n" +
                "'할당' 이라는 말은 변수에 값을 넣는 것을 의미한다. 예를 들어, '변수 num 에 10을 할당한다' 와 같이 표현한다.", 18, textCard1);

        viewFactory.addSimpleText("할당 연산자 '='\n" +
                "변수 = 값", 18, textCard2);

        viewFactory.addSimpleText("변수와 할당\n" +
                "상수가 아니라 변수이기 때문에 한 번 할당한 값을 새로 할당할 수 있다.", 18, textCard3);




    }
}
