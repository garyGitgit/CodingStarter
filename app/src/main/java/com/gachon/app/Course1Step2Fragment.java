package com.gachon.app;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Course1Step2Fragment extends Fragment {

    OnGoNextPageInterface goNextPage;
    Button buttonGoNext;
    View root;

    public Course1Step2Fragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_course1_step2, container, false);
        buttonGoNext = (Button) root.findViewById(R.id.buttonGoNext1_2);
        buttonGoNext.setOnClickListener(new View.OnClickListener() {
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
