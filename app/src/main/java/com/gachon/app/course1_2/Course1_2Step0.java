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
import com.gachon.app.helper.ViewFactoryCS;

/**
 * A simple {@link Fragment} subclass.
 */
public class Course1_2Step0 extends Fragment {

    View root;
    ViewFactoryCS viewFactory;

    public Course1_2Step0() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_course1_1_2step1, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //최상단 루트 레이아웃
        LinearLayout layout = (LinearLayout) root.findViewById(R.id.fragment_course1_1_2step1);
        viewFactory = new ViewFactoryCS(layout);

        LinearLayout textCard1 = viewFactory.createCard(1.0f, Color.WHITE, false, new int[]{0,0,0,0});
        viewFactory.addSimpleText("더하기, 빼기, 나누기 등 연산자들은 어떻게 표현할까?", 25, textCard1);
    }
}
