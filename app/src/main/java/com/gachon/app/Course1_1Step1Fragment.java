package com.gachon.app;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class Course1_1Step1Fragment extends Fragment {
    //항상 추가
    View root;
    ViewFactoryCS viewFactory;

    public Course1_1Step1Fragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //항상 추가
        root = inflater.inflate(R.layout.fragment_course1_1_step1, container, false);
        return root;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewFactory = new ViewFactoryCS();

        LinearLayout layout = (LinearLayout) root.findViewById(R.id.fragment_course1_1_step1);

        //카드뷰 생성 (linear layout 을 기본으로 한다, vertical, horizontal 설정도 고려해보자 )
        LinearLayout linearLayout1 = viewFactory.createCardView(1.0f, layout, this, Color.GREEN, false);
        LinearLayout linearLayout2 = viewFactory.createCardView(0.7f, layout, this, Color.WHITE, false);
        LinearLayout linearLayout3 = viewFactory.createCardView(3.0f, layout, this, Color.RED, true);

        //카드뷰에 다른 뷰 생성
//        viewFactory.addText("boston dynamics", linearLayout1);
//        페이드인 효과 적용 텍스트
        viewFactory.addText("새 학기가 시작되자 A는 신발장을 배정받습니다." +
                "신발장은 신발만 넣을 수 있는 용도이고, A는 신발장에 신발을 넣습니다." +
                "신발을 넣기 위해 만들어진 공간을 신발장이라고 부르고, 프로그래밍에서는 변수라고 부릅니다." +
                "신발장에는 신발만 넣을 수 있기 때문에 이것을 데이터 타입이라고 부릅니다." +
                "그리고 신발을 신발장에 넣는 행동을 초기화라고 합니다.", 15 ,linearLayout3);

        viewFactory.addText("*변수 = 공간\n*", 20 ,linearLayout3);
        viewFactory.addText("*데이터 타입 = 공간의 용도(신발장)\n*", 20 ,linearLayout3);
        viewFactory.addText("*초기화 = 공간에 값을 설정해주는 행위(신발장에 신발을 넣는 행위)\n*", 20 ,linearLayout3);

        //위젯추가
        //viewFactory.addWidget("Button", "dynamic button", linearLayout1);
        //viewFactory.addImage(getResources().getDrawable(R.drawable.codingstarter_logo), linearLayout1);

        //이미지 추가
        viewFactory.addImage(100, 100, getResources().getDrawable(R.drawable.shoebox), linearLayout1);
        viewFactory.addImage(100, 100, getResources().getDrawable(R.drawable.shoebox), linearLayout1);
        viewFactory.addImage(100, 100, getResources().getDrawable(R.drawable.shoebox), linearLayout1);
        //이미지 추가 2
        viewFactory.addImage(100, 100, getResources().getDrawable(R.drawable.shoe), linearLayout2);
        viewFactory.addImage(100, 100, getResources().getDrawable(R.drawable.shoe), linearLayout2);
        viewFactory.addImage(100, 100, getResources().getDrawable(R.drawable.shoe), linearLayout2);



    }
}
