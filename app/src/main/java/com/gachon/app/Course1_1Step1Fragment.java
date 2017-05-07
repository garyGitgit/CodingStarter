package com.gachon.app;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class Course1_1Step1Fragment extends Fragment {
    //항상 추가
    View root;
    ViewFactoryCS viewFactory;

    public Course1_1Step1Fragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //항상 추가
        root = inflater.inflate(R.layout.fragment_course1_1_step1, container, false);

        return root;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewFactory = new ViewFactoryCS();

        LinearLayout layout = (LinearLayout) root.findViewById(R.id.fragment_course1_1_step1);
        Log.e("gary", Integer.toString(layout.getId()));
        layout.setBackgroundColor(Color.GREEN);
        viewFactory.createImageCardView(100, layout, this, Color.GREEN);
        viewFactory.createImageCardView(100, layout, this, Color.BLUE);
    }
}
