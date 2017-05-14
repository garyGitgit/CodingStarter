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
 * A simple {@link Fragment} subclass.
 */
public class Course1_1_1Step0 extends Fragment {

    //항상 추가
    View root; // 부모 액티비티
    ViewFactoryCS viewFactory;
    public Course1_1_1Step0() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //항상 추가
        root = inflater.inflate(R.layout.fragment_g_step0, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //최상단 루트 레이아웃
        LinearLayout layout = (LinearLayout) root.findViewById(R.id.fragment_g_step0);
        viewFactory = new ViewFactoryCS(layout);



        //변수의 필요성에 대한 카드 생성
        LinearLayout textCard1 = viewFactory.createCard(1.0f, Color.WHITE, true, new int[]{0,0,0, PageHelper.defaultMargin});
        viewFactory.addSimpleText("프로그램은 수학적인 문제를 해결하기 위해서 만들어졌다.", 18, textCard1);

        LinearLayout textCard2 = viewFactory.createCard(1.0f, Color.WHITE, true, new int[]{0,0,0, PageHelper.defaultMargin});
        viewFactory.addSimpleText("이런 의문이 들었다. 1 + 2 를 계산한 결과를 저장하고 싶은데, 컴퓨터에 어떻게 저장하지?", 18, textCard2);

        LinearLayout textCard3 = viewFactory.createCard(1.0f, Color.WHITE, true, new int[]{0,0,0, PageHelper.defaultMargin});
        viewFactory.addSimpleText("그래서 이런 공간을 마련한 것이 '변수'다!", 18, textCard3);
    }

}
