package com.gachon.app.course1_1.course1_1_2;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.gachon.app.R;
import com.gachon.app.helper.MainPagerAdapter;
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
        viewFactory = new ViewFactoryCS(layout);

//        LinearLayout card1 = viewFactory.createCard(1.0f, Color.WHITE, true, new int[]{0, 0, 0, PageHelper.defaultMargin});
        //viewFactory.addSimpleText("컴퓨터 상에 변수라는 공간을 할당했으니 변수에 값은 어떻게 넣을까?", 30, card1);
        //animation card 생성
        FrameLayout animCard = viewFactory.createCard(3.0f, new int[]{0,0,0,PageHelper.defaultMargin});

        //viewpager card 생성
        //FrameLayout slideCard = viewFactory.createCard(1.0f, new int[]{0,0,0,PageHelper.defaultMargin});

        //slide card 생성
//        LinearLayout slideCard_linear = (LinearLayout)getActivity().getLayoutInflater().inflate(R.layout.slidecard, null);
//        MyViewPager viewPager = (MyViewPager) slideCard_linear.findViewById(R.id.slideCard_viewPager);

//        MainPagerAdapter pagerAdapter = viewFactory.createSlideCard(1.0f, new int[]{0,0,0,0}, viewPager, slideCard_linear);
        //MyViewPager viewPager = new MyViewPager(getContext());
        ViewPager viewPager = new ViewPager(getContext());
        viewPager.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        MainPagerAdapter pagerAdapter = viewFactory.createSlideCard(1.0f, new int[]{0,0,0,0}, viewPager);
        //slideCardNum.add(0);

        Activity parentActivity = getActivity();
        viewFactory.addCardOnSlideCard("컴퓨터 상에 변수라는 공간을 할당했으니 변수에 값은 어떻게 넣을까?", pagerAdapter, parentActivity);
        viewFactory.addCardOnSlideCard("다음", pagerAdapter, parentActivity);
    }

}
