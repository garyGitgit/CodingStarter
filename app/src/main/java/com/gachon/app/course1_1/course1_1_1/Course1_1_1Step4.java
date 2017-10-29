package com.gachon.app.course1_1.course1_1_1;


import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.gachon.app.R;
import com.gachon.app.helper.AnswerManager;
import com.gachon.app.helper.ContentPagerListener;
import com.gachon.app.helper.PageHelper;
import com.gachon.app.helper.UserManager;
import com.gachon.app.helper.ViewFactoryCS;
import com.gachon.app.helper.WidgetSet;


/**
 * course 1-1 데이터 타입 / 변수 / 초기화
 * step 4 : 문제 풀이
 */
public class Course1_1_1Step4 extends Fragment {
    //공통으로 적용
    View root; // 부모 액티비티
    ViewFactoryCS viewFactory;

    //사용자 관리 매니저
    UserManager userManager;

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

        //문제 카드 생성
        LinearLayout problemCard = viewFactory.createCard(0.0f, Color.WHITE, true, new int[]{0,0,0,0});
        viewFactory.addSimpleText("다음 중 변수가 될 수 없는 이름은?", 20 ,problemCard);

        //보기 카드 생성
        final LinearLayout answerCard = viewFactory.createCard(1.5f, Color.WHITE, false, new int[]{0,0,0,PageHelper.defaultMargin});
        //보기 추가
        RadioGroup radioGroup = (RadioGroup)viewFactory.createWidget("RadioButton", new String[]{"num", "1num", "num1", "_num1"});
        answerCard.addView(radioGroup);

        //feedback card 추가
        final TextView feedBackTextContainer = viewFactory.createFeedBackCard(1.0f, new int[]{0,0,0,0});
        viewFactory.addFeedBackText(0, "변수의 이름을 정할 때 규칙을 잘 생각해보세요", feedBackTextContainer);

        //컴파일 버튼 카드
        LinearLayout answerCheckLayout = viewFactory.createCard(0.0f, Color.WHITE, false, new int[]{0,0,0,0});
        LinearLayout linearLayout = new LinearLayout(getContext());
        viewFactory.addView(linearLayout, answerCheckLayout);

        viewFactory.addSpace(0.5f);


        //페이지 넘기기 버튼
        ImageButton goNext = (ImageButton)root.findViewById(R.id.goNext);
        ImageButton goPrev= (ImageButton)root.findViewById(R.id.goPrevious);

//        goNext.setOnClickListener(new ContentPageListener(5, getActivity()));
//        goPrev.setOnClickListener(new ContentPageListener(4, getActivity()));
        ContentPagerListener contentPagerListener = new ContentPagerListener(getActivity());

        goNext.setOnClickListener(contentPagerListener);
        goPrev.setOnClickListener(contentPagerListener);


        //컴파일 버튼 등 리스너
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

                if(isCorrect()){
                    viewFactory.addFeedBackText(1, "축하합니다~ 성공!", feedBackTextContainer);

                    AlertDialog.Builder alert_confirm = new AlertDialog.Builder(getContext());
                    // 메세지
                    alert_confirm.setMessage("축하합니다! 한 코스를 완료하셨습니다!");
                    // 확인 버튼 리스너
                    alert_confirm.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            doFinishJobs();
                        }
                    });
                    // 다이얼로그 생성
                    AlertDialog alert = alert_confirm.create();

                    // 아이콘
//                alert.setIcon(R.drawable.codingstarter_logo);
                    // 다이얼로그 타이틀
                    //alert.setTitle("완료");
                    // 다이얼로그 보기
                    viewFactory.addFeedBackText(1, "성공", feedBackTextContainer);
                    alert.show();
                }
                else{
                    viewFactory.addFeedBackText(2, "다시 한 번 생각해보세요!", feedBackTextContainer);
                    new AnswerManager(getContext()).vibrate();
                }
            }
        });

        //usermanager 객체 가져오는 것과 초기화
        userManager = UserManager.getIntance();
        userManager.init(root.getContext());

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
        RadioGroup radioGroup = widgetSet.getRadioGroupList().get(0);
        int id = radioGroup.getCheckedRadioButtonId();

        if(id == R.id.radio_answer)return true;
        return false;
    }
}
