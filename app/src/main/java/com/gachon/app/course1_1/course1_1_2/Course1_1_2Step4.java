package com.gachon.app.course1_1.course1_1_2;


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
import com.gachon.app.helper.ViewFactoryCS;


/**
 * course 1-1 데이터 타입 / 변수 / 초기화
 * step 4 : 문제 풀이
 */
public class Course1_1_2Step4 extends Fragment {
    //항상 추가
    View root;
    ViewFactoryCS viewFactory;

    // Required empty public constructor
    public Course1_1_2Step4() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_course1_1_2step4, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayout layout = (LinearLayout) root.findViewById(R.id.fragment_course1_1_2step4);
        viewFactory = new ViewFactoryCS(layout);

        //문제를 제시하는 카드 : 카드에 들어갈 위젯 또는 텍스트를 배치
        LinearLayout problemCard = viewFactory.createCard(0.0f, Color.WHITE, true, new int[]{0,0,0,PageHelper.defaultMargin});
        viewFactory.addSimpleText("정수형 변수를 선언하고, 45로 초기화시키시오.", 20 ,problemCard);

        //입력한 답이 보여지는 카드 : 사용자 입력 block 이 배치되는 카드
        LinearLayout answerCard = viewFactory.createCard(1.0f, Color.WHITE, false, new int[]{0,0,0,PageHelper.defaultMargin});

        //보기를 보여주는 카드 : 탭 block 이 배치되는 카드

        //TableLayout blockCard = viewFactory.createTableCard(0.0f, Color.WHITE, new int[]{0,0,0,20});
        HorizontalScrollView scrollView = viewFactory.createHorizontalScrollViewCard(0.0f, Color.WHITE, new int[]{0,0,0,PageHelper.defaultMargin});
        //block 생성
        viewFactory.createBlocks(
                new String[]{"int", "45", "float", "num", "char", ";"}, scrollView, answerCard, 1
        );

        //새로고침과 제출버튼 카드
        TableLayout tableCard2 = viewFactory.createTableCard(0.0f, Color.WHITE, new int[]{0,0,0, PageHelper.defaultMargin});

        //새로고침, 제출하기 버튼 추가
        Button buttonRefresh = (Button) viewFactory.createWidget("Button", new String[]{""});
        buttonRefresh.setBackground(getResources().getDrawable(android.R.drawable.ic_menu_delete));
        Button buttonSubmit = (Button) viewFactory.createWidget("Button", new String[]{""});
        buttonSubmit.setBackground(getResources().getDrawable(android.R.drawable.ic_media_play));
        View[] rowViews = new View[]{ buttonRefresh, buttonSubmit };

        tableCard2.setStretchAllColumns(true);
        viewFactory.addRow(rowViews, tableCard2);

        //정답 비교
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
