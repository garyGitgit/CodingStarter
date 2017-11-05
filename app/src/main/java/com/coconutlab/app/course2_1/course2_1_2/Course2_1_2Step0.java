package com.coconutlab.app.course2_1.course2_1_2;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.coconutlab.app.R;
import com.coconutlab.app.helper.PageHelper;
import com.coconutlab.app.helper.ViewFactoryCS;

/**
 * A simple {@link Fragment} subclass.
 */
public class Course2_1_2Step0 extends Fragment {

    View root;
    ViewFactoryCS viewFactory;

    public Course2_1_2Step0() {
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

        LinearLayout textCard1 = viewFactory.createCard(1.0f, Color.WHITE, false, new int[]{0,0,0, PageHelper.defaultMargin});
        viewFactory.addSimpleText("만약이라는 것을 어떻게 표현할 수 있을까?", 25, textCard1);
    }
}
