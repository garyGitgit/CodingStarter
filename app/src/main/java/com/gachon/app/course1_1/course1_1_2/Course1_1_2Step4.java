package com.gachon.app.course1_1.course1_1_2;


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
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gachon.app.R;
import com.gachon.app.helper.ContentPagerListener;
import com.gachon.app.helper.PageHelper;
import com.gachon.app.helper.UserManager;
import com.gachon.app.helper.ViewFactoryCS;
import com.gachon.app.helper.WidgetSet;

import java.util.ArrayList;


/**
 * course 1-1 데이터 타입 / 변수 / 초기화
 * step 4 : 문제 풀이
 */
public class Course1_1_2Step4 extends Fragment {
    //항상 추가
    View root;
    ViewFactoryCS viewFactory;
    UserManager userManager;

    // Required empty public constructor
    public Course1_1_2Step4() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_g_step4, container, false);
        userManager = UserManager.getIntance();
        userManager.init(root.getContext());
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayout layout = (LinearLayout) root.findViewById(R.id.fragment_g_step4);
        viewFactory = new ViewFactoryCS(layout);

        //header text 설정
        viewFactory.createHeaderCard("확인문제", new int[]{0, 0, 0, PageHelper.headerCardMargin});


        //문제를 제시하는 카드 : 카드에 들어갈 위젯 또는 텍스트를 배치
        LinearLayout problemCard = viewFactory.createCard(0.0f, Color.WHITE, true, new int[]{0,0,0,0});
        viewFactory.addSimpleText("정수형 변수를 선언하고, 45로 초기화시키시오.", 20 ,problemCard);

        //입력한 답이 보여지는 카드 : 사용자 입력 block 이 배치되는 카드
        //final LinearLayout answerCard = viewFactory.createCard(1.0f, Color.WHITE, false, new int[]{0,0,0,0});
        final LinearLayout answerCard = viewFactory.createHorizontalCard(1.0f, new int[]{0,0,0,0});

        //보기를 보여주는 카드 : 탭 block 이 배치되는 카드

        //TableLayout blockCard = viewFactory.createTableCard(0.0f, Color.WHITE, new int[]{0,0,0,20});
        HorizontalScrollView scrollView = viewFactory.createHorizontalScrollViewCard(0.0f, Color.WHITE, new int[]{0,0,0,PageHelper.defaultMargin});
        //block 생성
        viewFactory.createBlocks(
                new String[]{"int", "=" ,"45", "float", "num", "char", ";"}, scrollView, answerCard, 1
        );

        //feedback card 추가
        final TextView feedBackTextContainer = viewFactory.createFeedBackCard(1.0f, new int[]{0,0,0,0});
        viewFactory.addFeedBackText(0, "위 블록을 탭해서 블록들을 배치해보세요. 잘못 배치된 블록은 터치하면 취소할 수 있습니다.", feedBackTextContainer);

        //컴파일, 삭제 버튼이 있는 카드
        LinearLayout answerCheckLayout = viewFactory.createCard(0.0f, Color.WHITE, false, new int[]{0,0,0,0});
        LinearLayout linearLayout = new LinearLayout(getContext());
        viewFactory.addView(linearLayout, answerCheckLayout);

        viewFactory.addSpace(0.5f);
        ImageButton goNext = (ImageButton)root.findViewById(R.id.goNext);
        ImageButton goPrev= (ImageButton)root.findViewById(R.id.goPrevious);

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

        //초기화
        buttonRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //뷰에서 모두 제거
                answerCard.removeAllViews();
                WidgetSet widgetSet = viewFactory.getWidgetSet();
                ArrayList<TextView> answerBlocks = widgetSet.getAnswerBlocks();
                answerBlocks.clear();
            }
        });

        //정답 비교
        buttonCompile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //답이 맞을 시 정답입니다 후 종료
                //Toast.makeText(getContext(), "정답입니다!", Toast.LENGTH_SHORT).show();
                // 다이얼로그 바디

                if(isCorrect()){

                    final AlertDialog.Builder alert_confirm = new AlertDialog.Builder(getContext());
                    // 메세지
                    alert_confirm.setMessage("축하합니다! 한 코스를 완료하셨습니다!");
                    // 확인 버튼 리스너
                    alert_confirm.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
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
                    //모두 제거
                    WidgetSet widgetSet = viewFactory.getWidgetSet();
                    widgetSet.removeAllWidgetSets();

                    viewFactory.addFeedBackText(1, "축하합니다~ 성공!", feedBackTextContainer);

                }
                else{
                    viewFactory.addFeedBackText(2, "다시 한 번 확인해주세요!", feedBackTextContainer);
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
        ArrayList<TextView> answerBlocks = widgetSet.getAnswerBlocks();
        String str = "";
        //getAlltext
        for(TextView block : answerBlocks){
            str += block.getText();
        }
        Log.e("gary", "answer : " +  str);
        if(str.equals("intnum=45;")){
            return true;
        }
        return false;

    }
}
