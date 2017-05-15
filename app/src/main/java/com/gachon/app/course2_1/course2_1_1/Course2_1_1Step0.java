package com.gachon.app.course2_1.course2_1_1;


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
public class Course2_1_1Step0 extends Fragment {

    View root;
    ViewFactoryCS viewFactory;

    public Course2_1_1Step0() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_g_step0, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //최상단 루트 레이아웃
        LinearLayout layout = (LinearLayout) root.findViewById(R.id.fragment_g_step0);
        viewFactory = new ViewFactoryCS(layout);

        //색깔 정보
        int invalidColor = Color.WHITE;
        int validColor = getContext().getResources().getColor(R.color.codingstarter_background);

        LinearLayout textCard0 = viewFactory.createCard(1.0f, invalidColor, false, new int[]{0,0,0, PageHelper.defaultMargin});
        viewFactory.addSimpleText("프로그램이 실행되는 순서는 다음과 같이 4가지가 있다. (flow of control)", 25, textCard0);

        LinearLayout textCard1 = viewFactory.createCard(1.0f, invalidColor, false, new int[]{0,0,0,0});
        viewFactory.addSimpleText("순차형", 25, textCard1);

        LinearLayout textCard2 = viewFactory.createCard(1.0f, validColor, false, new int[]{0,0,0,0});
        viewFactory.addSimpleText("조건형", 25, textCard2);

        LinearLayout textCard3 = viewFactory.createCard(1.0f, invalidColor, false, new int[]{0,0,0,0});
        viewFactory.addSimpleText("반복형", 25, textCard3);

        LinearLayout textCard4 = viewFactory.createCard(1.0f,invalidColor, false, new int[]{0,0,0, PageHelper.defaultMargin});
        viewFactory.addSimpleText("호출형", 25, textCard4);
    }
}
