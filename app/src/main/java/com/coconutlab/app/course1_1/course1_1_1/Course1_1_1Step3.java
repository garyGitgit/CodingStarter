package com.coconutlab.app.course1_1.course1_1_1;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.coconutlab.app.R;
import com.coconutlab.app.helper.AnswerManager;
import com.coconutlab.app.helper.ContentPagerListener;
import com.coconutlab.app.helper.GrammarChecker;
import com.coconutlab.app.helper.PageHelper;
import com.coconutlab.app.helper.ViewFactoryCS;
import com.coconutlab.app.helper.WidgetSet;

import java.util.ArrayList;


/**
 * course 1-1 데이터 타입 / 변수 / 초기화
 * step 3 : 직접 선언 체험
 */
public class Course1_1_1Step3 extends Fragment{
    //공통으로 적용
    View root; // 부모 액티비티
    ViewFactoryCS viewFactory;
    ScrollView questionCard;

    // Required empty public constructor
    public Course1_1_1Step3() {}

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

        //header text 설정
        viewFactory.createHeaderCard("변수를 직접 만들어보자", new int[]{0, 0, 0, PageHelper.headerCardMargin});

        //문제카드 생성
        questionCard = viewFactory.createVerticalScrollViewCard(1.0f, Color.WHITE, new int[]{0,0,0,PageHelper.defaultMargin});
        final TableLayout tableCard1 = new TableLayout(getContext());
        TableLayout.LayoutParams params = new TableLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f);
        tableCard1.setLayoutParams(params);

        //문제 한 줄
        View[] rowViews = {
                viewFactory.createWidget("Spinner", new String[]{"int", "float", "char"}),
                viewFactory.createWidget("EditText", new String[]{"num"}),
                viewFactory.createWidget("TextView", new String[]{";"})};
        //한 줄 추가
        viewFactory.addRow(rowViews, tableCard1);

        //문제 한 줄
        rowViews = new View[]{
                viewFactory.createWidget("Spinner", new String[]{"int", "float", "char"}),
                viewFactory.createWidget("EditText", new String[]{"num"}),
                viewFactory.createWidget("TextView", new String[]{";"})};
        //한 줄 추가
        viewFactory.addRow(rowViews, tableCard1);

        //문제 한 줄
        rowViews = new View[]{
                viewFactory.createWidget("Spinner", new String[]{"int", "float", "char"}),
                viewFactory.createWidget("EditText", new String[]{"num"}),
                viewFactory.createWidget("TextView", new String[]{";"})};
        //한줄 추가
        viewFactory.addRow(rowViews, tableCard1);
        questionCard.addView(tableCard1);

        //피드백 카드 추가
        final TextView feedBackTextContainer = viewFactory.createFeedBackCard(1.0f, new int[]{0,0,0,0});
        //카드에 텍스트 추가
        viewFactory.addFeedBackText(0, "빈칸에 변수 이름 규칙을 따라서 변수를 선언해주세요!", feedBackTextContainer);

        //컴파일 버튼 카드
        LinearLayout answerCheckLayout = viewFactory.createCard(0.0f, Color.WHITE, false, new int[]{0,0,0,0});
        LinearLayout linearLayout = new LinearLayout(getContext());
        viewFactory.addView(linearLayout, answerCheckLayout);

        //공간 추가
        viewFactory.addSpace(0.5f);


        //페이지 넘기기 버튼 : 4,5 - 페이지만 넘기기
        ImageButton goNext = (ImageButton)root.findViewById(R.id.goNext);
        ImageButton goPrev= (ImageButton)root.findViewById(R.id.goPrevious);

//        goNext.setOnClickListener(new ContentPageListener(5, getActivity()));
//        goPrev.setOnClickListener(new ContentPageListener(4, getActivity()));
        final ContentPagerListener contentPagerListener = new ContentPagerListener(getActivity());

        goNext.setOnClickListener(contentPagerListener);
        goPrev.setOnClickListener(contentPagerListener);


        //컴파일 버튼 리스너
        LayoutInflater inflater = (LayoutInflater)root.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.answercheckwithadd, linearLayout);

        ImageButton buttonRefresh = (ImageButton)root.findViewById(R.id.button_delete);
        ImageButton buttonAdd = (ImageButton)root.findViewById(R.id.button_add);
        ImageButton buttonCompile = (ImageButton)root.findViewById(R.id.button_compile);

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
                Toast.makeText(getContext(), "입력하신 값을 모두 지웠습니다", Toast.LENGTH_LONG).show();
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
                //가장 아래로 이동
                questionCard.fullScroll(View.FOCUS_DOWN);
                Toast.makeText(getContext(), "문제를 하나 더 추가했습니다", Toast.LENGTH_LONG).show();
            }
        });

       //제출하기를 누르면 editText 에 있는 값들이 resultCard 에 보여짐
        buttonCompile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //답이 맞는지 판단 : 변수가 반드시 대소문자 또는 _ 로 시작하고 변수는 문자 + 숫자로만 구성이 된다

                //이 페이지에 있는 widget set 를 가져옴
                WidgetSet widgetSet = viewFactory.getWidgetSet();
                ArrayList<EditText> editTextList = widgetSet.getEditText();
                String[] userInputs = new String[editTextList.size()];
                boolean isSuccess = true;
                int i = 0;
                for(EditText editText : editTextList){
                    //사용자가 입력한 변수를 가져온다
                    userInputs[i] = editText.getText().toString();
                    //TODO : 답 체크하는 함수로 판단, 틀림없는지 확인해보자
                    if(!GrammarChecker.checkVariableValidity(userInputs[i])){
                        isSuccess = false;
                        //에러 보여주기
                        viewFactory.addFeedBackText(2, "변수 이름 설정 에러!", feedBackTextContainer);
                        //진동
                        new AnswerManager(getContext()).vibrate();
                        //캐릭터 움직이기

                        break;
                    }
                    i++;
                }
                if(isSuccess){
                    //이 widget set 을 바탕으로 resultCard 에 반영 : 잘못된
                    //show(widgetSet, userInputs, resultCard);

                    //성공이라고 텍스트에 표시해줌
                    viewFactory.addFeedBackText(1, "축하합니다~ 성공!", feedBackTextContainer);
                    contentPagerListener.setIsSolved(true); //문제 풀었음
                }
            }
        });
    }






    /**
     * 입력한 값대로 피드백을 띄워주겠다는 의도였는데 쓰진않을것 같지만 킵
     * @param widgetSet : 이 페이지에 있는 widget들
     * @param userInputs
     * @param resultCard : 사용자가 입력한 값을 보여주는 resultCard
     */
//    public void show(WidgetSet widgetSet, String[] userInputs, LinearLayout resultCard){
//        //resultCard 에 기존에 있던 위젯들을 제거
//        resultCard.removeAllViews();
//
//        int i = 0;
//
//        ArrayList<Spinner> spinnersList = widgetSet.getSpinner();
//        for(Spinner spinner : spinnersList){
//            String userDataType = spinner.getSelectedItem().toString();
//            String userVariableName = userInputs[i++];
//            String result = userDataType + " "+ userVariableName;
//            //load image
//            TextView textView = (TextView)viewFactory.createWidget("TextView", new String[]{result});
//            textView.setTextSize(PageHelper.questionTextSize);
//
//            //데이터 타입에 따라서 다른 box 색깔로 보여준다
//            switch (userDataType){
//                case "int":
//                    textView.setBackground(getResources().getDrawable(R.drawable.int_box));
//                    break;
//                case "float":
//                    textView.setBackground(getResources().getDrawable(R.drawable.float_box));
//                    break;
//                case "char":
//                    textView.setBackground(getResources().getDrawable(R.drawable.char_box));
//                    break;
//            }
//            textView.setGravity(Gravity.CENTER);
//            viewFactory.addView(textView, resultCard);
//        }
//    }
}
