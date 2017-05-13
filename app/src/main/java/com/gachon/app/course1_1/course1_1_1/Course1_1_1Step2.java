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
        LinearLayout textCard1 = viewFactory.createCard(1.0f, Color.WHITE, true, new int[]{0,0,0, PageHelper.defaultMargin});
        LinearLayout textCard2 = viewFactory.createCard(1.0f, Color.WHITE, true, new int[]{0,0,0, PageHelper.defaultMargin});
        LinearLayout textCard3 = viewFactory.createCard(1.0f, Color.WHITE, true, new int[]{0,0,0, PageHelper.defaultMargin});

        viewFactory.addSimpleText("변수 선언하는 법", 20, headerCard);
        viewFactory.addSimpleText("(변수의 타입) (변수 이름) = (변수 초기화 값);", 15, headerCard);

        viewFactory.addSimpleText("1. 변수의 타입을 정한다", 20, textCard1);
        viewFactory.addSimpleText("- int : 정수형 (예. 1, 100, 478)", 15, textCard1);
        viewFactory.addSimpleText("- float : 실수형 (예. 1.0, 100.1, 478.23)", 15, textCard1);
        viewFactory.addSimpleText("- char : 문자형 (예. '1', 'a', 'K', '-')", 15, textCard1);

        viewFactory.addSimpleText("2. 변수 이름을 정한다", 20, textCard2);
        viewFactory.addSimpleText("변수는 대/소문자, '_' 로 시작할 수 있고, 문자와 숫자만으로 구성이된다", 15, textCard2);
        viewFactory.addSimpleText("예1) num, count, score ...", 15, textCard2);
        viewFactory.addSimpleText("예2) num123, count, _score ...", 15, textCard2);

        viewFactory.addSimpleText("3. 초기화를 한다", 20, textCard3);
        viewFactory.addSimpleText("일반적으로 선언과 동시에 초기화를 한다.\n변수 = 값", 15, textCard3);
        viewFactory.addSimpleText("예) int num = 4; ", 15, textCard3);

        //이미지 카드 생성
//        LinearLayout imageCard = viewFactory.createCard(1.0f, Color.WHITE, false, new int[]{0,0,0,PageHelper.defaultMargin});
        //LinearLayout imageCard2 = viewFactory.createCard(0.7f, Color.WHITE, false, new int[]{0,0,0,20});

//        TableLayout imageTable = viewFactory.createTableCard(0.0f, Color.WHITE, new int[]{0,0,0,20});

        //이미지 추가
//        viewFactory.addImage(getResources().getDrawable(R.drawable.shoebox), imageCard);
//        viewFactory.addImage(getResources().getDrawable(R.drawable.shoe), imageCard);


        //설명 카드 생성
//        LinearLayout descriptionCard= viewFactory.createCard(3.0f, Color.WHITE, true, new int[]{0,0,0,0});

//        viewFactory.addSimpleText("새 학기가 시작되자 A는 신발장을 배정받습니다." +
//                "신발장은 신발만 넣을 수 있는 용도이고, A는 신발장에 신발을 넣습니다." +
//                "신발을 넣기 위해 만들어진 공간을 신발장이라고 부르고, 프로그래밍에서는 변수라고 부릅니다." +
//                "신발장에는 신발만 넣을 수 있기 때문에 이것을 데이터 타입이라고 부릅니다." +
//                "그리고 신발을 신발장에 넣는 행동을 초기화라고 합니다.", 15 ,descriptionCard);
//
//        viewFactory.addSimpleText("**변수 = 공간\n**", 20 ,descriptionCard);
//        viewFactory.addSimpleText("**데이터 타입 = 공간의 용도(신발장)\n**", 20 ,descriptionCard);
//        viewFactory.addSimpleText("**초기화 = 공간에 값을 설정해주는 행위(신발장에 신발을 넣는 행위)\n**", 20 ,descriptionCard);
    }
}
