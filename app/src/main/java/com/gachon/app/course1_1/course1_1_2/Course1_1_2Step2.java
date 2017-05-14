package com.gachon.app.course1_1.course1_1_2;


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
 * course 1-1 데이터 타입 / 변수 / 초기화
 * step 1 : 신발장을 통한 비유
 */
public class Course1_1_2Step2 extends Fragment {
    //항상 추가
    View root; // 부모 액티비티
    ViewFactoryCS viewFactory;

    // Required empty public constructor
    public Course1_1_2Step2() {}


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

        //LinearLayout headerCard = viewFactory.createCard(0.0f, Color.WHITE, true, new int[]{0,0,0,PageHelper.defaultMargin});
        LinearLayout textCard1 = viewFactory.createCard(1.0f, Color.WHITE, true, new int[]{0,0,0, PageHelper.defaultMargin});
        LinearLayout textCard2 = viewFactory.createCard(1.0f, Color.WHITE, true, new int[]{0,0,0, PageHelper.defaultMargin});
        LinearLayout textCard3 = viewFactory.createCard(1.0f, Color.WHITE, true, new int[]{0,0,0, PageHelper.defaultMargin});

        viewFactory.addSimpleText("변수의 초기화\n" +
                "변수를 선언하면서 초기화를 할 수 있다.\n" +
                "int num = 1;", 18, textCard1);

        viewFactory.addSimpleText("변수 값 재할당\n" +
                "처음에 할당했던 값을 바꿀 수 있다. 이 때, 다시 선언해주지 않아도 된다.\n" +
                "int num = 1;\n" +
                "num = 2;\n" +
                "현재 num 에 들어있는 값은 2", 18, textCard2);

        viewFactory.addSimpleText("데이터 타입과 변수\n" +
                "변수를 생성할 때 선언한 데이터 타입과 값은 일치해야한다.\n" +
                "정수형 변수 = 정수값, 실수형 변수 = 실수값, 문자형 변수 = 문자값\n" +
                "<더 알아보기>", 18, textCard3);




    }
}
