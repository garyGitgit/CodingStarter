package com.gachon.app.course1_1.course1_1_2;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gachon.app.R;
import com.gachon.app.helper.GrammarChecker;
import com.gachon.app.helper.PageHelper;
import com.gachon.app.helper.ViewFactoryCS;
import com.gachon.app.helper.WidgetSet;

import java.util.ArrayList;


/**
 * course 1-1 데이터 타입 / 변수 / 초기화
 * step 3 : 직접 선언 체험
 */
public class Course1_1_2Step3 extends Fragment{
    //항상 추가
    View root;
    ViewFactoryCS viewFactory;

    // Required empty public constructor

    TableLayout answerCard;
    ScrollView questionCard;
    public Course1_1_2Step3() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_g_step3, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayout layout = (LinearLayout) root.findViewById(R.id.fragment_g_step3);
        viewFactory = new ViewFactoryCS(layout);

        LinearLayout headerCard = viewFactory.createCard(0.0f, Color.WHITE, false, new int[]{0,0,0, PageHelper.defaultMargin});
        viewFactory.addSimpleText("변수를 선언하고 초기화 해보자", 20, headerCard);

        //카드뷰 생성 (linear layout 을 기본으로 한다, vertical, horizontal 설정도 고려해보자 )

        questionCard = viewFactory.createVerticalScrollViewCard(1.0f, Color.WHITE, new int[]{0,0,0,0});

        answerCard = new TableLayout(getContext());
        TableLayout.LayoutParams params = new TableLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f);
        answerCard.setLayoutParams(params);


        View[] rowViews = {
                viewFactory.createWidget("Spinner", new String[]{"int", "float", "char"}),
                viewFactory.createWidget("EditText", new String[]{"num"}),
                viewFactory.createWidget("TextView", new String[]{"="}),
                viewFactory.createWidget("EditText", new String[]{"5"}),
                viewFactory.createWidget("TextView", new String[]{";"})};
        viewFactory.addRow(rowViews, answerCard);

        rowViews = new View[]{
                viewFactory.createWidget("Spinner", new String[]{"int", "float", "char"}),
                viewFactory.createWidget("EditText", new String[]{"num"}),
                viewFactory.createWidget("TextView", new String[]{"="}),
                viewFactory.createWidget("EditText", new String[]{"5"}),
                viewFactory.createWidget("TextView", new String[]{";"})};
        viewFactory.addRow(rowViews, answerCard);

        rowViews = new View[]{
                viewFactory.createWidget("Spinner", new String[]{"int", "float", "char"}),
                viewFactory.createWidget("EditText", new String[]{"num"}),
                viewFactory.createWidget("TextView", new String[]{"="}),
                viewFactory.createWidget("EditText", new String[]{"5"}),
                viewFactory.createWidget("TextView", new String[]{";"})};

        viewFactory.addRow(rowViews, answerCard);
        questionCard.addView(answerCard);


        LinearLayout answerCheckLayout = viewFactory.createCard(0.0f, Color.WHITE, false, new int[]{0,0,0,PageHelper.defaultMargin});
        LinearLayout linearLayout = new LinearLayout(getContext());
        viewFactory.addView(linearLayout, answerCheckLayout);

        //answercheckwithadd 동적으로 인플레이트
        LayoutInflater inflater = (LayoutInflater)root.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.answercheckwithadd, linearLayout);

        ImageButton buttonAdd = (ImageButton)root.findViewById(R.id.button_add);
        ImageButton buttonRefresh = (ImageButton)root.findViewById(R.id.button_delete);
        ImageButton buttonCompile = (ImageButton)root.findViewById(R.id.button_compile);

        
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View[] newViews = new View[]{
                        viewFactory.createWidget("Spinner", new String[]{"int", "float", "char"}),
                        viewFactory.createWidget("EditText", new String[]{"num"}),
                        viewFactory.createWidget("TextView", new String[]{"="}),
                        viewFactory.createWidget("EditText", new String[]{"5"}),
                        viewFactory.createWidget("TextView", new String[]{";"})};
                viewFactory.addRow(newViews, answerCard);
                //추가 눌렀을 때 스크롤 아래로 이동
                questionCard.fullScroll(View.FOCUS_DOWN);

            }
        });
        //새로고침 버튼을 누르면 블록들이 모두 없어진다
        //TODO : 사실 이게 별로 안 컸으면 좋겠음
        buttonRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WidgetSet widgetSet = viewFactory.getWidgetSet();
                ArrayList<EditText> editTextList = widgetSet.getEditText();
                for (EditText editText : editTextList){
                    editText.setText("");
                }
            }
        });

        //결과 블록 카드
        final LinearLayout resultCard = viewFactory.createCard(1.0f, Color.WHITE, false, new int[]{0,0,0,PageHelper.defaultMargin});


        //제출하기를 누르면 editText 에 있는 값들이 resultCard 에 보여짐
        buttonCompile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //답이 맞는지 판단 : 변수가 반드시 대소문자 또는 _ 로 시작하고 변수는 문자 + 숫자로만 구성이 된다

                //이 페이지에 있는 widget set 를 가져옴
                WidgetSet widgetSet = viewFactory.getWidgetSet();
                ArrayList<EditText> editTextList = widgetSet.getEditText();
                String[] userVariables = new String[editTextList.size()/2];
                String[] userValues = new String[editTextList.size()/2];
                String errorMessage = "";
                boolean isSuccess = true;
                int i = 0;
                for(EditText editText : editTextList){
                    if(i%2 == 0){
                        userVariables[i/2] = editText.getText().toString();
                        Log.e("garynoh", userVariables[i/2]);
                        if(!GrammarChecker.checkVariableValidity(userVariables[i/2])){
                            isSuccess = false;
                            Toast.makeText(getContext(), "변수 이름 설정 에러", Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }
                    else{
                        //사용자가 입력한 값을 가져온다
                        userValues[i/2] = editText.getText().toString();
                    }
                    i++;
                }
                if(isSuccess){
                    //이 widget set 을 바탕으로 resultCard 에 반영 : 잘못된
                    show(widgetSet, userVariables, userValues, resultCard);
                }
            }
        });
    }

    /**
     * resultCard 에 widgetSet 의 내용들을 뿌려줌
     *
     * @param widgetSet : 이 페이지에 있는 widget들
     * @param resultCard : 사용자가 입력한 값을 보여주는 resultCard
     */
    public void show(WidgetSet widgetSet, String[] userInputs, String[] userValues,LinearLayout resultCard){
        //resultCard 에 기존에 있던 위젯들을 제거
        resultCard.removeAllViews();

        int i = 0;


        //TODO 2개씩 건너서 안나옴
        ArrayList<Spinner> spinnersList = widgetSet.getSpinner();
        for(Spinner spinner : spinnersList){
            String result = spinner.getSelectedItem() + "  "+ userInputs[i] + " = " + userValues[i++];
            //load image
            TextView textView = (TextView)viewFactory.createWidget("TextView", new String[]{result});
            textView.setBackground(getResources().getDrawable(R.drawable.shoebox));
            textView.setGravity(View.TEXT_ALIGNMENT_CENTER);
            viewFactory.addView(textView, resultCard);
        }
    }



    @Override
    public void onPause() {
        Log.e("garynoh", "onpause");
        super.onPause();
        //시야에서 사라질 때
        //빈칸 만들기
        WidgetSet widgetSet = viewFactory.getWidgetSet();
        ArrayList<EditText> editTextList = widgetSet.getEditText();
        for (EditText editText : editTextList){
            editText.setText("");
        }

        //이미지 제거
        answerCard.removeAllViews();
    }
}
