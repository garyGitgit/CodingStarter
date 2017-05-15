package com.gachon.app.course1_1.course1_1_1;


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
 * course 1-1 데이터 타입 / 변수 / 초기화
 * step 1 : 신발장을 통한 비유
 */
public class Course1_1_1Step2 extends Fragment {
    //항상 추가
    View root; // 부모 액티비티
    ViewFactoryCS viewFactory;

    //layout
    FrameLayout[] textCard = new FrameLayout[4];
    RelativeLayout[] cardCover = new RelativeLayout[4];
    int size = 4;

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

        //헤더 카드 생성


        //카드생성
        for(int i = 0; i < size; i++){
            if(i == 0 )
                textCard[i] = viewFactory.createCard(0.8f, new int[]{0,0,0,PageHelper.defaultMargin});
            else
                textCard[i] = viewFactory.createCard(1.0f, new int[]{0,0,0,PageHelper.defaultMargin});
        }
        //텍스트 설정

        viewFactory.addSimpleText("변수 선언하는 법\n(변수의 타입) (변수 이름);", 20, textCard[0]);

        viewFactory.addSimpleText("" +
                "1. 변수의 타입을 정한다\n" +
                "- int : 정수형 (예. 1, 100, 478)\n" +
                "- float : 실수형 (예. 1.0, 100.1, 478.23)\n" +
                "- char : 문자형 (예. '1', 'a', 'K', '-')", 20, textCard[1]);

        viewFactory.addSimpleText("" +
                "2. 변수 이름을 정한다\n" +
                "변수 이름을 지을 때는 몇 가지 규칙이 있다.\n" +
                "1. 영어 알파벳 대소문자 또는 대소문자+숫자\n" +
                "2. 첫 글자는 반드시 알파벳 대소문자\n" +
                "3. 특수문자는 '_' 만 가능(이 특수문자는 첫 글자로도 쓸 수 있다)", 20, textCard[2]);

        viewFactory.addSimpleText("" +
                "3. 세미콜론(;)\n" +
                "세미콜론(;)은 프로그램 한 줄의 끝을 의미한다. 세미콜론이 없으면 에러가 난다.", 20, textCard[3]);

        //inflate
        LayoutInflater inflater = (LayoutInflater)root.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        //카드 추가, 카드로 덮기
        for(int i = 0 ; i < size; i++){
            //card 커버 로드
            cardCover[i] = new RelativeLayout(getContext());
            inflater.inflate(R.layout.cardcover, cardCover[i]);
            //카드 눌렀을 때 카드 사라지기
            textCard[i].setTag(i);
            textCard[i].setOnClickListener(new onCardClicked());
            //card 로 덮기
            textCard[i].addView(cardCover[i]);
        }

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
