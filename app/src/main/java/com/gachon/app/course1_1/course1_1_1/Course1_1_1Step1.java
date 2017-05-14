package com.gachon.app.course1_1.course1_1_1;


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
public class Course1_1_1Step1 extends Fragment {
    //항상 추가
    View root; // 부모 액티비티
    ViewFactoryCS viewFactory;

    // Required empty public constructor
    public Course1_1_1Step1() {}


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

        //이해를 돕는 이미지 카드 생성
        LinearLayout imageCard = viewFactory.createCard(0.5f, Color.WHITE, true, new int[]{0,0,0, PageHelper.defaultMargin});
        viewFactory.addImage(100, 100, getResources().getDrawable(R.drawable.shoebox), imageCard);

        LinearLayout textCard1 = viewFactory.createCard(1.0f, Color.WHITE, true, new int[]{0,0,0, PageHelper.defaultMargin});
        viewFactory.addSimpleText("변수란", 20, textCard1);
        viewFactory.addSimpleText("값을 저장하기 위한 공간이다. (신발장 공간)", 15, textCard1);

        LinearLayout textCard2 = viewFactory.createCard(1.0f, Color.WHITE, true, new int[]{0,0,0, PageHelper.defaultMargin});
        viewFactory.addSimpleText("변수의 데이터 타입", 20, textCard2);
        viewFactory.addSimpleText("변수를 사용하기 위한 목적이다. 데이터 타입은 공간의 목적에 따라 다르다. (신발장의 목적 : 신발을 담기 위함)", 15, textCard2);

        LinearLayout textCard3 = viewFactory.createCard(1.0f, Color.WHITE, true, new int[]{0,0,0, 10});
        viewFactory.addSimpleText("변수의 선언", 20, textCard3);
        viewFactory.addSimpleText("변수를 사용하겠다고 이름과 데이터 타입을 정의하는 것. (나는 신발장을 담기 위한 공간을 마련할거야.)", 15, textCard3);
    }
}
