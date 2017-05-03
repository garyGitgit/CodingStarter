package com.gachon.app;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class Course1Step4Fragment extends Fragment {

    View root;
    Spinner[] spinners;
    OnGoNextPageInterface goNextPage;
    ImageView buttonCompile;

    public Course1Step4Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_course1_step4, container, false);
        buttonCompile = (ImageView)root.findViewById(R.id.compile1_4);
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
