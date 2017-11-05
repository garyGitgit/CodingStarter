package com.coconutlab.app.course1_2.course1_2_1;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.coconutlab.app.R;
import com.coconutlab.app.helper.ContentPagerListener;
import com.coconutlab.app.helper.MainPagerAdapter;
import com.coconutlab.app.helper.MyViewPager;
import com.coconutlab.app.helper.PageHelper;
import com.coconutlab.app.helper.ViewFactoryCS;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Course1_2_1Step0 extends Fragment {

    View root;
    ViewFactoryCS viewFactory;

    ArrayList<Integer> slideCardNum = new ArrayList<>();
    MainPagerAdapter pagerAdapter;

    public Course1_2_1Step0() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_g_step0, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //최상단 루트 레이아웃
        LinearLayout layout = (LinearLayout) root.findViewById(R.id.fragment_g_step0);
        viewFactory = new ViewFactoryCS(layout);

        //text card 추가
        final MyViewPager viewPager = new MyViewPager(getContext());
        viewPager.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        //header text 설정
        viewFactory.createHeaderCard("컴퓨터와 연산자", new int[]{0, 0, 0, PageHelper.headerCardMargin});

        //animation 카드 생성
        viewFactory.createAnimationCard(3.0f, R.raw.operator_why, new int[]{0, 0, 0, PageHelper.defaultMargin});

        pagerAdapter = viewFactory.createSlideCard(1.0f, new int[]{0,0,0,0}, viewPager);

        //text card 에 내용 추가
        Activity parentActivity = getActivity();
        viewFactory.addCardOnSlideCard("더하기, 빼기, 나누기 등 연산자들은 어떻게 표현할까?", pagerAdapter, parentActivity);
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

//        LinearLayout textCard1 = viewFactory.createCard(1.0f, Color.WHITE, false, new int[]{0,0,0, PageHelper.defaultMargin});
//        viewFactory.addSimpleText("더하기, 빼기, 나누기 등 연산자들은 어떻게 표현할까?", 25, textCard1);


    }
}
