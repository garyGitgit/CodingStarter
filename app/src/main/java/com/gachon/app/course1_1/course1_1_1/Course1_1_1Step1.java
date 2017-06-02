package com.gachon.app.course1_1.course1_1_1;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
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
    //항상 추가
    View root; // 부모 액티비티
    ViewFactoryCS viewFactory;

    //layout
    FrameLayout[] textCard = new FrameLayout[3];
    RelativeLayout[] cardCover = new RelativeLayout[3];

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

        //cardcover 로딩

        //최상단 루트 레이아웃
        LinearLayout layout = (LinearLayout) root.findViewById(R.id.fragment_g_step1);
        viewFactory = new ViewFactoryCS(layout);

        //header text 설정
        viewFactory.createHeaderCard("변수란 무엇일까?", new int[]{0, 0, 0, PageHelper.headerCardMargin});

        //animation 카드 생성
        viewFactory.createAnimationCard(3.0f, R.raw.variable1_how, new int[]{0, 0, 0, PageHelper.defaultMargin});

        final MyViewPager viewPager = new MyViewPager(getContext());
        //final ViewPager viewPager = new ViewPager(getContext());
        viewPager.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        pagerAdapter = viewFactory.createSlideCard(1.0f, new int[]{0,0,0,0}, viewPager);

        Activity parentActivity = getActivity();
        viewFactory.addCardOnSlideCard("변수란\n값을 저장하기 위한 공간이다.", pagerAdapter,parentActivity);
        viewFactory.addCardOnSlideCard("변수의 데이터 타입\n변수를 사용하기 위한 목적이다. 데이터 타입은 공간의 목적에 따라 다르다.", pagerAdapter, parentActivity);
        viewFactory.addCardOnSlideCard("변수의 선언\n변수를 사용하겠다고 이름과 데이터 타입을 정의하는 것 ", pagerAdapter, parentActivity);
        viewFactory.addCardOnSlideCard(PageHelper.endingString, pagerAdapter, parentActivity);

        //space 추가
        viewFactory.addSpace(0.8f);

        //image button
        /* 페이지 넘어가는 버튼 */
        ImageButton goNext = (ImageButton) root.findViewById(R.id.goNext);
        goNext.setOnClickListener(new ContentPageListener(1, viewPager, pagerAdapter, getActivity()));
        ImageButton goPrev = (ImageButton) root.findViewById(R.id.goPrevious);
        goPrev.setOnClickListener(new ContentPageListener(0, viewPager, pagerAdapter, getActivity()));



        //이해를 돕는 이미지 카드 생성
//        LinearLayout imageCard = viewFactory.createCard(0.0f, Color.WHITE, true, new int[]{0,0,0, PageHelper.defaultMargin});
//        viewFactory.addImage(100, 100, getResources().getDrawable(R.drawable.shoebox), imageCard);


//        //카드 생성
//        for(int i = 0 ; i < 3; i++){
//            textCard[i] = viewFactory.createCard(1.0f, new int[]{0,0,0, PageHelper.defaultMargin});
//        }
//
//        //텍스트 추가
//        viewFactory.addSimpleText("" +
//                "변수란\n" +
//                "값을 저장하기 위한 공간이다. (신발장 공간)", 20, textCard[0]);
//
//        viewFactory.addSimpleText("" +
//                "변수의 데이터 타입\n" +
//                "변수를 사용하기 위한 목적이다. 데이터 타입은 공간의 목적에 따라 다르다. (신발장의 목적 : 신발을 담기 위함)", 20, textCard[1]);
//
//        viewFactory.addSimpleText("" +
//                "변수의 선언\n" +
//                "변수를 사용하겠다고 이름과 데이터 타입을 정의하는 것. (나는 신발장을 담기 위한 공간을 마련할거야.)", 20, textCard[2]);
//
//        //카드 추가, 카드로 덮기
//        LayoutInflater inflater = (LayoutInflater)root.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//

//        for(int i = 0 ; i < 3; i++){
//            //card 커버 로드
//            cardCover[i] = new RelativeLayout(getContext());
//            inflater.inflate(R.layout.cardcover, cardCover[i]);
//            //카드 눌렀을 때 카드 사라지기
//            textCard[i].setTag(i);
//            textCard[i].setOnClickListener(new onCardClicked());
//            //card 로 덮기
//            textCard[i].addView(cardCover[i]);
//        }

    }
    class onCardClicked implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            int tag = ((Integer)v.getTag());
            switch (tag){
                case 0:
                    YoYo.with(Techniques.FadeOut).duration(PageHelper.cardOpenDelay).playOn(cardCover[0]);
                    break;
                case 1:
                    YoYo.with(Techniques.FadeOut).duration(PageHelper.cardOpenDelay).playOn(cardCover[1]);
                    break;
                case 2:
                    YoYo.with(Techniques.FadeOut).duration(PageHelper.cardOpenDelay).playOn(cardCover[2]);
                    break;
            }
        }
    }
}
