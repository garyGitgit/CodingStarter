package com.gachon.app.course1_1.course1_1_1;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

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
public class Course1_1_1Step0 extends Fragment {





    //항상 추가
    View root; // 부모 액티비티
    ViewFactoryCS viewFactory;

    ArrayList<Fragment> cards = new ArrayList<>();
    //layouts

    RelativeLayout[] cardCover = new RelativeLayout[3];
    int cardBackgroundColor;
    MainPagerAdapter pagerAdapter;

    Handler mHandler = new Handler();

    ArrayList<Integer> slideCardNum = new ArrayList<>();


    public Course1_1_1Step0() {
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
        cardBackgroundColor = getResources().getColor(R.color.codingstarter_background);

        //최상단 루트 레이아웃
        LinearLayout layout = (LinearLayout) root.findViewById(R.id.fragment_g_step0);
        //viewfactory 생성
        viewFactory = new ViewFactoryCS(layout);

        //header text 설정
        viewFactory.createHeaderCard("변수가 왜 필요할까?", new int[]{0, 0, 0, PageHelper.headerCardMargin});

        //animation 카드 생성
        viewFactory.createAnimationCard(3.0f, R.raw.variable1_why, new int[]{0, 0, 0, PageHelper.defaultMargin});

        //text card 추가
        final MyViewPager viewPager = new MyViewPager(getContext());
        //final ViewPager viewPager = new ViewPager(getContext());
        viewPager.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        pagerAdapter = viewFactory.createSlideCard(1.0f, new int[]{0, 0, 0, 0}, viewPager);
        slideCardNum.add(0);

        //text card 에 내용 추가
        Activity parentActivity = getActivity();
        viewFactory.addCardOnSlideCard("프로그램은 수학적인 문제를 해결하기 위해서 만들어졌다.", pagerAdapter, parentActivity);
        viewFactory.addCardOnSlideCard("결과값을 기억하기 위해 컴퓨터는 '변수'라는 것을 사용한다.", pagerAdapter, parentActivity);
        viewFactory.addCardOnSlideCard(PageHelper.endingString, pagerAdapter, parentActivity);

        //공간 추가
        viewFactory.addSpace(0.8f);






//        goNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int thisPage = viewPager.getCurrentItem();
//                int pageNum = pagerAdapter.getCount();
//
//                if (thisPage < pageNum - 1) {
//                    viewPager.setCurrentItem(++thisPage);
//                } else {
//                    Toast.makeText(getActivity().getApplicationContext(), "next", Toast.LENGTH_SHORT).show();
//                    ViewFactoryCS.onGoNext onGoNext = (ViewFactoryCS.onGoNext) getActivity();
//                    onGoNext.onPressNext();
//                }
//            }
//        });

        /* 페이지 넘어가는 버튼 */
        ImageButton goNext = (ImageButton) root.findViewById(R.id.goNext);
        goNext.setOnClickListener(new ContentPageListener(1, viewPager, pagerAdapter, getActivity()));
        ImageButton goPrev = (ImageButton) root.findViewById(R.id.goPrevious);
        goPrev.setOnClickListener(new ContentPageListener(0, viewPager, pagerAdapter, getActivity()));
//        goPrev.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int thisPage = viewPager.getCurrentItem();
//
//                if (thisPage > 0) {
//                    viewPager.setCurrentItem(--thisPage);
//                } else {
//                    ViewFactoryCS.onGoPrevious onGoPrev = (ViewFactoryCS.onGoPrevious) getActivity();
//                    onGoPrev.onPressPrev();
//                }
//
//            }
//        });
    }
}
