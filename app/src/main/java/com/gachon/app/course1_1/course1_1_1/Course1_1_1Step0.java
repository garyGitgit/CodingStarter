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
import com.gachon.app.helper.ContentPagerListener;
import com.gachon.app.helper.MainPagerAdapter;
import com.gachon.app.helper.MyViewPager;
import com.gachon.app.helper.PageHelper;
import com.gachon.app.helper.ViewFactoryCS;

/**
 * A simple {@link Fragment} subclass.
 */
public class Course1_1_1Step0 extends Fragment {
    //공통으로 적용
    View root; // 부모 액티비티
    ViewFactoryCS viewFactory;
    MainPagerAdapter pagerAdapter;


    //default 는 꼭 있어야 한다
    public Course1_1_1Step0() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //항상 추가
        root = inflater.inflate(R.layout.fragment_g_step0, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //최상단 루트 레이아웃
        LinearLayout layout = (LinearLayout) root.findViewById(R.id.fragment_g_step0);
        //viewfactory 생성
        viewFactory = new ViewFactoryCS(layout);

        //header text 설정
        viewFactory.createHeaderCard("변수가 왜 필요할까?", new int[]{0, 0, 0, PageHelper.headerCardMargin});

        //animation 카드 생성
        viewFactory.createAnimationCard(3.0f, R.raw.variable1_why, new int[]{0, 0, 0, PageHelper.defaultMargin});


        final MyViewPager viewPager = new MyViewPager(getContext());
        viewPager.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        //slide text card 추가
        pagerAdapter = viewFactory.createSlideCard(1.0f, new int[]{0, 0, 0, 0}, viewPager);

        //text card 에 내용 추가
        Activity parentActivity = getActivity();
        viewFactory.addCardOnSlideCard("프로그램은 수학적인 문제를 해결하기 위해서 만들어졌다.", pagerAdapter, parentActivity);
        viewFactory.addCardOnSlideCard("결과값을 기억하기 위해 컴퓨터는 '변수'라는 것을 사용한다.", pagerAdapter, parentActivity);
        viewFactory.addEndCardOnSlideCard(pagerAdapter); //완료 페이지 추가

        //공간 추가
        viewFactory.addSpace(0.5f);

        //페이지 넘기기 버튼 : 0, 1 하나짜리 슬라이드 넘기기
        ImageButton goNext = (ImageButton) root.findViewById(R.id.goNext);
        ImageButton goPrev = (ImageButton) root.findViewById(R.id.goPrevious);
        //goNext.setOnClickListener(new ContentPageListener(1, viewPager, pagerAdapter, getActivity()));

        ContentPagerListener contentPagerListener = new ContentPagerListener(new MyViewPager[]{viewPager}, new MainPagerAdapter[]{pagerAdapter}, getActivity());
        goNext.setOnClickListener(contentPagerListener);
        goPrev.setOnClickListener(contentPagerListener);

        //goPrev.setOnClickListener(new ContentPageListener(0, viewPager, pagerAdapter, getActivity()));

    }
}
