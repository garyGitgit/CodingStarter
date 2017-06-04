package com.gachon.app.course1_1.course1_1_1;


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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.gachon.app.R;
import com.gachon.app.helper.PageHelper;
import com.gachon.app.helper.UserLevelManager;
import com.gachon.app.helper.ViewFactoryCS;


/**
 * course 1-1 데이터 타입 / 변수 / 초기화
 * step 4 : 문제 풀이
 */
public class Course1_1_1Step4 extends Fragment {
    //항상 추가
    View root;
    ViewFactoryCS viewFactory;

    // Required empty public constructor
    public Course1_1_1Step4() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_g_step4, container, false);
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
        LinearLayout problemCard = viewFactory.createCard(0.0f, Color.WHITE, true, new int[]{0,0,0,PageHelper.defaultMargin});
        viewFactory.addSimpleText("다음 중 변수가 될 수 없는 이름은?", 20 ,problemCard);

        //보기가 나타나는 카드
        final LinearLayout answerCard = viewFactory.createCard(1.5f, Color.WHITE, false, new int[]{0,0,0,PageHelper.defaultMargin});
        RadioGroup radioGroup = (RadioGroup)viewFactory.createWidget("RadioButton", new String[]{"num", "1num", "num1", "_num1"});
        answerCard.addView(radioGroup);

        //결과 블록 카드
        //final LinearLayout resultCard = viewFactory.createCard(1.0f, Color.WHITE, false, new int[]{0,0,0,PageHelper.defaultMargin});
        //feedback card 추가
        final TextView feedBackTextContainer = viewFactory.createFeedBackCard(1.0f, new int[]{0,0,0,PageHelper.defaultMargin});
        viewFactory.addFeedBackText("변수의 이름을 정할 때 규칙을 잘 생각해보세요", feedBackTextContainer);


        //컴파일 버튼 카드
        LinearLayout answerCheckLayout = viewFactory.createCard(0.0f, Color.WHITE, false, new int[]{0,0,0,PageHelper.defaultMargin});
        LinearLayout linearLayout = new LinearLayout(getContext());
        viewFactory.addView(linearLayout, answerCheckLayout);
        //answercheckwithadd 동적으로 인플레이트
        LayoutInflater inflater = (LayoutInflater)root.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.answercheck, linearLayout);

        ImageButton buttonRefresh = (ImageButton)root.findViewById(R.id.button_delete);
        ImageButton buttonCompile = (ImageButton)root.findViewById(R.id.button_compile);

        //새로고침 버튼을 누르면 블록들이 모두 없어진다
        //TODO : 사실 이게 별로 안 컸으면 좋겠음
        buttonRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        buttonCompile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //답이 맞을 시 정답입니다 후 종료
                //Toast.makeText(getContext(), "정답입니다!", Toast.LENGTH_SHORT).show();
                // 다이얼로그 바디
                AlertDialog.Builder alert_confirm = new AlertDialog.Builder(getContext());
                // 메세지
                alert_confirm.setMessage("축하합니다! 한 코스를 완료하셨습니다!");
                // 확인 버튼 리스너
                alert_confirm.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //레벨업인지 확인
                        UserLevelManager ulm = new UserLevelManager(getContext());
                        Log.e("gary before : ", Integer.toString(ulm.getPoints()));
                        if(ulm.addPoints()){
                            Toast.makeText(getContext(), "레벨 업 하셨습니다!", Toast.LENGTH_SHORT).show();
                        }
                        Log.e("gary after : ", Integer.toString(ulm.getPoints()));
                        getActivity().finish();
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

            }
        });
    }

    public boolean isCorrect(){
        return true;
    }
}
