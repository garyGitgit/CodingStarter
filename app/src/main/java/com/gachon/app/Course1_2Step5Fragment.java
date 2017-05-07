package com.gachon.app;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class Course1_2Step5Fragment extends Fragment {

    Button buttonEnd;
    View root;


    public Course1_2Step5Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_course1_2_step5, container, false);
        buttonEnd = (Button) root.findViewById(R.id.buttonGoNext1_5);
        buttonEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //확인 누르면 종료
                getActivity().finish();
            }
        });
        return root;
    }

}
