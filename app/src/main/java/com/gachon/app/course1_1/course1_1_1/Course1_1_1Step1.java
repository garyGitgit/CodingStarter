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
public class Course1_1_1Step1 extends Fragment {
    //항상 추가
    View root; // 부모 액티비티
    ViewFactoryCS viewFactory;

    // Required empty public constructor
    public Course1_1_1Step1() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //항상 추가
        root = inflater.inflate(R.layout.fragment_course1_1_1step1, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //최상단 루트 레이아웃
        LinearLayout layout = (LinearLayout) root.findViewById(R.id.fragment_course1_1_1step1);
        viewFactory = new ViewFactoryCS(layout);


        //이해를 돕는 이미지 카드 생성
        LinearLayout imageCard = viewFactory.createCard(1.0f, Color.WHITE, true, new int[]{0,0,0, PageHelper.defaultMargin});
        viewFactory.addImage(getResources().getDrawable(R.drawable.shoebox), imageCard);

        LinearLayout textCard1 = viewFactory.createCard(1.0f, Color.WHITE, true, new int[]{0,0,0, PageHelper.defaultMargin});
        LinearLayout textCard2 = viewFactory.createCard(1.0f, Color.WHITE, true, new int[]{0,0,0, PageHelper.defaultMargin});
        LinearLayout textCard3 = viewFactory.createCard(1.0f, Color.WHITE, true, new int[]{0,0,0, PageHelper.defaultMargin});

        viewFactory.addSimpleText("비유와 설명", 18, textCard1);
        viewFactory.addSimpleText("변수는 신발장과 같다. 사용하기 위한 공간이다\n", 18, textCard1);
        viewFactory.addSimpleText("" +
                "변수의 데이터 타입은 변수를 사용하기 위한 목적이다.\n" +
                "dfd", 18, textCard1);
        viewFactory.addSimpleText("변수의 이름은 변수를 사용하기 위한 이름이다\n. ", 18, textCard1);

//        LinearLayout textCard1 = viewFactory.createCard(1.0f, Color.WHITE, true, new int[]{0,0,0, PageHelper.defaultMargin});
//        LinearLayout textCard2 = viewFactory.createCard(1.0f, Color.WHITE, true, new int[]{0,0,0, PageHelper.defaultMargin});
//        LinearLayout textCard3 = viewFactory.createCard(1.0f, Color.WHITE, true, new int[]{0,0,0, PageHelper.defaultMargin});
//        LinearLayout textCard4 = viewFactory.createCard(1.0f, Color.WHITE, true, new int[]{0,0,0, PageHelper.defaultMargin});
//
//        viewFactory.addSimpleText(
//                "변수란\n"+
//                        "반복적으로 사용하는 값을 저장하는 공간\n"+
//                        "예) 철수네 = 성남시 수정구 성남대로 1342"
//                , 18, textCard1);
//
//        viewFactory.addSimpleText(
//                "변수의 선언\n"+
//                        "공간을 마련하는 것을 '선언' 이라고 부른다.\n또는 변수를 위한 공간을 '할당'한다고 한다\n"+
//                        "예) 철수가 사는 곳을 '철수네' 라고 표현하겠다."
//                , 18, textCard2);
//        viewFactory.addSimpleText(
//                "변수의 타입\n"+
//                        "공간에 들어갈 수 있는 값의 종류\n"+
//                        "예) '철수네'라고 할 수 있는 주소는 동사무소에서 발급한 주소만 인정한다."
//                , 18, textCard3);
//        viewFactory.addSimpleText(
//                "변수의 초기화\n"+
//                        "공간에 어떤 값을 대입을 하는데, 이 때 변수의 타입에 맞는 값을 넣어야 한다.\n"+
//                        "예) 앞으로 성남시 수정구 성남대로 1342를 '철수네' 라고 부르겠다."
//                , 18, textCard4);
//
    }
}
