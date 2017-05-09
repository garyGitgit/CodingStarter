package com.gachon.app.course1_2;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.gachon.app.R;
import com.gachon.app.helper.PageHelper;
import com.gachon.app.helper.ViewFactoryCS;


/**
 * course 1-2 연산자
 * step 1 : 연산자 종류 설명
 */

public class Course1_2Step1Fragment extends Fragment {

    View root;
    ViewFactoryCS viewFactory;

    public Course1_2Step1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_course1_2_step1, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //최상단 루트 레이아웃
        LinearLayout layout = (LinearLayout) root.findViewById(R.id.fragment_course1_2_step1);
        viewFactory = new ViewFactoryCS(layout);

        //연산자에 대해서 설명하는 카드 생성
        LinearLayout descriptionCard = viewFactory.createCard(1.0f, Color.WHITE, true, new int[]{0,0,0, PageHelper.defaultMargin});

        //연산자에 대해서 하나씩 소개하는 simple Text 생성
        viewFactory.addSimpleText("**더하기(+) : + **", 20, descriptionCard);
        viewFactory.addSimpleText("**빼기(-) : - **", 20, descriptionCard);
        viewFactory.addSimpleText("**곱하기(x) : * **", 20, descriptionCard);
        viewFactory.addSimpleText("**나누기(÷) : / **", 20, descriptionCard);
        viewFactory.addSimpleText("**나머지 : % **", 20, descriptionCard);
        viewFactory.addSimpleText("**등호(=) : == **", 20, descriptionCard);
        viewFactory.addSimpleText("**부등호(≠) : != **", 20, descriptionCard);
        viewFactory.addSimpleText("**할당(대입) : = **", 20, descriptionCard);
    }
}
