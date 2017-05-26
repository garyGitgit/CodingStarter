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
import android.widget.RelativeLayout;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.gachon.app.R;
import com.gachon.app.helper.MainPagerAdapter;
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

    //layout
    int size = 3;
    FrameLayout[] textCard = new FrameLayout[size];
    RelativeLayout[] cardCover = new RelativeLayout[size];

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

        //이미지 추가
//        LinearLayout imageCard = viewFactory.createCard(1.0f, Color.WHITE, false, new int[]{0, 0, 0, PageHelper.defaultMargin});
//        viewFactory.addImage(getResources().getDrawable(R.drawable.shoe), imageCard);
//
//        //카드 생성
//        for(int i = 0; i < size; i ++){
//            textCard[i] = viewFactory.createCard(1.0f, new int[]{0, 0, 0, PageHelper.defaultMargin});
//        }
//
//        //텍스트 설정
//        viewFactory.addSimpleText("할당이란\n" +
//                "'할당' 이라는 말은 변수에 값을 넣는 것을 의미한다. 예를 들어, '변수 num 에 10을 할당한다' 와 같이 표현한다.", 18, textCard[0]);
//
//        viewFactory.addSimpleText("할당 연산자 '='\n" +
//                "변수 = 값", 18, textCard[1]);
//
//        viewFactory.addSimpleText("변수와 할당\n" +
//                "상수가 아니라 변수이기 때문에 한 번 할당한 값을 새로 할당할 수 있다.", 18, textCard[2]);
//        //inflate
//        LayoutInflater inflater = (LayoutInflater) root.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

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
        viewFactory.addCardOnSlideCard("'할당' 이라는 말은 변수에 값을 넣는 것을 의미한다. 예를 들어, '변수 num 에 10을 할당한다' 와 같이 표현한다.", pagerAdapter, parentActivity);
        viewFactory.addCardOnSlideCard("할당 연산자 '='\n변수 = 값", pagerAdapter, parentActivity);
        viewFactory.addCardOnSlideCard("변수와 할당\n상수가 아니라 변수이기 때문에 한 번 할당한 값을 새로 할당할 수 있다.", pagerAdapter, parentActivity);
        viewFactory.addCardOnSlideCard("다음", pagerAdapter, parentActivity);


//        //카드 추가, 카드로 덮기
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
                case 3:
                    YoYo.with(Techniques.FadeOut).duration(PageHelper.cardOpenDelay).playOn(cardCover[3]);
                    break;
            }
        }
    }
}
