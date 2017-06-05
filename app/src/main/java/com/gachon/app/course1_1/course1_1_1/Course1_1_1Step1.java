package com.gachon.app.course1_1.course1_1_1;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.gachon.app.R;
import com.gachon.app.helper.ContentPageListener;
import com.gachon.app.helper.MainPagerAdapter;
import com.gachon.app.helper.MyViewPager;
import com.gachon.app.helper.PageHelper;
import com.gachon.app.helper.ViewFactoryCS;


/**
 * course 1-1 데이터 타입 / 변수 / 초기화
 * step 1 : 신발장을 통한 비유
 */
public class Course1_1_1Step1 extends Fragment {
    //공통으로 적용
    View root; // 부모 액티비티
    ViewFactoryCS viewFactory;
    MainPagerAdapter pagerAdapter;

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

        //header text 설정
        viewFactory.createHeaderCard("변수란 무엇일까?", new int[]{0, 0, 0, PageHelper.headerCardMargin});

        //animation 카드 생성
        viewFactory.createAnimationCard(3.0f, R.raw.variable1_how, new int[]{0, 0, 0, PageHelper.defaultMargin});

        final MyViewPager viewPager = new MyViewPager(getContext());
        viewPager.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        //slide card text 카드 생성
        pagerAdapter = viewFactory.createSlideCard(1.0f, new int[]{0, 0, 0, 0}, viewPager);

        Activity parentActivity = getActivity();
        viewFactory.addCardOnSlideCard("변수란\n\n값을 저장하기 위한 공간이다.", pagerAdapter, parentActivity);
        viewFactory.addCardOnSlideCard("변수의 데이터 타입\n\n변수를 사용하기 위한 목적이다. 데이터 타입은 공간의 목적에 따라 다르다.", pagerAdapter, parentActivity);
        viewFactory.addCardOnSlideCard("변수의 선언\n\n변수를 사용하겠다고 이름과 데이터 타입을 정의하는 것 ", pagerAdapter, parentActivity);
        viewFactory.addEndCardOnSlideCard(pagerAdapter); //완료 카드

        //space 추가
        viewFactory.addSpace(0.5f);

        /* 페이지 넘어가는 버튼 */
        ImageButton goNext = (ImageButton) root.findViewById(R.id.goNext);
        goNext.setOnClickListener(new ContentPageListener(1, viewPager, pagerAdapter, getActivity()));
        ImageButton goPrev = (ImageButton) root.findViewById(R.id.goPrevious);
        goPrev.setOnClickListener(new ContentPageListener(0, viewPager, pagerAdapter, getActivity()));
    }
}
