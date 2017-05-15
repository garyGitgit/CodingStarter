package com.gachon.app.course1_2.course1_2_1;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gachon.app.R;
import com.gachon.app.helper.PageHelper;
import com.gachon.app.helper.TabCodingInterface;
import com.gachon.app.helper.ViewFactoryCS;
import com.gachon.app.helper.WidgetSet;

import java.util.ArrayList;

/**
 * course 1-2 연산자
 * step 2 : 연산자 종류 문제 풀이
 */

public class Course1_2_1Step2 extends Fragment implements TabCodingInterface{

    View root;
    ViewFactoryCS viewFactory;

    public Course1_2_1Step2() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_g_step2, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //최상단 루트 레이아웃
        LinearLayout layout = (LinearLayout) root.findViewById(R.id.fragment_g_step2);
        viewFactory = new ViewFactoryCS(layout);

        //문제 카드 생성
        LinearLayout questionCard = viewFactory.createCard(0.0f, Color.WHITE, false, new int[]{0,0,0, PageHelper.defaultMargin});
        viewFactory.addSimpleText("Q. 다음 식을 보고 빈칸에 적절한 연산자를 넣으시오. ", 20, questionCard);

        //사용자 입력 카드 생성
        TableLayout userInputCard = viewFactory.createTableCard(1.0f, Color.WHITE, new int[]{0,0,0,0});
        viewFactory.addQuestion("1. 4 [[+]] 5 = 9", 20, userInputCard);
        viewFactory.addQuestion("2. 6 [[*]] 2 = 12", 20, userInputCard);
        viewFactory.addQuestion("3. 72 [[-]] 44 = 28", 20, userInputCard);
        viewFactory.addQuestion("4. 22 [[/]] 2 = 11", 20, userInputCard);
        viewFactory.addQuestion("5. 6 [[==]] 6 = True", 20, userInputCard);

        final LinearLayout answerCheckLayout = viewFactory.createCard(0.0f, Color.WHITE, false, new int[]{0,0,0,PageHelper.defaultMargin});
        LinearLayout linearLayout = new LinearLayout(getContext());
        viewFactory.addView(linearLayout, answerCheckLayout);

        //answercheckwithadd 동적으로 인플레이트
        LayoutInflater inflater = (LayoutInflater)root.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.answercheck, linearLayout);

        ImageButton buttonRefresh = (ImageButton)root.findViewById(R.id.button_delete);
        ImageButton buttonCompile = (ImageButton)root.findViewById(R.id.button_compile);

        //새로고침 버튼 누르면 editText 모두 제거
        buttonRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WidgetSet widgetSet = viewFactory.getWidgetSet();
                ArrayList<TextView> textViews = widgetSet.getTextView();
                for(TextView textView : textViews){
                    //초기화시킨다
                    textView.setText("          ");
                    //remainlist 에 추가한다
                    viewFactory.sortByTag(textView);
                }

            }
        });

        //제출하기를 누르면 editText 에 있는 값들이 resultCard 에 보여짐
        buttonCompile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "성공입니다!", Toast.LENGTH_SHORT).show();
            }
        });


        //보기 블록 카드 생성
        HorizontalScrollView scrollView = viewFactory.createHorizontalScrollViewCard(0.0f, Color.WHITE, new int[]{0,0,0, PageHelper.defaultMargin});
        viewFactory.createBlocks(new String[]{"+", "-", "*", "/", "%", "==", ";"}, scrollView, userInputCard, 2);

    }

    /**
     * resultCard 에 widgetSet 의 내용들을 뿌려줌
     *
     * @param widgetSet : 이 페이지에 있는 widget들
     * @param resultCard : 사용자가 입력한 값을 보여주는 resultCard
     */
    public void show(WidgetSet widgetSet, LinearLayout resultCard){


    }


}
