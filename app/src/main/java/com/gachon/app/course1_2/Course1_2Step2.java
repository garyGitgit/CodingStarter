package com.gachon.app.course1_2;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TableLayout;

import com.gachon.app.R;
import com.gachon.app.helper.PageHelper;
import com.gachon.app.helper.TabCodingInterface;
import com.gachon.app.helper.ViewFactoryCS;
import com.gachon.app.helper.WidgetSet;

/**
 * course 1-2 연산자
 * step 2 : 연산자 종류 문제 풀이
 */

public class Course1_2Step2 extends Fragment implements TabCodingInterface{

    View root;
    ViewFactoryCS viewFactory;

    public Course1_2Step2() {
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
        root = inflater.inflate(R.layout.fragment_course1_1_2step2, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //최상단 루트 레이아웃
        LinearLayout layout = (LinearLayout) root.findViewById(R.id.fragment_course1_1_2step2);
        viewFactory = new ViewFactoryCS(layout);

        //문제 카드 생성
        LinearLayout questionCard = viewFactory.createCard(0.0f, Color.WHITE, false, new int[]{0,0,0, PageHelper.defaultMargin});
        viewFactory.addSimpleText("Q. 다음 식을 보고 빈칸에 적절한 연산자를 넣으시오. ", 20, questionCard);

        //사용자 입력 카드 생성
        //LinearLayout userInputCard = viewFactory.createCard(1.0f, Color.WHITE, true, new int[]{0,0,0, PageHelper.defaultMargin});
        TableLayout userInputCard = viewFactory.createTableCard(1.0f, Color.WHITE, new int[]{0,0,0, PageHelper.defaultMargin});
//        viewFactory.addSimpleText("1. 4 [[+]] 5 = 9", 20, userInputCard);
//        viewFactory.addSimpleText("2. 6 [[*]] 2 = 12", 20, userInputCard);
//        viewFactory.addSimpleText("3. 72 [[-]] 44 = 28", 20, userInputCard);
//        viewFactory.addSimpleText("4. 22 [[/]] 2 = 11", 20, userInputCard);
//        viewFactory.addSimpleText("5. 6 [[==]] 6 = True", 20, userInputCard);
        viewFactory.addQuestion("1. 4 [[+]] 5 = 9", 20, userInputCard);
        viewFactory.addQuestion("2. 6 [[*]] 2 = 12", 20, userInputCard);
        viewFactory.addQuestion("3. 72 [[-]] 44 = 28", 20, userInputCard);
        viewFactory.addQuestion("4. 22 [[/]] 2 = 11", 20, userInputCard);
        viewFactory.addQuestion("5. 6 [[==]] 6 = True", 20, userInputCard);


        //보기 블록 카드 생성
        //TableLayout blockCard = viewFactory.createTableCard(0.0f, Color.WHITE, new int[]{0,0,0, PageHelper.defaultMargin});
        HorizontalScrollView scrollView = viewFactory.createScrollViewCard(0.0f, Color.WHITE, new int[]{0,0,0, PageHelper.defaultMargin});
        viewFactory.createBlocks(new String[]{"+", "-", "*", "/", "%", "==", ";"}, scrollView, userInputCard, 2);

        //사용자 입력 블록 카드
        TableLayout tableCard = viewFactory.createTableCard(0.0f, Color.WHITE, new int[]{0,0,0,20});

        //테이블카드를 꽉 채우도록 함
        tableCard.setStretchAllColumns(true);

        //새로고침, 제출하기 버튼 추가
        Button buttonRefresh = (Button) viewFactory.createWidget("Button", new String[]{"새로고침"});
        Button buttonSubmit = (Button) viewFactory.createWidget("Button", new String[]{"제출하기"});
        View[] rowViews = new View[]{ buttonRefresh, buttonSubmit };
        viewFactory.addRow(rowViews, tableCard);

        //제출하기를 누르면 editText 에 있는 값들이 resultCard 에 보여짐
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
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
