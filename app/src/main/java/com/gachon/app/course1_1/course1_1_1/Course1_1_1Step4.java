package com.gachon.app.course1_1.course1_1_1;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.gachon.app.R;
import com.gachon.app.helper.PageHelper;
import com.gachon.app.helper.ViewFactoryCS;


/**
 * course 1-1 데이터 타입 / 변수 / 초기화
 * step 4 : 문제 풀이
 */
public class Course1_1_1Step4 extends Fragment {
    //항상 추가
    View root;
    ViewFactoryCS viewFactory;

    // Required empty public constructor
    public Course1_1_1Step4() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_g_step4, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayout layout = (LinearLayout) root.findViewById(R.id.fragment_g_step4);
        viewFactory = new ViewFactoryCS(layout);

        //header text 설정
        viewFactory.createHeaderCard("확인문제", new int[]{0, 0, 0, PageHelper.headerCardMargin});


        //문제를 제시하는 카드 : 카드에 들어갈 위젯 또는 텍스트를 배치
        LinearLayout problemCard = viewFactory.createCard(0.0f, Color.WHITE, true, new int[]{0,0,0,PageHelper.defaultMargin});
        viewFactory.addSimpleText("다음 중 변수가 될 수 없는 이름은?", 20 ,problemCard);

        //보기가 나타나는 카드
        final LinearLayout answerCard = viewFactory.createCard(1.0f, Color.WHITE, false, new int[]{0,0,0,PageHelper.defaultMargin});
        RadioGroup radioGroup = (RadioGroup)viewFactory.createWidget("RadioButton", new String[]{"num", "1num", "num1", "_num1"});
        answerCard.addView(radioGroup);

        //결과 블록 카드
        //final LinearLayout resultCard = viewFactory.createCard(1.0f, Color.WHITE, false, new int[]{0,0,0,PageHelper.defaultMargin});
        //feedback card 추가
        final TextView feedBackTextContainer = viewFactory.createFeedBackCard(1.0f, new int[]{0,0,0,PageHelper.defaultMargin});
        viewFactory.addFeedBackText("변수의 이름을 정할 때 규칙을 잘 생각해보세요", feedBackTextContainer);


        //컴파일 버튼 카드
        LinearLayout answerCheckLayout = viewFactory.createCard(0.0f, Color.WHITE, false, new int[]{0,0,0,PageHelper.defaultMargin});
        LinearLayout linearLayout = new LinearLayout(getContext());
        viewFactory.addView(linearLayout, answerCheckLayout);
        //answercheckwithadd 동적으로 인플레이트
        LayoutInflater inflater = (LayoutInflater)root.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.answercheck, linearLayout);

        ImageButton buttonRefresh = (ImageButton)root.findViewById(R.id.button_delete);
        ImageButton buttonCompile = (ImageButton)root.findViewById(R.id.button_compile);

        //새로고침 버튼을 누르면 블록들이 모두 없어진다
        //TODO : 사실 이게 별로 안 컸으면 좋겠음
        buttonRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        buttonCompile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "정답입니다!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean isCorrect(){
        return true;
    }
}
