package com.gachon.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;

public class Fragment1 extends Fragment implements MainActivity.onBluetoothMessageReceived {
    View rootView;

    ImageView imageViewMainBlock1_1;


    /*
    fragment 1 : 메인 페이지
     */
    public static final String TAG = "gachon/Fragment1/";

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        int position = FragmentPagerItem.getPosition(getArguments());
        Log.e("bisecu", "onCreateView : " + Integer.toString(position));
        setRetainInstance(true);
        rootView = inflater.inflate(R.layout.fragment_fragment1, container, false);
        imageViewMainBlock1_1 = (ImageView) rootView.findViewById(R.id.mainBlock1_1);
        imageViewMainBlock1_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(rootView.getContext(), Course1ListActivity.class));
            }
        });

        return rootView;
    }


    @Override
    public void onStop() {
        Log.e("bisecu", "onStop");
        super.onStop();
    }

    @Override
    public void onResume() {
        Log.e("bisecu", "onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.e("bisecu", "onPause");
        super.onPause();
    }

    @Override
    public void onDetach() {
        Log.e("bisecu", "onDetach");
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        Log.e("bisecu", "onDestroy");
        super.onDestroy();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.e("bisecu", "onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        Log.e("bisecu", "onAttach");
        super.onAttach(activity);
    }

    @Override
    public void onStart() {
        Log.e("bisecu", "onStart");
        super.onStart();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.e("bisecu", "onViewCreated");
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onMessageReceived(byte[] receivedData) {
    }

}
