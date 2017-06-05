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
import com.gachon.app.helper.ContentPageListener;
import com.gachon.app.helper.MainPagerAdapter;
import com.gachon.app.helper.MyViewPager;
import com.gachon.app.helper.PageHelper;
import com.gachon.app.helper.ViewFactoryCS;


/**
 * course 1-1 데이터 타입 / 변수 / 초기화
 * step 1 : 신발장을 통한 비유
 */
public class Course1_1_2Step2 extends Fragment {
    //항상 추가
    View root; // 부모 액티비티
    ViewFactoryCS viewFactory;

    int numOfCards = 2;
    MyViewPager[] viewPagers = new MyViewPager[numOfCards];
    MainPagerAdapter[] pagerAdapters = new MainPagerAdapter[numOfCards];
    int currentCardNum = 0;


    // Required empty public constructor
    public Course1_1_2Step2() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //항상 추가
        root = inflater.inflate(R.layout.fragment_g_step2, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //최상단 루트 레이아웃
        LinearLayout layout = (LinearLayout) root.findViewById(R.id.fragment_g_step2);
        viewFactory = new ViewFactoryCS(layout);

        //header text 설정
        viewFactory.createHeaderCard("변수의 초기화", new int[]{0, 0, 0, PageHelper.headerCardMargin});

        viewPagers[0] = new MyViewPager(getContext());
        viewPagers[0].setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        pagerAdapters[0] = viewFactory.createSlideCard(1.0f, new int[]{0,0,0,PageHelper.defaultMargin}, viewPagers[0]);

        Activity parentActivity = getActivity();
        viewFactory.addCardOnSlideCard("변수의 초기화", pagerAdapters[0], parentActivity);
        viewFactory.addCardOnSlideCard("처음에 할당했던 값을 바꿀 수 있다. 이 때, 다시 선언해주지 않아도 된다", pagerAdapters[0], parentActivity);
        viewFactory.addCardOnSlideCard("(예시) int num = 1;", pagerAdapters[0], parentActivity);
        viewFactory.addEndCardOnSlideCard(pagerAdapters[0]);

        viewPagers[1] = new MyViewPager(getContext());
        viewPagers[1].setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        pagerAdapters[1] = viewFactory.createSlideCard(1.0f, new int[]{0,0,0,0}, viewPagers[1]);

        viewFactory.addCardOnSlideCard("변수 값 재할당", pagerAdapters[1], parentActivity);
        viewFactory.addCardOnSlideCard("한 번 할당한 값을 새로 할당할 수 있다", pagerAdapters[1], parentActivity);
        viewFactory.addCardOnSlideCard("(예시) int num = 1;\nnum = 2;", pagerAdapters[1], parentActivity);
        viewFactory.addCardOnSlideCard("이 때, num 에 저장되어있는 값은 2 이다", pagerAdapters[1], parentActivity);
        viewFactory.addEndCardOnSlideCard(pagerAdapters[1]);

        //공간추가
        viewFactory.addSpace(0.5f);

        /* 페이지 넘어가는 버튼 */
        ImageButton goNext = (ImageButton) root.findViewById(R.id.goNext);
        ImageButton goPrev = (ImageButton) root.findViewById(R.id.goPrevious);
        goNext.setOnClickListener(new ContentPageListener(3, viewPagers, pagerAdapters, getActivity()));
        goPrev.setOnClickListener(new ContentPageListener(2, viewPagers, pagerAdapters, getActivity()));
    }
}
