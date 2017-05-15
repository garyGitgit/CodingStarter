package com.gachon.app.course2_1.course2_1_2;


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
import android.widget.TableLayout;

import com.gachon.app.R;
import com.gachon.app.helper.PageHelper;
import com.gachon.app.helper.ViewFactoryCS;


/**
 * A simple {@link Fragment} subclass.
 */
public class Course2_1_2Step3 extends Fragment {

    View root;
    ViewFactoryCS viewFactory;

    public Course2_1_2Step3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_g_step3, container, false);
        return root;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //최상단 루트 레이아웃
        LinearLayout layout = (LinearLayout) root.findViewById(R.id.fragment_g_step3);
        viewFactory = new ViewFactoryCS(layout);

        //문제 카드
        LinearLayout questionCard = viewFactory.createCard(0.0f, Color.WHITE, false, new int[]{0,0,0, PageHelper.defaultMargin});
        viewFactory.addSimpleText("Q. 다음 연산의 예상되는 결과 값은?", 20, questionCard);

        //문제 입력 카드
        TableLayout answerCard = viewFactory.createTableCard(1.0f, Color.WHITE, new int[]{0,0,0,10});

        //문제 추가
        View[] rowViews;

        rowViews = new View[]{
                viewFactory.createWidget("TextView", new String[]{"if "}),
                viewFactory.createWidget("EditText", new String[]{"조건을 입력하세요"})
        };
        viewFactory.addRow(rowViews, answerCard);

        rowViews = new View[]{
                viewFactory.createWidget("TextView", new String[]{"     "}),
                viewFactory.createWidget("EditText", new String[]{"명령을 입력하세요"})
        };
        viewFactory.addRow(rowViews , answerCard);

        LinearLayout answerCheckLayout = viewFactory.createCard(0.0f, Color.WHITE, false, new int[]{0,0,0,PageHelper.defaultMargin});
        LinearLayout linearLayout = new LinearLayout(getContext());
        viewFactory.addView(linearLayout, answerCheckLayout);
        //answercheckwithadd 동적으로 인플레이트
        LayoutInflater inflater = (LayoutInflater)root.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.answercheckwithadd, linearLayout);

        //ImageButton buttonAdd = (ImageButton)root.findViewById(R.id.button_add);
        ImageButton buttonRefresh = (ImageButton)root.findViewById(R.id.button_delete);
        ImageButton buttonCompile = (ImageButton)root.findViewById(R.id.button_compile);

        //새로고침 버튼 누르면 editText 모두 제거
        buttonRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //제출하기를 누르면 editText 에 있는 값들이 resultCard 에 보여짐
        buttonCompile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
}
