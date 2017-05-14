package com.gachon.app.course1_1.course1_1_1;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TableLayout;
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

        //문제를 제시하는 카드 : 카드에 들어갈 위젯 또는 텍스트를 배치
        LinearLayout problemCard = viewFactory.createCard(0.0f, Color.WHITE, true, new int[]{0,0,0,PageHelper.defaultMargin});
        viewFactory.addSimpleText("다음 중 변수가 될 수 없는 이름은?", 20 ,problemCard);

        //입력한 답이 보여지는 카드 : 사용자 입력 block 이 배치되는 카드
        final LinearLayout answerCard = viewFactory.createCard(1.0f, Color.WHITE, false, new int[]{0,0,0,0});

        RadioGroup radioGroup = (RadioGroup)viewFactory.createWidget("RadioButton", new String[]{"num", "1num", "num1", "_num1"});
        answerCard.addView(radioGroup);


        //보기를 보여주는 카드 : 탭 block 이 배치되는 카드

        //TableLayout blockCard = viewFactory.createTableCard(0.0f, Color.WHITE, new int[]{0,0,0,20});
//        HorizontalScrollView scrollView = viewFactory.createHorizontalScrollViewCard(0.0f, Color.WHITE, new int[]{0,0,0,PageHelper.defaultMargin});
//        //block 생성
//        viewFactory.createBlocks(
//                new String[]{"int", "45", "float", "num", "char", ";"}, scrollView, answerCard, 1
//        );

        //새로고침과 제출버튼 카드
        TableLayout tableCard2 = viewFactory.createTableCard(0.0f, Color.WHITE, new int[]{0,0,0, PageHelper.defaultMargin});

        //새로고침, 제출하기 버튼 추가
        Button buttonRefresh = (Button) viewFactory.createWidget("Button", new String[]{""});
        buttonRefresh.setBackground(getResources().getDrawable(android.R.drawable.ic_menu_delete));
        Button buttonSubmit = (Button) viewFactory.createWidget("Button", new String[]{""});
        buttonSubmit.setBackground(getResources().getDrawable(android.R.drawable.ic_media_play));
        View[] rowViews = new View[]{ buttonRefresh, buttonSubmit };

        //새로고침 버튼을 누르면 블록들이 모두 없어진다
        //TODO : 사실 이게 별로 안 컸으면 좋겠음
        buttonRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //answerCard.removeAllViews();
            }
        });
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "정답입니다!", Toast.LENGTH_SHORT).show();
            }
        });

        //제출하기 버튼을 누르면 답과 비교해서 정답 체크를 한다
//        buttonSubmit.setOnClickListener(new View.OnClickListener() {
//            //이게 콜되지않는데...
//            @Override
//            public void onClick(View v) {
//                if(isCorrect()){
//
//                }
//            }
//        });

        tableCard2.setStretchAllColumns(true);
        viewFactory.addRow(rowViews, tableCard2);

        //정답 비교
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public boolean isCorrect(){
        return true;
    }
}
