package com.gachon.app.main;

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

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.gachon.app.R;
import com.gachon.app.course1_1.Course1_1Activity;
import com.gachon.app.course1_2.Course1_2Activity;
import com.gachon.app.course2_1.Course2_1Activity;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;

public class Fragment1 extends Fragment implements MainActivity.onBluetoothMessageReceived {
    View rootView;

    //ImageView imageViewMainBlock1_1, imageViewMainBlock1_2, imageViewMainBlock1_3, imageViewMainBlock2_1;
    ImageView[][] mainBlocks = new ImageView[4][3];



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
        mainBlocks[0][0] = (ImageView) rootView.findViewById(R.id.mainBlock1_1);
        mainBlocks[0][1]= (ImageView) rootView.findViewById(R.id.mainBlock1_2);
        mainBlocks[0][2] = (ImageView) rootView.findViewById(R.id.mainBlock1_3);
        mainBlocks[1][0] = (ImageView) rootView.findViewById(R.id.mainBlock2_1);
        mainBlocks[1][1] = (ImageView) rootView.findViewById(R.id.mainBlock2_2);
        mainBlocks[1][2]= (ImageView) rootView.findViewById(R.id.mainBlock2_3);
        mainBlocks[2][0] = (ImageView) rootView.findViewById(R.id.mainBlock3_1);
        mainBlocks[2][1] = (ImageView) rootView.findViewById(R.id.mainBlock3_2);
        mainBlocks[2][2] = (ImageView) rootView.findViewById(R.id.mainBlock3_3);
        mainBlocks[3][0]= (ImageView) rootView.findViewById(R.id.mainBlock4_1);
        mainBlocks[3][1] = (ImageView) rootView.findViewById(R.id.mainBlock4_2);
        mainBlocks[3][2] = (ImageView) rootView.findViewById(R.id.mainBlock4_3);

        for(int i = 0 ; i < 4; i++){
            for(int j = 0 ; j< 3; j++){
                mainBlocks[i][j].setOnClickListener(new onMainBlockClickListener());
            }
        }


        return rootView;
    }

    class onMainBlockClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id){
                case R.id.mainBlock1_1:
                    startActivity(new Intent(rootView.getContext(), Course1_1Activity.class));
                    break;
                case R.id.mainBlock1_2:
                    startActivity(new Intent(rootView.getContext(), Course1_2Activity.class));
                    break;
                case R.id.mainBlock1_3:
                    YoYo.with(Techniques.Shake).duration(1000).playOn(v);
                    break;
                case R.id.mainBlock2_1:
                    startActivity(new Intent(rootView.getContext(), Course2_1Activity.class));
                    break;
                case R.id.mainBlock2_2:
                    YoYo.with(Techniques.Shake).duration(1000).playOn(v);
                    break;
                case R.id.mainBlock2_3:
                    YoYo.with(Techniques.Shake).duration(1000).playOn(v);
                    break;
                case R.id.mainBlock3_1:
                    YoYo.with(Techniques.Shake).duration(1000).playOn(v);
                    break;
                case R.id.mainBlock3_2:
                    YoYo.with(Techniques.Shake).duration(1000).playOn(v);
                    break;
                case R.id.mainBlock3_3:
                    YoYo.with(Techniques.Shake).duration(1000).playOn(v);
                    break;
                case R.id.mainBlock4_1:
                    YoYo.with(Techniques.Shake).duration(1000).playOn(v);
                    break;
                case R.id.mainBlock4_2:
                    YoYo.with(Techniques.Shake).duration(1000).playOn(v);
                    break;
                case R.id.mainBlock4_3:
                    YoYo.with(Techniques.Shake).duration(1000).playOn(v);
                    break;
            }
        }
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
