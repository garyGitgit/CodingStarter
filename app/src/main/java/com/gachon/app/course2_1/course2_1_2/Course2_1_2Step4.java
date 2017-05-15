package com.gachon.app.course2_1.course2_1_2;


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

import com.gachon.app.R;
import com.gachon.app.helper.PageHelper;
import com.gachon.app.helper.ViewFactoryCS;


/**
 * A simple {@link Fragment} subclass.
 */
public class Course2_1_2Step4 extends Fragment {

    View root;
    ViewFactoryCS viewFactory;

    public Course2_1_2Step4() {
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

        //최상단 루트 레이아웃
        LinearLayout layout = (LinearLayout) root.findViewById(R.id.fragment_g_step4);
        viewFactory = new ViewFactoryCS(layout);


        //문제 카드
        LinearLayout questionCard = viewFactory.createCard(0.0f, Color.WHITE, false, new int[]{0,0,0, PageHelper.defaultMargin});
        viewFactory.addSimpleText("Q. 다음 중 if ( **** ) 조건문에 들어가는 조건이 아닌 것은?", 20, questionCard);

        LinearLayout answerCard = viewFactory.createCard(0.0f, Color.WHITE, false, new int[]{0,0,0, PageHelper.defaultMargin});
        RadioGroup radioGroup = (RadioGroup) viewFactory.createWidget("RadioButton",
                new String[]{"5 > 4", "5 == 3", "!true", "int a"});

        viewFactory.addView(radioGroup, answerCard);

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
