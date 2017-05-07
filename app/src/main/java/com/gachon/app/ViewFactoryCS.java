package com.gachon.app;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

/**
 * Created by garyNoh on 2017. 5. 7..
 * view 를 쉽게 생성해주는 클래스임
 *
 * 1) 이미지를 위한 카드뷰 생성
 * 2) 텍스트를 위한 카드뷰 생성
 * 3)
 */

public class ViewFactoryCS {
    public CardView  createImageCardView(int height, LinearLayout layout, Fragment self){
        CardView imageCardView = new CardView(layout.getContext());
        //상수를 할당하면 픽셀 단위로 할당하는 것 같음
        CardView.LayoutParams params = new CardView.LayoutParams(CardView.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, 1);

        TableLayout.LayoutParams params1 = new TableLayout.LayoutParams(0, 0, 1f);
        params.setMargins(100,100,100,100);

        imageCardView.setLayoutParams(params1);
        imageCardView.setBackgroundColor(Color.BLUE);

//        layout.addView(imageCardView);
        Button button = new Button(imageCardView.getContext());
        button.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        //작동하지 않는 것 같음
        button.setText("hello world");
        //button.setBackgroundColor(Color.GREEN);
        imageCardView.addView(button);
        layout.addView(imageCardView);

        return imageCardView;
    }

    public CardView  createImageCardView(int height, LinearLayout layout, Fragment self, int color){
        CardView imageCardView = new CardView(layout.getContext());
        //상수를 할당하면 픽셀 단위로 할당하는 것 같음
        CardView.LayoutParams params = new CardView.LayoutParams(CardView.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, 1);

        TableLayout.LayoutParams params1 = new TableLayout.LayoutParams(0, 0, 1f);
        params.setMargins(100,100,100,100);

        imageCardView.setLayoutParams(params1);
        imageCardView.setBackgroundColor(color);

//        layout.addView(imageCardView);
        Button button = new Button(imageCardView.getContext());
        button.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        //작동하지 않는 것 같음
        button.setText("hello world");
        //button.setBackgroundColor(Color.GREEN);
        imageCardView.addView(button);
        layout.addView(imageCardView);

        return imageCardView;
    }

//    public void addImageView(ImageView imageView, CardView parent){
//        parent.addView(imageView);
//    }

    public void addText(String str, CardView parent){
        TextView textView = new TextView(parent.getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(params);



    }
}
