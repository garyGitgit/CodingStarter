package com.gachon.app.course1_2.course1_2_1;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.gachon.app.R;
import com.gachon.app.helper.PageHelper;
import com.gachon.app.helper.ViewFactoryCS;


/**
 * course 1-2 연산자
 * step 1 : 연산자 종류 설명
 */

public class Course1_2_1Step1 extends Fragment {

    View root;
    ViewFactoryCS viewFactory;

    int size = 3;
    FrameLayout[] textCard = new FrameLayout[size];
    RelativeLayout[] cardCover = new RelativeLayout[size];

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

        viewFactory.addSpace(0.5f);

        //연산자에 대해서 설명하는 카드 생성
        //카드 생성
        for(int i = 0 ; i < size; i++){
            textCard[i] = viewFactory.createCard(1.0f, new int[]{0,0,0, PageHelper.defaultMargin});
        }

        //텍스트 설정
        viewFactory.addSimpleText("" +
                "계산(?) 연산자\n" +
                "더하기(+) : + \n" +
                "빼기(-) : - \n" +
                "곱하기(x) : * \n" +
                "나누기(÷) : / \n" +
                "나머지 : % ", 20, textCard[0]);

        //비교연산자카드
        viewFactory.addSimpleText("" +
                "비교 연산자\n" +
                "등호(=) : == \n" +
                "부등호(≠) : != ", 20, textCard[1]);

        //기타 연산자
        viewFactory.addSimpleText("" +
                "기타 연산자\n" +
                "할당(대입) : = \n" +
                "부정(~) : ! ", 20, textCard[2]);

        //카드 추가

        LayoutInflater inflater = (LayoutInflater)root.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        for (int i = 0; i < size; i++) {
            //card 커버 로드
            cardCover[i] = new RelativeLayout(getContext());
            inflater.inflate(R.layout.cardcover, cardCover[i]);
            //카드 눌렀을 때 카드 사라지기
            textCard[i].setTag(i);
            textCard[i].setOnClickListener(new onCardClicked());
            //card 로 덮기
            textCard[i].addView(cardCover[i]);
        }

        //공간 추가
        viewFactory.addSpace(0.5f);
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
