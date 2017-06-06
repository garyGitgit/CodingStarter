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
 * course 1-1 데이터 타입 / 변수 / 초기화
 * step 1 : 신발장을 통한 비유
 */
public class Course1_1_1Step2 extends Fragment {
    //공통으로 적용
    View root; // 부모 액티비티
    ViewFactoryCS viewFactory;

    //여러 장의 슬라이드 카드 변수
    int numOfCards = 3; // 총 슬라이드 카드 수
    MyViewPager[] viewPagers = new MyViewPager[numOfCards]; //
    MainPagerAdapter[] pagerAdapters = new MainPagerAdapter[numOfCards];
    int currentCardNum = 0;

    // Required empty public constructor
    public Course1_1_1Step2() {}

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
        Activity parentActivity = getActivity();

        //header text 설정
        viewFactory.createHeaderCard("변수를 어떻게 사용할까?", new int[]{0, 0, 0, PageHelper.headerCardMargin});

        viewPagers[0] = new MyViewPager(getContext());
        viewPagers[0].setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        //슬라이드 카드1 생성
        pagerAdapters[0] = viewFactory.createSlideCard(1.0f, new int[]{0,0,0,PageHelper.defaultMargin}, viewPagers[0]);
        //카드1 내용 추가
        viewFactory.addCardOnSlideCard("1. 변수의 타입을 정한다", pagerAdapters[0], parentActivity);
        viewFactory.addCardOnSlideCard("int : 정수형 (예. 1, 100, 478)", pagerAdapters[0], parentActivity);
        viewFactory.addCardOnSlideCard("float : 실수형 (예. 1.0, 100.1, 478.23)", pagerAdapters[0], parentActivity);
        viewFactory.addCardOnSlideCard("char : 문자형 (예. '1', 'a', 'K', '-')", pagerAdapters[0], parentActivity);
        viewFactory.addEndCardOnSlideCard(pagerAdapters[0]); //완료 페이지



        viewPagers[1] = new MyViewPager(getContext());
        viewPagers[1].setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        //슬라이드 카드2 생성
        pagerAdapters[1] = viewFactory.createSlideCard(1.0f, new int[]{0,0,0,PageHelper.defaultMargin}, viewPagers[1]);
        //카드2 내용 추가
        viewFactory.addCardOnSlideCard("2. 변수 이름을 정한다", pagerAdapters[1], parentActivity);
        viewFactory.addCardOnSlideCard("변수 이름을 지을 때는 몇 가지 규칙이 있다.", pagerAdapters[1], parentActivity);
        viewFactory.addCardOnSlideCard("(1) 영어 알파벳 대소문자 또는 대소문자+숫자", pagerAdapters[1], parentActivity);
        viewFactory.addCardOnSlideCard("(2) 첫 글자는 반드시 알파벳 대소문자", pagerAdapters[1], parentActivity);
        viewFactory.addCardOnSlideCard("(3) 특수문자는 '_' 만 가능(이 특수문자는 첫 글자로도 쓸 수 있다)", pagerAdapters[1], parentActivity);
        viewFactory.addEndCardOnSlideCard(pagerAdapters[1]); //완료 페이지


        viewPagers[2] = new MyViewPager(getContext());
        viewPagers[2].setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        //슬라이드 카드3 생성
        pagerAdapters[2] = viewFactory.createSlideCard(1.0f, new int[]{0,0,0,0}, viewPagers[2]);
        //카드3 내용 추가
        viewFactory.addCardOnSlideCard("3. 세미콜론(;)", pagerAdapters[2], parentActivity);
        viewFactory.addCardOnSlideCard("세미콜론(;)은 프로그램 한 줄의 끝을 의미한다.", pagerAdapters[2], parentActivity);
        viewFactory.addCardOnSlideCard("세미콜론이 없으면 에러가 난다.", pagerAdapters[2], parentActivity);
        viewFactory.addEndCardOnSlideCard(pagerAdapters[2]); //완료 페이지

        //space 추가
        viewFactory.addSpace(0.5f);


        //이전 또는 다음으로 넘어가는 버튼
        ImageButton goNext = (ImageButton)root.findViewById(R.id.goNext);
        ImageButton goPrev= (ImageButton)root.findViewById(R.id.goPrevious);

        ContentPagerListener contentPagerListener = new ContentPagerListener(viewPagers, pagerAdapters, getActivity());

//        goNext.setOnClickListener(new ContentPageListener(3, viewPagers, pagerAdapters, getActivity()));
//        goPrev.setOnClickListener(new ContentPageListener(2, viewPagers, pagerAdapters, getActivity()));
        goNext.setOnClickListener(contentPagerListener);
        goPrev.setOnClickListener(contentPagerListener);
    }

    @Override
    public void onPause() {
        super.onPause();
        //화면이 넘어갔을 떄 카드들을 모두 처음으로 초기화시켜준다
        for(int i = 0; i < numOfCards; i++ ){
            viewPagers[i].setCurrentItem(0);
        }
        //현재 카드를 초기화 시킨다
        currentCardNum = 0;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
