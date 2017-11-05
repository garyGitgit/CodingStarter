package com.coconutlab.app.helper;

import android.util.Log;

/**
 * Created by garyNoh on 2017. 5. 14..
 */

public class GrammarChecker {
    /**
     * TODO : 변수가 문자 또는 _ 로 시작을 하고 문자,_와 숫자로만 구성되어있는지 확인하는 자바 모듈 (yj)
     *
     * @param str : 판단할 문자열
     * @return : true : 변수로 사용될 수 있으면/ false : 변수로 사용될 수 없으면
     */
    public static boolean checkVariableValidity(String str) {
        if(str.equals("")) return false;

        int size = str.length();
        char v;
        //첫 문자 확인 (숫자 허용안됨)
        v = str.charAt(0);
        if( !((('A'<=v) && (v<='Z')) || (('a'<=v) && (v<='z')) || (v=='_')) )
            return false;
        //문자가 하나면 true 리턴
        if(size <= 1) return true;

        //두 번째 문자 확인
        int i=1;
        while(true) {
            v = str.charAt(i);
            System.out.println(v);
            Log.e("garyTest", Character.toString(v));
            if( (('A'<=v) && (v<='Z')) || (('a'<=v) && (v<='z')) || ('0'<=v)&&(v<='9') || (v=='_') )
                i++;
            else
                return false;
            if (i==str.length())
                break;
        }
        return true;
    }



}
