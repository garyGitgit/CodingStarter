package com.gachon.app.course1_1.course1_1_2;


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
import com.gachon.app.helper.ContentPagerListener;
import com.gachon.app.helper.MainPagerAdapter;
import com.gachon.app.helper.MyViewPager;
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
    MainPagerAdapter pagerAdapter;

    // Required empty public constructor
    public Course1_1_2Step1() {
    }


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
        viewFactory.createHeaderCard("변수 할당", new int[]{0, 0, 0, PageHelper.headerCardMargin});

        //animation 카드 생성
        viewFactory.createAnimationCard(3.0f, R.raw.variable2_how, new int[]{0, 0, 0, PageHelper.defaultMargin});

        final MyViewPager viewPager = new MyViewPager(getContext());
        viewPager.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        //슬라이드 카드 생성
        pagerAdapter = viewFactory.createSlideCard(1.0f, new int[]{0, 0, 0, 0}, viewPager);
        Activity parentActivity = getActivity();
        viewFactory.addCardOnSlideCard("'할당' 이라는 말은 변수에 값을 넣는 것을 의미한다.", pagerAdapter, parentActivity);
        viewFactory.addCardOnSlideCard("예를 들어, 변수 num 에 10을 저장하는 것을 '변수 num 에 10을 할당한다' 와 같이 표현한다.", pagerAdapter, parentActivity);
        viewFactory.addCardOnSlideCard("할당 연산자 '=' 을 이용하여 '변수 = 값' 으로 쓴다 ", pagerAdapter, parentActivity);
        viewFactory.addCardOnSlideCard("상수가 아니라 변수이기 때문에 한 번 할당한 값을 새로 할당할 수 있다.", pagerAdapter, parentActivity);
        //viewFactory.addCardOnSlideCard(PageHelper.endingString, pagerAdapter, parentActivity);
        viewFactory.addEndCardOnSlideCard(pagerAdapter);

        //공간 추가
        viewFactory.addSpace(0.5f);

        /* 페이지 넘어가는 버튼 */
        ImageButton goNext = (ImageButton) root.findViewById(R.id.goNext);
        ImageButton goPrev = (ImageButton) root.findViewById(R.id.goPrevious);
//        goNext.setOnClickListener(new ContentPageListener(1, viewPager, pagerAdapter, getActivity()));
//        goPrev.setOnClickListener(new ContentPageListener(0, viewPager, pagerAdapter, getActivity()));

        ContentPagerListener contentPagerListener = new ContentPagerListener(new MyViewPager[]{viewPager}, new MainPagerAdapter[]{pagerAdapter}, getActivity());
        goNext.setOnClickListener(contentPagerListener);
        goPrev.setOnClickListener(contentPagerListener);
    }
}
