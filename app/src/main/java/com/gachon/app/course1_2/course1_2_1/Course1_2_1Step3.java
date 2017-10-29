package com.gachon.app.course1_2.course1_2_1;


import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gachon.app.R;
import com.gachon.app.helper.AnswerManager;
import com.gachon.app.helper.ContentPagerListener;
import com.gachon.app.helper.PageHelper;
import com.gachon.app.helper.UserManager;
import com.gachon.app.helper.ViewFactoryCS;
import com.gachon.app.helper.WidgetSet;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Course1_2_1Step3 extends Fragment {

    View root;
    ViewFactoryCS viewFactory;

    UserManager userManager;

    public Course1_2_1Step3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_g_step3, container, false);
        userManager = UserManager.getIntance();
        userManager.init(root.getContext());
        return root;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //최상단 루트 레이아웃
        LinearLayout layout = (LinearLayout) root.findViewById(R.id.fragment_g_step3);
        viewFactory = new ViewFactoryCS(layout);

        //문제 카드
        LinearLayout questionCard = viewFactory.createCard(0.0f, Color.WHITE, false, new int[]{0,0,0, PageHelper.defaultMargin});
        viewFactory.addSimpleText("Q. 다음 연산의 예상되는 결과 값은?", 20, questionCard);

        //문제 입력 카드
        TableLayout answerCard = viewFactory.createTableCard(1.0f, Color.WHITE, new int[]{0,0,0,PageHelper.defaultMargin});

        //문제 추가
        View[] rowViews;

        rowViews = new View[]{
                viewFactory.createWidget("TextView", new String[]{"12  *  4  =  "}),
                viewFactory.createWidget("EditText", new String[]{"답을 입력하세요"})
        };
        viewFactory.addRow(rowViews, answerCard);

        rowViews = new View[]{
                viewFactory.createWidget("TextView", new String[]{"10  /  3  =  "}),
                viewFactory.createWidget("EditText", new String[]{"답을 입력하세요"})
        };
        viewFactory.addRow(rowViews , answerCard);

        rowViews  = new View[]{
                viewFactory.createWidget("TextView", new String[]{"7  %  2  =  "}),
                viewFactory.createWidget("EditText", new String[]{"답을 입력하세요"})
        };
        viewFactory.addRow(rowViews , answerCard);

        rowViews  = new View[]{
                viewFactory.createWidget("TextView", new String[]{"1  ==  1  =  "}),
                viewFactory.createWidget("EditText", new String[]{"true 또는 false"})
        };
        viewFactory.addRow(rowViews , answerCard);

        rowViews  = new View[]{
                viewFactory.createWidget("TextView", new String[]{"(num  =  1)  =  "}),
                viewFactory.createWidget("EditText", new String[]{"true 또는 false"})
        };
        viewFactory.addRow(rowViews , answerCard);

//        rowViews  = new View[]{
//                viewFactory.createWidget("TextView", new String[]{"<더 알아보기> 정수/정수 는 정수값으로 소수점은 버린다"}),
//        };
//        viewFactory.addRow(rowViews , answerCard);
//
//        rowViews  = new View[]{
//                viewFactory.createWidget("TextView", new String[]{"<더 알아보기> num = 1 이 true 라는 것 설명 "}),
//        };
//        viewFactory.addRow(rowViews , answerCard);

        final TextView feedBackTextContainer = viewFactory.createFeedBackCard(0.5f, new int[]{0,0,0,0});
        viewFactory.addFeedBackText(0, "빈칸에 알맞은 결과 값을 타아핑해주세요!", feedBackTextContainer);

        LinearLayout answerCheckLayout = viewFactory.createCard(0.0f, Color.WHITE, false, new int[]{0,0,0,0});
        LinearLayout linearLayout = new LinearLayout(getContext());
        viewFactory.addView(linearLayout, answerCheckLayout);

        viewFactory.addSpace(0.3f);

        /* 페이지 넘어가는 버튼 */
        ImageButton goNext = (ImageButton) root.findViewById(R.id.goNext);
        ImageButton goPrev = (ImageButton) root.findViewById(R.id.goPrevious);
//        goNext.setOnClickListener(new ContentPageListener(5, getActivity()));
//        goPrev.setOnClickListener(new ContentPageListener(4, getActivity()));
        ContentPagerListener contentPagerListener = new ContentPagerListener(getActivity());
        goNext.setOnClickListener(contentPagerListener);
        goPrev.setOnClickListener(contentPagerListener);


        //answercheckwithadd 동적으로 인플레이트
        LayoutInflater inflater = (LayoutInflater)root.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.answercheck, linearLayout);

        ImageButton buttonRefresh = (ImageButton)root.findViewById(R.id.button_delete);
        ImageButton buttonCompile = (ImageButton)root.findViewById(R.id.button_compile);

        //새로고침 버튼 누르면 editText 모두 제거
        buttonRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        //제출하기를 누르면 editText 에 있는 값들이 resultCard 에 보여짐
        buttonCompile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isCorrect()){
                    final AlertDialog.Builder alert_confirm = new AlertDialog.Builder(getContext());
                    // 메세지
                    alert_confirm.setMessage("축하합니다! 한 코스를 완료하셨습니다!");
                    // 확인 버튼 리스너
                    alert_confirm.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //다음 코스 오픈
                            doFinishJobs();
                        }
                    });
                    alert_confirm.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {

                        }
                    });
                    // 다이얼로그 생성
                    AlertDialog alert = alert_confirm.create();

                    // 아이콘
//                alert.setIcon(R.drawable.codingstarter_logo);
                    // 다이얼로그 타이틀
                    //alert.setTitle("완료");
                    // 다이얼로그 보기
                    alert.show();
                    viewFactory.addFeedBackText(1, "성공", feedBackTextContainer);
                }
                else{
                    new AnswerManager(getContext()).vibrate();
                    viewFactory.addFeedBackText(2, "다시 한 번 생각해보세요!", feedBackTextContainer);
                }
            }
        });
    }

    public void doFinishJobs(){
        //레벨업인지 확인
        userManager.incrementPoints();
        //포인트 추가
        if(userManager.isMaxPoints()){
            Toast.makeText(getContext(), "레벨업을 축하드립니다!", Toast.LENGTH_SHORT).show();
            userManager.incrementMaxPoints();
        }

        //course 1_1 마지막 이므로 progress 증가
        userManager.incrementProgress();


        //widget set 모두 제거
        WidgetSet widgetSet = viewFactory.getWidgetSet();
        widgetSet.removeAllWidgetSets();
        //액티비티 종료
        getActivity().finish();
    }

    public boolean isCorrect(){
        WidgetSet widgetSet = viewFactory.getWidgetSet();
        //radiogroup 은 하나밖에 없음
        ArrayList<EditText> userAnswers= widgetSet.getEditText();
        String str = "";
        //getAlltext
        for(TextView answer : userAnswers){
            str += answer.getText();
        }
        Log.e("gary", "answerblocks : " + str);
        if(str.equals("4831truetrue")){
            return true;
        }
        return false;
    }
}
