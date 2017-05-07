package com.gachon.app;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Course1_2Step4Fragment extends Fragment {

    View root;
    Spinner[] spinners;
    OnGoNextPageInterface goNextPage;
    ImageView buttonCompile;
    TextView answer1;
    int[] answerPos, item1Pos, item2Pos2;
    LinearLayout questionTable;
    TableRow question1;
    Button item1, item2;
    TranslateAnimation translateAnimation;

    public Course1_2Step4Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_course1_2_step4, container, false);
        buttonCompile = (ImageView)root.findViewById(R.id.compile1_4);
        answer1 = (TextView)root.findViewById(R.id.course1_answer1);
        item1 = (Button)root.findViewById(R.id.course1_answer1_item1);
        item2 = (Button)root.findViewById(R.id.course1_answer1_item2);
        questionTable = (LinearLayout)root.findViewById(R.id.course1_questionTable);
        question1 = (TableRow)root.findViewById(R.id.course1_question1);

        //스크린상 x, y 좌표를 확인
        root.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                root.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                answerPos = new int[2];
                item1Pos = new int[2];
                answer1.getLocationOnScreen(answerPos);
                item1.getLocationOnScreen(item1Pos);
                Log.e("gary", Integer.toString(answer1.getLeft()) + " " + Integer.toString(answer1.getTop()));
                Log.e("gary", Integer.toString(answerPos[0]) + " " + Integer.toString(answerPos[1]));
                Log.e("gary", Integer.toString(item1.getLeft()) + " " + Integer.toString(item1.getTop()));
                Log.e("gary", Integer.toString(item1Pos[0]) + " " + Integer.toString(item1Pos[1]));
            }
        });

        item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                translateAnimation = new TranslateAnimation(
//                        Animation.ABSOLUTE, answerPos[0],
//                        Animation.ABSOLUTE, item1Pos[0],
//                        Animation.ABSOLUTE, answerPos[1],
//                        Animation.ABSOLUTE, item1Pos[1]
//                );
                translateAnimation = new TranslateAnimation(
                        Animation.ABSOLUTE, item1.getLeft(),
                        Animation.ABSOLUTE, questionTable.getLeft(),
                        Animation.ABSOLUTE, item1.getTop(),
                        Animation.ABSOLUTE, questionTable.getTop()
                );
                translateAnimation.setDuration(1000);
                item1.startAnimation(translateAnimation);
            }
        });






        buttonCompile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goNextPage.onPressGoNext();
            }
        });
        return root;
    }


    //액티비티가 인터페이스를 잘 구현했는지 확인
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            goNextPage = (OnGoNextPageInterface) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnGoNextPageInterface");
        }
    }
}
