package com.gachon.app;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;


/**
 * course 1-1 데이터 타입 / 변수 / 초기화
 * step 3 : 직접 선언 체험
 */
public class Course1_1Step3Fragment extends Fragment {
    //항상 추가
    View root;
    ViewFactoryCS viewFactory;

    // Required empty public constructor
    public Course1_1Step3Fragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_course1_1_step3, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayout layout = (LinearLayout) root.findViewById(R.id.fragment_course1_1_step3);
        viewFactory = new ViewFactoryCS(layout);

        //카드뷰 생성 (linear layout 을 기본으로 한다, vertical, horizontal 설정도 고려해보자 )
        TableLayout tableCard1 = viewFactory.createTableCard(2.0f, Color.GREEN);

        View[] rowViews = {
                viewFactory.getWidget("Spinner", new String[]{"int", "float", "char"}),
                viewFactory.getWidget("EditText", new String[]{"num"}),
                viewFactory.getWidget("TextView", new String[]{"="}),
                viewFactory.getWidget("EditText", new String[]{"5"}),
                viewFactory.getWidget("TextView", new String[]{";"})};
        viewFactory.addRow(rowViews, tableCard1);

        rowViews = new View[]{
                viewFactory.getWidget("Spinner", new String[]{"int", "float", "char"}),
                viewFactory.getWidget("EditText", new String[]{"num"}),
                viewFactory.getWidget("TextView", new String[]{"="}),
                viewFactory.getWidget("EditText", new String[]{"5"}),
                viewFactory.getWidget("TextView", new String[]{";"})};
        viewFactory.addRow(rowViews, tableCard1);

        rowViews = new View[]{
                viewFactory.getWidget("Spinner", new String[]{"int", "float", "char"}),
                viewFactory.getWidget("EditText", new String[]{"num"}),
                viewFactory.getWidget("TextView", new String[]{"="}),
                viewFactory.getWidget("EditText", new String[]{"5"}),
                viewFactory.getWidget("TextView", new String[]{";"})};
        viewFactory.addRow(rowViews, tableCard1);

        LinearLayout card1 = viewFactory.createCard(1.0f, Color.BLUE, true);

        TableLayout tableCard2 = viewFactory.createTableCard(0.0f, Color.CYAN);

        //테이블카드를 꽉 채우도록 한다
        tableCard2.setStretchAllColumns(true);

        rowViews = new View[]{
                viewFactory.getWidget("Button", new String[]{"새로고침"}),
                viewFactory.getWidget("Button", new String[]{"제출하기"})
        } ;
        viewFactory.addRow(rowViews, tableCard2);
    }
}
