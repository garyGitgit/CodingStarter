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
public class Course1_1Step2Fragment extends Fragment {
    //항상 추가
    View root;
    ViewFactoryCS viewFactory;

    public Course1_1Step2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_course1_1_step2, container, false);

        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewFactory = new ViewFactoryCS();

        LinearLayout layout = (LinearLayout) root.findViewById(R.id.fragment_course1_1_step2);

        //카드뷰 생성 (linear layout 을 기본으로 한다, vertical, horizontal 설정도 고려해보자 )
        LinearLayout card1 = viewFactory.createCardView(1.0f, layout, this, Color.GREEN, true);
        LinearLayout card2 = viewFactory.createCardView(1.0f, layout, this, Color.WHITE, true);
        LinearLayout card3 = viewFactory.createCardView(1.0f, layout, this, Color.RED, true);

        viewFactory.addText("데이터 타입\n", 20, card1);
        viewFactory.addText("- int : 정수형 (예. 1, 100, 478)", 15, card1);
        viewFactory.addText("- float : 실수형 (예. 1.0, 100.1, 478.23)", 15, card1);
        viewFactory.addText("- char : 문자형 (예. '1', 'a', 'K', '-')", 15, card1);

        viewFactory.addText("변수\n", 20, card2);
        viewFactory.addText("- 변수를 사용하기 위해서는 '선언' 을 해야한다.", 15, card2);
        viewFactory.addText("더 알아보기", 15, card2);

        viewFactory.addText("초기화\n", 20, card3);
        viewFactory.addText("- 변수를 선언할 때 처음 값을 지정해주는 것을 초기화라고 한다.", 15, card3);
        viewFactory.addText("- 초기화는 '변수 = 값' 으로 한다", 15, card3);
        viewFactory.addText("- 예)int num = 4; ", 15, card3);
        viewFactory.addText("주의", 15, card3);

    }
}
