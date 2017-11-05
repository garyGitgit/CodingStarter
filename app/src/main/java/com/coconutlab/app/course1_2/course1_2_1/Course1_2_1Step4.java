package com.coconutlab.app.course1_2.course1_2_1;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coconutlab.app.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Course1_2_1Step4 extends Fragment {

    View root;

    public Course1_2_1Step4() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_g_step4, container, false);
        return root;
    }
}
