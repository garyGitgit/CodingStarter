package com.coconutlab.app.course2_1.course2_1_1;


import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;

import com.coconutlab.app.R;
import com.coconutlab.app.helper.PageHelper;
import com.coconutlab.app.helper.ViewFactoryCS;


/**
 * course 1-2 연산자
 * step 1 : 연산자 종류 설명
 */

public class Course2_1_1Step1 extends Fragment {

    View root;
    ViewFactoryCS viewFactory;

    //card 수
    int cardNum = 5;
    //타이머 핸들러
    Handler mHandler;
    //색깔
    int invalidColor;
    int validColor;
    //button
    int buttonNum = 0;
    Button button1, button2;
    //cards
    LinearLayout[] cards = new LinearLayout[5];


    public Course2_1_1Step1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_g_step1, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        invalidColor =  Color.WHITE;
        validColor =  getContext().getResources().getColor(R.color.codingstarter_background);

        //최상단 루트 레이아웃
        LinearLayout layout = (LinearLayout) root.findViewById(R.id.fragment_g_step1);
        viewFactory = new ViewFactoryCS(layout);



        //연산자에 대해서 설명하는 카드 생성
        cards[0] = viewFactory.createCard(1.0f, invalidColor, true, new int[]{0,0,0,PageHelper.defaultMargin});

        //연산자에 대해서 하나씩 소개하는 simple Text 생성
        viewFactory.addSimpleText("조건형은 실행 순서가 특정한 조건에 의해서 순차적이지 않는 순서로 진행되는 것을 의미", 20, cards[0]);

        cards[1] = viewFactory.createCard(1.0f, invalidColor, true, new int[]{0,0,0, 0});
        viewFactory.addSimpleText("학교를 간다", 20, cards[1]);

        cards[2] = viewFactory.createCard(1.0f, invalidColor, true, new int[]{0,0,0,0});
        viewFactory.addSimpleText("체육수업을 시작한다", 20, cards[2]);

        cards[3] = viewFactory.createCard(1.0f, invalidColor, true, new int[]{0,0,0,0});
        viewFactory.addSimpleText("만약에 비가오면\n교실에서 이론수업을 한다", 20, cards[3]);

        cards[4] = viewFactory.createCard(1.0f, invalidColor, true, new int[]{0,0,0,0});
        viewFactory.addSimpleText("만약에 비가 오지 않으면\n야외에서 농구경기를 한다", 20, cards[4]);

        TableLayout selectionCard = viewFactory.createTableCard(1.0f, Color.TRANSPARENT, new int[]{0,0,0, PageHelper.defaultMargin});
        selectionCard.setStretchAllColumns(true);
        button1 = (Button)viewFactory.createWidget("Button", new String[]{"비가 온다"});
        button2 = (Button)viewFactory.createWidget("Button", new String[]{"비가 오지않는다"});

        viewFactory.addRow(new View[]{button1, button2}, selectionCard);


        //비가 온다 버튼을 누르면 색깔 바뀌는 비가 오면을 색칠한다
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AutoCursor autoCursor = new AutoCursor();
                buttonNum = 1;
                autoCursor.execute();
            }
        });

        //비가 온다 버튼을 누르면 색깔 바뀌는 비가 오지 않으면을 색칠한다
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AutoCursor autoCursor = new AutoCursor();
                buttonNum = 2;
                autoCursor.execute();
            }
        });
    }

    class AutoCursor extends  AsyncTask<Void, Void, Void>{

        int i = 1;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            button1.setEnabled(false);
            button2.setEnabled(false);

        }

        @Override
        protected Void doInBackground(Void... params) {

            while(i < cardNum-1) {
                Log.e("garynoh", Integer.toString(i));
                //Publish because onProgressUpdate runs on the UIThread

                //publicProgress 가 sleep 보다 앞에 나와야지 에러가 발생 안한다. 왜 그럴까
                publishProgress();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            // TODO Auto-generated method stub
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            //This is run on the UIThread and will actually Toast... Or update a View if you need it to!
            if(i < 3){
                cards[i-1].setBackgroundColor(invalidColor);
                cards[i].setBackgroundColor(validColor);
            }
            else{
                Log.e("garynoh", Integer.toString(i));
                if(buttonNum == 2){
                    cards[i-1].setBackgroundColor(invalidColor);
                    cards[i+1].setBackgroundColor(validColor);
                }
                else if(buttonNum == 1){
                    cards[i-1].setBackgroundColor(invalidColor);
                    cards[i].setBackgroundColor(validColor);
                }
                i++;
            }
            i++;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            for(int i = 0 ; i < cardNum; i ++){
                cards[i].setBackgroundColor(invalidColor);
            }
            button1.setEnabled(true);
            button2.setEnabled(true);
        }
    };
}
