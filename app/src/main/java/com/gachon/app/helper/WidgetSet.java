package com.gachon.app.helper;

import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by garyNoh on 2017. 5. 9..
 */

public class WidgetSet {

    ArrayList<Spinner> spinnerList;
    ArrayList<EditText> editTextList;
    ArrayList<TextView> textViewList;
    static float scale;

    public WidgetSet(){
        spinnerList = new ArrayList<>();
        editTextList = new ArrayList<>();
    }

    /**
     * 화면의 density 에 따라 scale 설정
     *
     * @param val : density
     */
    public static void setScale(float val){
        scale = val;
    }

    /**
     * density 에 따라 원하는 dp 를 px 로 변환
     *
     * @param val : dp
     * @return : 원하는 dp를 px 로 변환한 값 리턴
     */
    public static int getPxFromDp(int val){
        return (int) (val*scale + 0.5f);
    }
    public ArrayList<Spinner> getSpinner() {
        return spinnerList;
    }

    public void setSpinner(Spinner spinner) {
        this.spinnerList.add(spinner);
    }

    public ArrayList<EditText> getEditText() {
        return editTextList;
    }

    public void setEditText(EditText editText) {
        this.editTextList.add(editText);
    }

    public void setTextView(TextView textView) {
        this.textViewList.add(textView);
    }

    public ArrayList<TextView> getTextView(){
        return textViewList;
    }


}
