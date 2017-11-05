package com.coconutlab.app.course2_1.course2_1_3;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.coconutlab.app.R;
import com.coconutlab.app.helper.PageHelper;
import com.coconutlab.app.helper.ViewFactoryCS;


/**
 * course 1-2 연산자
 * step 1 : 연산자 종류 설명
 */

public class Course2_1_3Step1 extends Fragment {

    View root;
    ViewFactoryCS viewFactory;

    public Course2_1_3Step1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_g_step1, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //최상단 루트 레이아웃
        LinearLayout layout = (LinearLayout) root.findViewById(R.id.fragment_g_step1);
        viewFactory = new ViewFactoryCS(layout);

        viewFactory = new ViewFactoryCS(layout);

        //이미지가 필요할 듯 3개 정도 범위를 나타낼 수 있는
        LinearLayout imageCard = viewFactory.createCard(1.0f, Color.WHITE, true, new int[]{0,0,0,0});
        viewFactory.addImage(getResources().getDrawable(R.drawable.ifelsefiltering), imageCard);


        LinearLayout headerCard = viewFactory.createCard(1.0f, Color.WHITE, true, new int[]{0,0,0, PageHelper.defaultMargin});
        viewFactory.addSimpleText("물을 정화시키는 과정과 유사하다", 20, headerCard);

        LinearLayout textCard1 = viewFactory.createCard(1.0f, Color.WHITE, true, new int[]{0,0,0, PageHelper.defaultMargin});
        viewFactory.addSimpleText("1. 가장 위에서부터 조건을 만족하는지 확인한다", 20, textCard1);

        LinearLayout textCard2 = viewFactory.createCard(1.0f, Color.WHITE, true, new int[]{0,0,0, PageHelper.defaultMargin});
        viewFactory.addSimpleText("2. 조건이 만족하지 않으면 다음 조건으로 넘어간다. ", 20, textCard2);
        viewFactory.addSimpleText("이때 판단하는 조건은 이전 조건은 한 번 거쳤기 때문에 고려하지않는다. ", 20, textCard2);

        LinearLayout textCard3 = viewFactory.createCard(1.0f, Color.WHITE, true, new int[]{0,0,0, PageHelper.defaultMargin});
        viewFactory.addSimpleText("" +
                "만약에 '점수가 0~30 안에 있다'가 참이면\n" +
                "     C를 받는다 \n" +
                "그렇지 않고 만약에 '점수가 30~60 안에 있다'가 참이면\n" +
                "     B를 받는다 \n" +
                "그렇지 않고 '점수가 60~100 안에 있다'가 참이면\n" +
                "     A를 받는다 \n" , 20, textCard3);

        LinearLayout textCard4 = viewFactory.createCard(1.0f, Color.WHITE, true, new int[]{0,0,0, PageHelper.defaultMargin});
        viewFactory.addSimpleText("<참고> else if 또는 else 는 선택사항이다. ", 20, textCard4);
    }
}
