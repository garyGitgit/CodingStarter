package com.gachon.app.main;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gachon.app.R;

/**
 * A simple {@link Fragment} subclass.
 */
/*
fragment 3번 페이지
 */
public class Fragment3 extends Fragment implements MainActivity.onBluetoothMessageReceived {


    String[] testList = {"a","b","c","d","e"};
    View root;
    Context rootContext;

    public Fragment3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        root = inflater.inflate(R.layout.fragment_fragment3, container, false);
        rootContext = root.getContext();

        return root;
    }



    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onMessageReceived(byte[] data) {

    }
}
