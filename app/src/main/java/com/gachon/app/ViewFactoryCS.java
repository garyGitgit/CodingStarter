package com.gachon.app;

import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

/**
 * Created by garyNoh on 2017. 5. 7..
 * view 를 쉽게 생성해주는 클래스임
 *
 * 1) 이미지를 위한 카드뷰 생성
 * 2) 텍스트를 위한 카드뷰 생성
 * 3)
 */

public class ViewFactoryCS {

    public LinearLayout createCardView(float weight, LinearLayout layout, Fragment self, int color, boolean isVertical){

        CardView cardView = new CardView(layout.getContext());
        //cardview 안에 차곡차곡 쌓기 위해 포함될 linear layout
        LinearLayout linearLayout = new LinearLayout(cardView.getContext());
        linearLayout.setLayoutParams(
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        if(isVertical) linearLayout.setOrientation(LinearLayout.VERTICAL);
        else linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        //상수를 할당하면 픽셀 단위로 할당하는 것 같음
//        CardView.LayoutParams params = new CardView.LayoutParams(CardView.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT, 1);

        //weight 를 줄 수 있음
        TableLayout.LayoutParams params1 = new TableLayout.LayoutParams(0, 0, weight);
        //params1.setMargins(0, 0, 0, 10);

        cardView.setLayoutParams(params1);
        cardView.setCardBackgroundColor(color);

        cardView.addView(linearLayout);
        //레이아웃에 카드뷰 추가
        layout.addView(cardView);

        return linearLayout;
    }

    public TableLayout createTableCardView(float weight, LinearLayout layout, Fragment self, int color, boolean isVertical){
        //card view 로 만들어야지 이쁘다
        TableLayout tableLayout = new TableLayout(layout.getContext());
        TableLayout.LayoutParams params;

        //weight 가 있을 때는 weight 를 맞추고 0일 때는 wrap content 로 처리한다
        if(weight > 0)
            params = new TableLayout.LayoutParams(0, 0, weight);
        else
            params = new TableLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        tableLayout.setLayoutParams(params);
        tableLayout.setBackgroundColor(color);
        layout.addView(tableLayout);
        return tableLayout;
    }

    public void addRow(View[] views, TableLayout parent){
        TableRow tableRow = new TableRow(parent.getContext());
        TableRow.LayoutParams params =
                new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
        tableRow.setLayoutParams(params);
        for(View v : views){
            tableRow.addView(v);
        }
        parent.addView(tableRow);
    }


    /**
     * 카드뷰에 텍스트뷰를 추가
     * @param str
     * @param parent
     */
    public void addText(String str, int size, LinearLayout parent){

        TextView textView = new TextView(parent.getContext());
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(params);

        //text parser * * 나타나기 효과

        if(str.startsWith("*") && str.endsWith("*")){
            YoYo.with(Techniques.FadeIn)
                    .duration(2000)
                    .playOn(textView);
            str = str.substring(1, str.length()-1);
        }
        textView.setText(str);
        textView.setTextSize(size);
        parent.addView(textView);
    }

    public void addImage(int width, int height, Drawable image, LinearLayout parent){
        ImageView imageView = new ImageView(parent.getContext());
        TableLayout.LayoutParams params =
                new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f);
        imageView.setImageDrawable(image);
        imageView.setLayoutParams(params);

        parent.addView(imageView);
    }

    public View getWidget(String viewType, String[] str, LinearLayout parent){
        if(viewType.equalsIgnoreCase("Button")){
            Button button = new Button(parent.getContext());
            //이게 있으면 버튼이 안보인다
//            ViewGroup.LayoutParams params =
//                    new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            button.setLayoutParams(params);
            button.setText(str[0]);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            return button;
        }
        else if(viewType.equalsIgnoreCase("Spinner")){
            Spinner spinner = new Spinner(parent.getContext());
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(parent.getContext(), android.R.layout.simple_spinner_item, str);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            return spinner;
        }
        else if(viewType.equalsIgnoreCase("EditText")){
            EditText editText = new EditText(parent.getContext());
            editText.setHint(str[0]);
            return editText;
        }
        else if(viewType.equalsIgnoreCase("TextView")){
            TextView textView = new TextView(parent.getContext());
            textView.setText(str[0]);
            return textView;
        }
        else return null;
    }
}
