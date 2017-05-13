package com.gachon.app.course1_1.course1_1_1;


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
public class Course1_1_1Step2 extends Fragment {
    //항상 추가
    View root; // 부모 액티비티
    ViewFactoryCS viewFactory;

    // Required empty public constructor
    public Course1_1_1Step2() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //항상 추가
        root = inflater.inflate(R.layout.fragment_course1_1_1step2, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //최상단 루트 레이아웃
        LinearLayout layout = (LinearLayout) root.findViewById(R.id.fragment_course1_1_1step2);
        viewFactory = new ViewFactoryCS(layout);

        LinearLayout headerCard = viewFactory.createCard(0.0f, Color.WHITE, true, new int[]{0,0,0,PageHelper.defaultMargin});
        viewFactory.addSimpleText("변수 선언하는 법", 20, headerCard);
        viewFactory.addSimpleText("(변수의 타입) (변수 이름);", 15, headerCard);

        LinearLayout textCard1 = viewFactory.createCard(1.0f, Color.WHITE, true, new int[]{0,0,0, PageHelper.defaultMargin});
        viewFactory.addSimpleText("1. 변수의 타입을 정한다", 20, textCard1);
        viewFactory.addSimpleText("- int : 정수형 (예. 1, 100, 478)", 15, textCard1);
        viewFactory.addSimpleText("- float : 실수형 (예. 1.0, 100.1, 478.23)", 15, textCard1);
        viewFactory.addSimpleText("- char : 문자형 (예. '1', 'a', 'K', '-')", 15, textCard1);

        LinearLayout textCard2 = viewFactory.createCard(1.0f, Color.WHITE, true, new int[]{0,0,0, PageHelper.defaultMargin});
        viewFactory.addSimpleText("2. 변수 이름을 정한다", 20, textCard2);
        viewFactory.addSimpleText("" +
                "변수 이름을 지을 때는 몇 가지 규칙이 있다.\n" +
                "1. 영어 알파벳 대소문자 또는 대소문자+숫자\n" +
                "2. 첫 글자는 반드시 알파벳 대소문자\n" +
                "3. 특수문자는 '_' 만 가능(이 특수문자는 첫 글자로도 쓸 수 있다)", 15, textCard2);

        LinearLayout textCard3 = viewFactory.createCard(1.0f, Color.WHITE, true, new int[]{0,0,0, PageHelper.defaultMargin});
        viewFactory.addSimpleText("3. 세미콜론(;)", 20, textCard3);
        viewFactory.addSimpleText("세미콜론(;)은 프로그램 한 줄의 끝을 의미한다. 세미콜론이 없으면 에러가 난다.", 15, textCard3);
    }
}
