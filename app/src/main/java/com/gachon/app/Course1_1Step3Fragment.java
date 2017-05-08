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
 * A simple {@link Fragment} subclass.
 */
public class Course1_1Step3Fragment extends Fragment {
    //항상 추가
    View root;
    ViewFactoryCS viewFactory;

    public Course1_1Step3Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_course1_1_step3, container, false);

        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewFactory = new ViewFactoryCS();

        LinearLayout layout = (LinearLayout) root.findViewById(R.id.fragment_course1_1_step3);

        //카드뷰 생성 (linear layout 을 기본으로 한다, vertical, horizontal 설정도 고려해보자 )
        TableLayout table1 = viewFactory.createTableCardView(2.0f, layout, this, Color.GREEN, true);

        View[] rowViews = {
                viewFactory.getWidget("Spinner", new String[]{"int", "float", "char"}, table1),
                viewFactory.getWidget("EditText", new String[]{"num"}, table1),
                viewFactory.getWidget("TextView", new String[]{"="}, table1),
                viewFactory.getWidget("EditText", new String[]{"5"}, table1),
                viewFactory.getWidget("TextView", new String[]{";"}, table1)};
        viewFactory.addRow(rowViews, table1);



        rowViews = new View[]{
                viewFactory.getWidget("Spinner", new String[]{"int", "float", "char"}, table1),
                viewFactory.getWidget("EditText", new String[]{"num"}, table1),
                viewFactory.getWidget("TextView", new String[]{"="}, table1),
                viewFactory.getWidget("EditText", new String[]{"5"}, table1),
                viewFactory.getWidget("TextView", new String[]{";"}, table1)};
        viewFactory.addRow(rowViews, table1);

        rowViews = new View[]{
                viewFactory.getWidget("Spinner", new String[]{"int", "float", "char"}, table1),
                viewFactory.getWidget("EditText", new String[]{"num"}, table1),
                viewFactory.getWidget("TextView", new String[]{"="}, table1),
                viewFactory.getWidget("EditText", new String[]{"5"}, table1),
                viewFactory.getWidget("TextView", new String[]{";"}, table1)};
        viewFactory.addRow(rowViews, table1);

        LinearLayout card1 = viewFactory.createCardView(1.0f, layout, this, Color.BLUE, true);

        TableLayout buttonTable = viewFactory.createTableCardView(0.0f, layout, this, Color.CYAN, true);
        buttonTable.setStretchAllColumns(true);
        rowViews = new View[]{
                viewFactory.getWidget("Button", new String[]{"새로고침"}, buttonTable),
                viewFactory.getWidget("Button", new String[]{"제출하기"}, buttonTable)
        } ;
        viewFactory.addRow(rowViews, buttonTable);
    }
}
