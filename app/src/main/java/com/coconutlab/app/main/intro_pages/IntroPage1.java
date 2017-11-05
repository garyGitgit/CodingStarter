package com.coconutlab.app.main.intro_pages;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coconutlab.app.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class IntroPage1 extends Fragment {


    public IntroPage1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_intro_page1, container, false);
    }

}
