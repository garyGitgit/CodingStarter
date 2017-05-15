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
 * A simple {@link Fragment} subclass.
 */
public class Course1_1_1Step0 extends Fragment {

    //항상 추가
    View root; // 부모 액티비티
    ViewFactoryCS viewFactory;

    //layouts
    FrameLayout[] textCard = new FrameLayout[3];
    RelativeLayout[] cardCover = new RelativeLayout[3];
    int cardBackgroundColor;



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
        //최상단 루트 레이아웃
        cardBackgroundColor = getResources().getColor(R.color.codingstarter_background);

        LinearLayout layout = (LinearLayout) root.findViewById(R.id.fragment_g_step0);
        viewFactory = new ViewFactoryCS(layout);

        //space 추가
        viewFactory.addSpace(1.0f);

        //cardcover 로딩
        LayoutInflater inflater = (LayoutInflater)root.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for(int i = 0 ; i < 3; i++){
            //카드 생성
            textCard[i] = viewFactory.createCard(1.0f, new int[]{0,0,0, PageHelper.defaultMargin});
        }
        //텍스트 추가
        viewFactory.addSimpleText("프로그램은 수학적인 문제를 해결하기 위해서 만들어졌다.", 18, textCard[0]);
        viewFactory.addSimpleText("이런 의문이 들었다. 1 + 2 를 계산한 결과를 저장하고 싶은데, 컴퓨터에 어떻게 저장하지?", 18, textCard[1]);
        viewFactory.addSimpleText("그래서 이런 공간을 마련한 것이 '변수'다!", 18, textCard[2]);

        //카드로 덮기
        for(int i = 0 ; i < 3; i++){
            //card 커버 로드
            cardCover[i] = new RelativeLayout(getContext());
            inflater.inflate(R.layout.cardcover, cardCover[i]);
            //카드 눌렀을 때 카드 사라지기
            textCard[i].setTag(i);
            textCard[i].setOnClickListener(new onCardClicked());
            //card 로 덮기
            textCard[i].addView(cardCover[i]);
        }

        //space 추가
        viewFactory.addSpace(1.0f);
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
