package com.gachon.app.course1_1.course1_1_1;


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
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gachon.app.R;
import com.gachon.app.helper.PageHelper;
import com.gachon.app.helper.ViewFactoryCS;
import com.gachon.app.helper.WidgetSet;

import java.util.ArrayList;


/**
 * course 1-1 데이터 타입 / 변수 / 초기화
 * step 3 : 직접 선언 체험
 */
public class Course1_1_1Step3 extends Fragment{
    //항상 추가
    View root;
    ViewFactoryCS viewFactory;

    // Required empty public constructor
    public Course1_1_1Step3() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_course1_1_1step3, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayout layout = (LinearLayout) root.findViewById(R.id.fragment_course1_1_1step3);
        viewFactory = new ViewFactoryCS(layout);

        LinearLayout headerCard = viewFactory.createCard(0.0f, Color.WHITE, false, new int[]{0,0,0, PageHelper.defaultMargin});
        viewFactory.addSimpleText("변수를 선언해보자 ", 20, headerCard);

        //카드뷰 생성 (linear layout 을 기본으로 한다, vertical, horizontal 설정도 고려해보자 )
        //final TableLayout tableCard1 = viewFactory.createTableCard(2.0f, Color.WHITE, new int[]{0,0,0,0});
        ScrollView questionCard = viewFactory.createVerticalScrollViewCard(1.0f, Color.WHITE, new int[]{0,0,0,0});
        final TableLayout tableCard1 = new TableLayout(getContext());
        TableLayout.LayoutParams params = new TableLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f);
        tableCard1.setLayoutParams(params);


        View[] rowViews = {
                viewFactory.createWidget("Spinner", new String[]{"int", "float", "char"}),
                viewFactory.createWidget("EditText", new String[]{"num"}),
                viewFactory.createWidget("TextView", new String[]{";"})};
        viewFactory.addRow(rowViews, tableCard1);

        rowViews = new View[]{
                viewFactory.createWidget("Spinner", new String[]{"int", "float", "char"}),
                viewFactory.createWidget("EditText", new String[]{"num"}),
                viewFactory.createWidget("TextView", new String[]{";"})};
        viewFactory.addRow(rowViews, tableCard1);

        rowViews = new View[]{
                viewFactory.createWidget("Spinner", new String[]{"int", "float", "char"}),
                viewFactory.createWidget("EditText", new String[]{"num"}),
                viewFactory.createWidget("TextView", new String[]{";"})};

        viewFactory.addRow(rowViews, tableCard1);

        questionCard.addView(tableCard1);

        //사용자 입력 블록 카드
        TableLayout tableCard2 = viewFactory.createTableCard(0.0f, Color.WHITE, new int[]{0,0,0,PageHelper.defaultMargin});

        //테이블카드를 꽉 채우도록 함
        tableCard2.setStretchAllColumns(true);

        //새로고침, 제출하기 버튼 추가
        Button buttonRefresh = (Button) viewFactory.createWidget("Button", new String[]{""});
        buttonRefresh.setBackground(getResources().getDrawable(android.R.drawable.ic_menu_delete));
        Button buttonAdd = (Button) viewFactory.createWidget("Button", new String[]{""});
        buttonAdd.setBackground(getResources().getDrawable(android.R.drawable.ic_menu_add));
        Button buttonSubmit = (Button) viewFactory.createWidget("Button", new String[]{""});
        buttonSubmit.setBackground(getResources().getDrawable(android.R.drawable.ic_media_play));
        rowViews = new View[]{ buttonRefresh, buttonAdd, buttonSubmit };
        viewFactory.addRow(rowViews, tableCard2);

        //결과 블록 카드
        final LinearLayout resultCard = viewFactory.createCard(1.0f, Color.WHITE, false, new int[]{0,0,0,PageHelper.defaultMargin});

        //새로고침을 누르면 editText 에 있는 값들이 모두 없어짐
        buttonRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //editText 내용 제거
                WidgetSet widgetSet = viewFactory.getWidgetSet();
                ArrayList<EditText> editTextList = widgetSet.getEditText();
                for (EditText editText : editTextList){
                    editText.setText("");
                }
                //resultCard 이미지 제거
                resultCard.removeAllViews();

            }
        });

        //TODO 제거하기 버튼도 필요
        //추가하기 버튼을 누르면 editText 를 추가한다
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View[] rowViews = {
                        viewFactory.createWidget("Spinner", new String[]{"int", "float", "char"}),
                        viewFactory.createWidget("EditText", new String[]{"num"}),
                        viewFactory.createWidget("TextView", new String[]{";"})};
                viewFactory.addRow(rowViews, tableCard1);
            }
        });

       //제출하기를 누르면 editText 에 있는 값들이 resultCard 에 보여짐
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //답이 맞는지 판단 : 변수가 반드시 대소문자 또는 _ 로 시작하고 변수는 문자 + 숫자로만 구성이 된다

                //이 페이지에 있는 widget set 를 가져옴
                WidgetSet widgetSet = viewFactory.getWidgetSet();
                ArrayList<EditText> editTextList = widgetSet.getEditText();
                String[] userInputs = new String[editTextList.size()];
                String errorMessage = "";
                boolean isSuccess = true;
                int i = 0;
                for(EditText editText : editTextList){
                    //사용자가 입력한 변수를 가져온다
                    userInputs[i] = editText.getText().toString();
                    if(!checkVariableValidity(userInputs[i])){
                        isSuccess = false;
                        Toast.makeText(getContext(), "변수 이름 설정 에러", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    i++;
                }
                if(isSuccess){
                    //이 widget set 을 바탕으로 resultCard 에 반영 : 잘못된
                    show(widgetSet, userInputs, resultCard);
                }
            }
        });
    }

    /**
     * TODO : 변수가 문자 또는 _ 로 시작을 하고 문자,_와 숫자로만 구성되어있는지 확인하는 자바 모듈 (yj)
     *
     * @param str : 판단할 문자열
     * @return : true : 변수로 사용될 수 있으면/ false : 변수로 사용될 수 없으면
     */
    boolean checkVariableValidity(String str) {
        if(str.equals("")) return false;

        int size = str.length();
        char v;
        //첫 문자 확인 (숫자 허용안됨)
        v = str.charAt(0);
        if( !( (('A'<=v) && (v>='Z')) || (('a'<=v) && (v>='z')) || (v=='_')))
            return false;
        //문자가 하나면 true 리턴
        if(size <= 1) return true;

        //두 번째 문자 확인
        int i=1;
        while(true) {
            v = str.charAt(i);
            System.out.println(v);

            if( (('A'<=v) && (v<='Z')) || (('a'<=v) && (v<='z')) || ('0'<=v)&&(v<='9') || (v=='_') )
                i++;
            else
                return false;
            if (i==str.length())
                break;
        }
        return true;
    }

    /**
     *
     * @param widgetSet : 이 페이지에 있는 widget들
     * @param userInputs
     * @param resultCard : 사용자가 입력한 값을 보여주는 resultCard
     */
    public void show(WidgetSet widgetSet, String[] userInputs, LinearLayout resultCard){
        //resultCard 에 기존에 있던 위젯들을 제거
        resultCard.removeAllViews();


//        String str = "";
//        boolean toggle = false;

        //결과 string 을 가져온다

//        int i = 0;
//        //editText 에서 사용자가 입력한 값들 가져옴
//        for (EditText editText : editTextList) {
//            str += editText.getText().toString();
//            if(str.equals("")) Toast.makeText(root.getContext(), "빈칸이 있습니다!", Toast.LENGTH_SHORT).show();
//
//            //TODO 텍스트말고 다른 방법으로 보여줄 방법?
//            if(toggle) {
//                //TODO 변수의 이름을 필터해야함 (변수 룰을 지켜야함)
//                //viewFactory.addSimpleText(str, 20, resultCard);
//                userInputs[i++] = str;
//                toggle = false;
//                str = ""; //초기화
//            }
//            else {
//                str += " = ";
//                toggle = true;
//            }
//        }

        int i = 0;

        ArrayList<Spinner> spinnersList = widgetSet.getSpinner();
        for(Spinner spinner : spinnersList){
            String result = spinner.getSelectedItem() + " "+ userInputs[i++];
            //load image
            TextView textView = (TextView)viewFactory.createWidget("TextView", new String[]{result});
            textView.setBackground(getResources().getDrawable(R.drawable.shoebox));
            textView.setGravity(View.TEXT_ALIGNMENT_CENTER);
            viewFactory.addView(textView, resultCard);
        }
    }
}
