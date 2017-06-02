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

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Course1_1_2Step0 extends Fragment {

    //항상 추가
    View root; // 부모 액티비티
    ViewFactoryCS viewFactory;

    ArrayList<Integer> slideCardNum = new ArrayList<>();
    MainPagerAdapter pagerAdapter;

    public Course1_1_2Step0() {
        // Required empty public constructor
    }

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
        viewFactory.createHeaderCard("변수에 값을 어떻게 넣을까?", new int[]{0, 0, 0, PageHelper.headerCardMargin});

        //animation 카드 생성
        viewFactory.createAnimationCard(3.0f, R.raw.variable2_why, new int[]{0, 0, 0, PageHelper.defaultMargin});

        //text card 추가
        final MyViewPager viewPager = new MyViewPager(getContext());
        viewPager.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        pagerAdapter = viewFactory.createSlideCard(1.0f, new int[]{0,0,0,0}, viewPager);
        slideCardNum.add(0);

        //text card 에 내용 추가
        Activity parentActivity = getActivity();
        viewFactory.addCardOnSlideCard("컴퓨터 상에 변수라는 공간을 할당했으니 변수에 값은 어떻게 넣을까?", pagerAdapter, parentActivity);
        viewFactory.addCardOnSlideCard(PageHelper.endingString, pagerAdapter, parentActivity);

        //공간 추가
        viewFactory.addSpace(0.8f);


       /* 페이지 넘어가는 버튼 */
        ImageButton goNext = (ImageButton) root.findViewById(R.id.goNext);
        goNext.setOnClickListener(new ContentPageListener(1, viewPager, pagerAdapter, getActivity()));
        ImageButton goPrev = (ImageButton) root.findViewById(R.id.goPrevious);
        goPrev.setOnClickListener(new ContentPageListener(0, viewPager, pagerAdapter, getActivity()));
    }

}
