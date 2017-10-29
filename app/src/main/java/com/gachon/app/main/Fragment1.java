package com.gachon.app.main;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.gachon.app.R;
import com.gachon.app.course1_1.Course1_1Activity;
import com.gachon.app.course1_2.Course1_2Activity;
import com.gachon.app.helper.AnswerManager;
import com.gachon.app.helper.UserManager;

public class Fragment1 extends Fragment implements MainActivity.onFragmentMessageReceived {
    View rootView;

    //ImageView imageViewMainBlock1_1, imageViewMainBlock1_2, imageViewMainBlock1_3, imageViewMainBlock2_1;
    ImageView[][] mainBlocks = new ImageView[4][3];

    UserManager userManager;
    AnswerManager answerManager;
    int progress = 1;

    /*
    fragment 1 : 메인 페이지
     */
    public static final String TAG = "codingstarter";

    Bitmap bitmapBlock1_disabled, bitmapBlock2_disabled, bitmapBlock3_disabled;
    Bitmap bitmapBlock1, bitmapBlock2, bitmapBlock3;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        setRetainInstance(true);
        rootView = inflater.inflate(R.layout.fragment_fragment1, container, false);

        bitmapBlock1_disabled = BitmapFactory.decodeResource(getResources(), R.drawable.main_block1_disabled);
        bitmapBlock2_disabled = BitmapFactory.decodeResource(getResources(), R.drawable.main_block2_disabled);
        bitmapBlock3_disabled = BitmapFactory.decodeResource(getResources(), R.drawable.main_block3_disabled);
        bitmapBlock1 = BitmapFactory.decodeResource(getResources(), R.drawable.main_block1);
        bitmapBlock2 = BitmapFactory.decodeResource(getResources(), R.drawable.main_block2);
        bitmapBlock3 = BitmapFactory.decodeResource(getResources(), R.drawable.main_block3);


        mainBlocks[0][0] = (ImageView) rootView.findViewById(R.id.mainBlock1_1);
        mainBlocks[0][0].setImageBitmap(bitmapBlock1);

        mainBlocks[0][1]= (ImageView) rootView.findViewById(R.id.mainBlock1_2);
        mainBlocks[0][1].setImageBitmap(bitmapBlock2_disabled);

        mainBlocks[0][2] = (ImageView) rootView.findViewById(R.id.mainBlock1_3);
        mainBlocks[0][2].setImageBitmap(bitmapBlock3_disabled);

        mainBlocks[1][0] = (ImageView) rootView.findViewById(R.id.mainBlock2_1);
        mainBlocks[1][0].setImageBitmap(bitmapBlock1_disabled);

        mainBlocks[1][1] = (ImageView) rootView.findViewById(R.id.mainBlock2_2);
        mainBlocks[1][1].setImageBitmap(bitmapBlock2_disabled);

        mainBlocks[1][2]= (ImageView) rootView.findViewById(R.id.mainBlock2_3);
        mainBlocks[1][2].setImageBitmap(bitmapBlock3_disabled);

        mainBlocks[2][0] = (ImageView) rootView.findViewById(R.id.mainBlock3_1);
        mainBlocks[2][0].setImageBitmap(bitmapBlock1_disabled);

        mainBlocks[2][1] = (ImageView) rootView.findViewById(R.id.mainBlock3_2);
        mainBlocks[2][1].setImageBitmap(bitmapBlock2_disabled);

        mainBlocks[2][2] = (ImageView) rootView.findViewById(R.id.mainBlock3_3);
        mainBlocks[2][2].setImageBitmap(bitmapBlock3_disabled);

        mainBlocks[3][0]= (ImageView) rootView.findViewById(R.id.mainBlock4_1);
        mainBlocks[3][0].setImageBitmap(bitmapBlock1_disabled);

        mainBlocks[3][1] = (ImageView) rootView.findViewById(R.id.mainBlock4_2);
        mainBlocks[3][1].setImageBitmap(bitmapBlock2_disabled);

        mainBlocks[3][2] = (ImageView) rootView.findViewById(R.id.mainBlock4_3);
        mainBlocks[3][2].setImageBitmap(bitmapBlock3_disabled);

        for(int i = 0 ; i < 4; i++){
            for(int j = 0 ; j< 3; j++){
                mainBlocks[i][j].setOnClickListener(new onMainBlockClickListener());
            }
        }


        //answer manager
        answerManager = new AnswerManager(rootView.getContext());

        //사용자 관리 매니저 
        userManager = UserManager.getIntance();
        userManager.init(rootView.getContext());


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
                    //progress 가
                    if(progress >= 2)
                        startActivity(new Intent(rootView.getContext(), Course1_2Activity.class));
                    else{
                        YoYo.with(Techniques.Shake).duration(1000).playOn(v);
                        answerManager.vibrate();
                    }
                    break;
                case R.id.mainBlock1_3:
                case R.id.mainBlock2_1:
                case R.id.mainBlock2_2:
                case R.id.mainBlock2_3:
                case R.id.mainBlock3_1:
                case R.id.mainBlock3_2:
                case R.id.mainBlock3_3:
                case R.id.mainBlock4_1:
                case R.id.mainBlock4_2:
                case R.id.mainBlock4_3:
                    alertClosed(v);
                    break;
            }
        }
    }

    private void alertClosed(View v){
        YoYo.with(Techniques.Shake).duration(1000).playOn(v);
        answerManager.vibrate();
        Toast.makeText(getContext(), "콘텐츠 준비중입니다", Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onStop() {
        super.onStop();
        bitmapBlock1_disabled.recycle();
        bitmapBlock2_disabled.recycle();
        bitmapBlock3_disabled.recycle();
        bitmapBlock1.recycle();
        bitmapBlock2.recycle();
        bitmapBlock3.recycle();
    }

    @Override
    public void onResume() {
        super.onResume();
        bitmapBlock1_disabled = BitmapFactory.decodeResource(getResources(), R.drawable.main_block1_disabled);
        bitmapBlock2_disabled = BitmapFactory.decodeResource(getResources(), R.drawable.main_block2_disabled);
        bitmapBlock3_disabled = BitmapFactory.decodeResource(getResources(), R.drawable.main_block3_disabled);
        bitmapBlock1 = BitmapFactory.decodeResource(getResources(), R.drawable.main_block1);
        bitmapBlock2 = BitmapFactory.decodeResource(getResources(), R.drawable.main_block2);
        bitmapBlock3 = BitmapFactory.decodeResource(getResources(), R.drawable.main_block3);


//        mainBlocks[0][0] = (ImageView) rootView.findViewById(R.id.mainBlock1_1);
//        mainBlocks[0][1]= (ImageView) rootView.findViewById(R.id.mainBlock1_2);
//        mainBlocks[0][2] = (ImageView) rootView.findViewById(R.id.mainBlock1_3);
//        mainBlocks[1][0] = (ImageView) rootView.findViewById(R.id.mainBlock2_1);
//        mainBlocks[1][1] = (ImageView) rootView.findViewById(R.id.mainBlock2_2);
//        mainBlocks[1][2]= (ImageView) rootView.findViewById(R.id.mainBlock2_3);
//        mainBlocks[2][0] = (ImageView) rootView.findViewById(R.id.mainBlock3_1);
//        mainBlocks[2][1] = (ImageView) rootView.findViewById(R.id.mainBlock3_2);
//        mainBlocks[2][2] = (ImageView) rootView.findViewById(R.id.mainBlock3_3);
//        mainBlocks[3][0]= (ImageView) rootView.findViewById(R.id.mainBlock4_1);
//        mainBlocks[3][1] = (ImageView) rootView.findViewById(R.id.mainBlock4_2);
//        mainBlocks[3][2] = (ImageView) rootView.findViewById(R.id.mainBlock4_3);



        progress = userManager.getProgress();

        Log.e(TAG, "PROGRESS - " + Integer.toString(progress));
        if(progress == 1){
            mainBlocks[0][0].setImageBitmap(bitmapBlock1);
        }
        else if(progress >= 2){
            mainBlocks[0][0].setImageBitmap(bitmapBlock1);
            mainBlocks[0][1].setImageBitmap(bitmapBlock2);
        }
    }



    @Override
    public void onPause() {
        Log.e(TAG, "onPause");
        super.onPause();
    }

    @Override
    public void onDetach() {
        Log.e(TAG, "onDetach");
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy");
        super.onDestroy();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        Log.e(TAG, "onAttach");
        super.onAttach(activity);
    }

    @Override
    public void onStart() {
        Log.e(TAG, "onStart");
        super.onStart();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onViewCreated");
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onMessageReceived(byte[] receivedData) {
    }

}
