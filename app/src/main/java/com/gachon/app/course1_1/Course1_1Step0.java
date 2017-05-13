package com.gachon.app.course1_1;


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
public class Course1_1Step0 extends Fragment {

    //항상 추가
    View root; // 부모 액티비티
    ViewFactoryCS viewFactory;
    public Course1_1Step0() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //항상 추가
        root = inflater.inflate(R.layout.fragment_course1_1_step0, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //최상단 루트 레이아웃
        LinearLayout layout = (LinearLayout) root.findViewById(R.id.fragment_course1_1_step0);
        viewFactory = new ViewFactoryCS(layout);

        //이해를 돕는 이미지 카드 생성
        LinearLayout imageCard = viewFactory.createCard(1.0f, Color.WHITE, true, new int[]{0,0,0, PageHelper.defaultMargin});
        viewFactory.addImage(getResources().getDrawable(R.drawable.shoebox), imageCard);

        //변수의 필요성에 대한 카드 생성
        LinearLayout textCard1 = viewFactory.createCard(1.0f, Color.WHITE, true, new int[]{0,0,0, PageHelper.defaultMargin});

        viewFactory.addSimpleText("들어가기", 20, textCard1);
        viewFactory.addSimpleText(
                        "철수는 성남시 수정구 성남대로 1342에 산다.\n" +
                                "철수는 성남시 수정구 성남대로 1342에서 아침에 일어나 씻고 밥을 먹는다.\n" +
                                "철수는 성남시 수정구 성남대로 1342에서 나와 학교를 간다.\n" +
                                "방과 후 철수는 성남시 수정구 성남대로 1342 에 가서 공부를 한다\n", 18, textCard1);
    }

}
