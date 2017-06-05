package com.gachon.app.course1_2.course1_2_1;


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
 * course 1-2 연산자
 * step 1 : 연산자 종류 설명
 */

public class Course1_2_1Step1 extends Fragment {

    int size = 3;
    int numCards = 3;

    View root;
    ViewFactoryCS viewFactory;
    MyViewPager[] viewPagers = new MyViewPager[numCards];
    MainPagerAdapter[] pagerAdapters = new MainPagerAdapter[numCards];


    //FrameLayout[] textCard = new FrameLayout[size];
    //RelativeLayout[] cardCover = new RelativeLayout[size];

    public Course1_2_1Step1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_g_step1, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //최상단 루트 레이아웃
        LinearLayout layout = (LinearLayout) root.findViewById(R.id.fragment_g_step1);
        viewFactory = new ViewFactoryCS(layout);


        //카드 1
        viewPagers[0] = new MyViewPager(getContext());
        //final ViewPager viewPager = new ViewPager(getContext());
        viewPagers[0].setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        pagerAdapters[0] = viewFactory.createSlideCard(1.0f, new int[]{0,0,0,PageHelper.defaultMargin}, viewPagers[0]);

        Activity parentActivity = getActivity();
        viewFactory.addCardOnSlideCard("1. 산술 연산자", pagerAdapters[0],parentActivity);
        viewFactory.addCardOnSlideCard("더하기 (+) : +", pagerAdapters[0], parentActivity);
        viewFactory.addCardOnSlideCard("빼기 (-) : -", pagerAdapters[0], parentActivity);
        viewFactory.addCardOnSlideCard("곱하기 (x) : *", pagerAdapters[0], parentActivity);
        viewFactory.addCardOnSlideCard("나누기 (÷) : /", pagerAdapters[0], parentActivity);
        viewFactory.addCardOnSlideCard("나머지 : %", pagerAdapters[0], parentActivity);
        viewFactory.addCardOnSlideCard("거듭제곱 : ^", pagerAdapters[0], parentActivity);
        viewFactory.addCardOnSlideCard("(예시) 2의 5승 : 2^5", pagerAdapters[0], parentActivity);
        viewFactory.addEndCardOnSlideCard(pagerAdapters[0]);
        //viewFactory.addCardOnSlideCard(PageHelper.endingString, pagerAdapters[0], parentActivity);

        //카드 2
        viewPagers[1] = new MyViewPager(getContext());
        //final ViewPager viewPager = new ViewPager(getContext());
        viewPagers[1].setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        pagerAdapters[1] = viewFactory.createSlideCard(1.0f, new int[]{0,0,0,PageHelper.defaultMargin}, viewPagers[1]);

        viewFactory.addCardOnSlideCard("2. 비교 연산자", pagerAdapters[1],parentActivity);
        viewFactory.addCardOnSlideCard("등호 (=) : ==", pagerAdapters[1], parentActivity);
        viewFactory.addCardOnSlideCard("부등호 (≠) : !=", pagerAdapters[1], parentActivity);
        viewFactory.addCardOnSlideCard("작거나 같다 (≤) : <=", pagerAdapters[1], parentActivity);
        viewFactory.addCardOnSlideCard("크거나 같다 (≥) : >=", pagerAdapters[1], parentActivity);
        //viewFactory.addCardOnSlideCard(PageHelper.endingString, pagerAdapters[1], parentActivity);
        viewFactory.addEndCardOnSlideCard(pagerAdapters[1]);


        //카드 3
        viewPagers[2] = new MyViewPager(getContext());
        //final ViewPager viewPager = new ViewPager(getContext());
        viewPagers[2].setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        pagerAdapters[2] = viewFactory.createSlideCard(1.0f, new int[]{0,0,0,PageHelper.defaultMargin}, viewPagers[2]);

        viewFactory.addCardOnSlideCard("3. 기타 연산자", pagerAdapters[2],parentActivity);
        viewFactory.addCardOnSlideCard("할당 연산자 : = ", pagerAdapters[2], parentActivity);
        viewFactory.addCardOnSlideCard("A = B 는 변수 A 에 B 값을 넣는다는 의미", pagerAdapters[2], parentActivity);
        viewFactory.addCardOnSlideCard("부정 연산자 (~) : !", pagerAdapters[2], parentActivity);
        viewFactory.addCardOnSlideCard("false == !true, true == !false ", pagerAdapters[2], parentActivity);
        //viewFactory.addCardOnSlideCard(PageHelper.endingString, pagerAdapters[2], parentActivity);
        viewFactory.addEndCardOnSlideCard(pagerAdapters[2]);


        //space 추가
        viewFactory.addSpace(0.5f);

        /* 페이지 넘어가는 버튼 */
        ImageButton goNext = (ImageButton) root.findViewById(R.id.goNext);
        ImageButton goPrev = (ImageButton) root.findViewById(R.id.goPrevious);
        goNext.setOnClickListener(new ContentPageListener(3, viewPagers, pagerAdapters, parentActivity));
        goPrev.setOnClickListener(new ContentPageListener(2, viewPagers, pagerAdapters, parentActivity));

//        viewFactory.addSpace(0.5f);

        //연산자에 대해서 설명하는 카드 생성
        //카드 생성
//        for(int i = 0 ; i < size; i++){
//            textCard[i] = viewFactory.createCard(1.0f, new int[]{0,0,0, PageHelper.defaultMargin});
//        }

        //텍스트 설정
//        viewFactory.addSimpleText("" +
//                "계산(?) 연산자\n" +
//                "더하기(+) : + \n" +
//                "빼기(-) : - \n" +
//                "곱하기(x) : * \n" +
//                "나누기(÷) : / \n" +
//                "나머지 : % ", 20, textCard[0]);
//
//        //비교연산자카드
//        viewFactory.addSimpleText("" +
//                "비교 연산자\n" +
//                "등호(=) : == \n" +
//                "부등호(≠) : != ", 20, textCard[1]);
//
//        //기타 연산자
//        viewFactory.addSimpleText("" +
//                "기타 연산자\n" +
//                "할당(대입) : = \n" +
//                "부정(~) : ! ", 20, textCard[2]);
//
//        //카드 추가
//
//        LayoutInflater inflater = (LayoutInflater)root.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//        for (int i = 0; i < size; i++) {
//            //card 커버 로드
//            cardCover[i] = new RelativeLayout(getContext());
//            inflater.inflate(R.layout.cardcover, cardCover[i]);
//            //카드 눌렀을 때 카드 사라지기
//            textCard[i].setTag(i);
//            textCard[i].setOnClickListener(new onCardClicked());
//            //card 로 덮기
//            textCard[i].addView(cardCover[i]);
//        }

        //공간 추가
//        viewFactory.addSpace(0.5f);
    }

//    class onCardClicked implements View.OnClickListener{
//        @Override
//        public void onClick(View v) {
//            int tag = ((Integer)v.getTag());
//            switch (tag){
//                case 0:
//                    YoYo.with(Techniques.FadeOut).duration(PageHelper.cardOpenDelay).playOn(cardCover[0]);
//                    break;
//                case 1:
//                    YoYo.with(Techniques.FadeOut).duration(PageHelper.cardOpenDelay).playOn(cardCover[1]);
//                    break;
//                case 2:
//                    YoYo.with(Techniques.FadeOut).duration(PageHelper.cardOpenDelay).playOn(cardCover[2]);
//                    break;
//                case 3:
//                    YoYo.with(Techniques.FadeOut).duration(PageHelper.cardOpenDelay).playOn(cardCover[3]);
//                    break;
//            }
//        }
//    }
}
