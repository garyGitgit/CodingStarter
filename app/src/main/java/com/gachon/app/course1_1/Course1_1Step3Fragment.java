package com.gachon.app.course1_1;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.Toast;

import com.gachon.app.R;
import com.gachon.app.helper.ViewFactoryCS;
import com.gachon.app.helper.WidgetSet;

import java.util.ArrayList;


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
        TableLayout tableCard1 = viewFactory.createTableCard(2.0f, Color.WHITE, new int[]{0,0,0,20});

        View[] rowViews = {
                viewFactory.createWidget("Spinner", new String[]{"int", "float", "char"}),
                viewFactory.createWidget("EditText", new String[]{"num"}),
                viewFactory.createWidget("TextView", new String[]{"="}),
                viewFactory.createWidget("EditText", new String[]{"5"}),
                viewFactory.createWidget("TextView", new String[]{";"})};
        viewFactory.addRow(rowViews, tableCard1);

        rowViews = new View[]{
                viewFactory.createWidget("Spinner", new String[]{"int", "float", "char"}),
                viewFactory.createWidget("EditText", new String[]{"num"}),
                viewFactory.createWidget("TextView", new String[]{"="}),
                viewFactory.createWidget("EditText", new String[]{"5"}),
                viewFactory.createWidget("TextView", new String[]{";"})};
        viewFactory.addRow(rowViews, tableCard1);

        rowViews = new View[]{
                viewFactory.createWidget("Spinner", new String[]{"int", "float", "char"}),
                viewFactory.createWidget("EditText", new String[]{"num"}),
                viewFactory.createWidget("TextView", new String[]{"="}),
                viewFactory.createWidget("EditText", new String[]{"5"}),
                viewFactory.createWidget("TextView", new String[]{";"})};
        viewFactory.addRow(rowViews, tableCard1);

        //결과 블록 카드
        final LinearLayout resultCard = viewFactory.createCard(1.0f, Color.WHITE, true, new int[]{0,0,0,20});

        //사용자 입력 블록 카드
        TableLayout tableCard2 = viewFactory.createTableCard(0.0f, Color.WHITE, new int[]{0,0,0,20});

        //테이블카드를 꽉 채우도록 함
        tableCard2.setStretchAllColumns(true);

        //새로고침, 제출하기 버튼 추가
        Button buttonRefresh = (Button) viewFactory.createWidget("Button", new String[]{"새로고침"});
        Button buttonSubmit = (Button) viewFactory.createWidget("Button", new String[]{"제출하기"});
        rowViews = new View[]{ buttonRefresh, buttonSubmit };
        viewFactory.addRow(rowViews, tableCard2);

        //제출하기를 누르면 editText 에 있는 값들이 resultCard 에 보여짐
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //이 페이지에 있는 widget set 를 가져옴
                WidgetSet widgetSet = viewFactory.getWidgetSet();
                //이 widget set 을 바탕으로 resultCard 에 반영
                show(widgetSet, resultCard);
            }
        });
    }

    /**
     * resultCard 에 widgetSet 의 내용들을 뿌려줌
     *
     * @param widgetSet : 이 페이지에 있는 widget들
     * @param resultCard : 사용자가 입력한 값을 보여주는 resultCard
     */
    public void show(WidgetSet widgetSet, LinearLayout resultCard){
        //resultCard 에 기존에 있던 위젯들을 제거
        resultCard.removeAllViews();

        ArrayList<EditText> editTextList = widgetSet.getEditText();
        String str = "";
        boolean toggle = false;

        //editText 에서 사용자가 입력한 값들 가져옴
        for (EditText editText : editTextList) {
            str += editText.getText().toString();
            if(str.equals("")) Toast.makeText(root.getContext(), "빈칸이 있습니다!", Toast.LENGTH_SHORT).show();

            //TODO 텍스트말고 다른 방법으로 보여줄 방법?
            if(toggle) {
                //TODO 변수의 이름을 필터해야함 (변수 룰을 지켜야함)
                viewFactory.addSimpleText(str, 20, resultCard);
                toggle = false;
                str = ""; //초기화
            }
            else {
                str += " = ";
                toggle = true;
            }
        }
//        Spinner[] spinnerList = (Spinner[])widgetSet.getSpinner().toArray();
//        int size = editTestList.length;
//        for(int i = 0 ; i < size; i++){
//            String str = spinnerList[i].getSelectedItem() + " ";
//            str += editTestList[i].getText().toString();
//            viewFactory.addSimpleText(str, 15, resultCard);
//        }
    }
}
