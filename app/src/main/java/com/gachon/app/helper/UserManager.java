package com.gachon.app.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

/**
 * Created by garyNoh on 2017. 6. 4..
 */

public class UserManager {
    Context context;
    SharedPreferences sharedPreferencesPoints;
    SharedPreferences sharedPreferencesMax;
    SharedPreferences sharedPreferencesProgress;
    SharedPreferences sharedPreferencesLogin;
    SharedPreferences sharedPreferencesUserInfo;

    static String pointsKey = "userPoints";
    static String maxKey = "maxPoints";
    static String progressKey = "userProgress";
    static String loginKey = "autologin";
    static String nicknameKey = "nickname";
    static String userKey = "user";

    String nickname = "코딩이";
    String mygroup = "NoGroup";

    public UserManager(Context context){
        this.context = context;
        sharedPreferencesPoints = context.getSharedPreferences(pointsKey, 0);
        sharedPreferencesMax = context.getSharedPreferences(maxKey, 0);
        sharedPreferencesProgress = context.getSharedPreferences(progressKey, 0);
        sharedPreferencesLogin = context.getSharedPreferences(loginKey, 0);
        sharedPreferencesUserInfo = context.getSharedPreferences(userKey, 0);
    }


    public void setAutoLogin(boolean isAutoLogin){
        SharedPreferences.Editor editor = sharedPreferencesLogin.edit();
        editor.putBoolean(loginKey, isAutoLogin); // max 를 초깃값으로 초기화
        editor.commit();
    }

    public boolean isAutoLogin(){
        return sharedPreferencesLogin.getBoolean(loginKey, false);
    }


    public String getMygroup() {
        return mygroup;
    }

    public void setMygroup(String mygroup) {
        this.mygroup = mygroup;
    }

    public String getNickname(){
        return sharedPreferencesUserInfo.getString(nicknameKey, this.nickname);
    }

    public void setNickname(String nickname){
        SharedPreferences.Editor editor = sharedPreferencesUserInfo.edit();
        editor.putString(nicknameKey, nickname);
        Log.e("gary", "setnickname " + nickname);
        editor.commit();
    }

    public int getPoints(){
        return sharedPreferencesPoints.getInt(pointsKey, 0);
    }

    public int getMaxPoints(){
        return sharedPreferencesMax.getInt(maxKey, 0);
    }

    /**
     *
     * @return true 면 레벨업
     */
    public boolean addPoints(){
        SharedPreferences.Editor editor = sharedPreferencesPoints.edit();
        editor.putInt(pointsKey, getPoints()+100); //100점씩 더한다
        editor.commit();

        ParseUser user = ParseUser.getCurrentUser();
        Log.e("gary", "user nickname " + user.get("nickname"));

        //개인 포인트 적립
        user.put("points", getMaxPoints()+getPoints()+100);
        user.saveInBackground();
        String myGroup = (String)user.get("group");


        //내 그룹 포인트에 100 추가
        //ParseObject parseObject = new ParseObject("Groups");
        ParseQuery<ParseObject> query = new ParseQuery<>("Groups");
        query.whereEqualTo("name", myGroup);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> results, ParseException e) {
                if (e == null) {
                    // results contains a list of all the emails found
                    for (ParseObject x : results) {
                        int groupPoint = (Integer) x.get("points");
                        x.put("points", groupPoint + 100);
                        x.saveInBackground();
                    }
                } else {
                    // error
                    e.printStackTrace();
                }
            }
        });





        //user.add("points", getMaxPoints()+getPoints()+100);
        //user.saveEventually();

        //레벨업 할 때
        if(getPoints() == getMaxPoints()){
            editor.putInt(pointsKey, 0); // 0으로 초기화시키고
            setMaxPoints(getPoints()+100); //max 를 한 단계 올린다
            editor.commit();
            return true;
        }
        return false;
    }

    public void setMaxPoints(int maxPoints){
        SharedPreferences.Editor editor = sharedPreferencesMax.edit();
        editor.putInt(maxKey, maxPoints); // max 를 초깃값으로 초기화
        editor.commit();
    }


    public void incrementProgress(){
        SharedPreferences.Editor editor = sharedPreferencesProgress.edit();
        editor.putInt(progressKey, getProgress()+1); // max 를 초깃값으로 초기화
        editor.commit();
    }

    public int getProgress(){return sharedPreferencesProgress.getInt(progressKey, 0);}




    void resetMaxPoints(){
        SharedPreferences.Editor editor = sharedPreferencesMax.edit();
        editor.putInt(maxKey, 100); // max 를 초깃값으로 초기화
        editor.commit();
    }

    void resetPoints(){
        SharedPreferences.Editor editor = sharedPreferencesPoints.edit();
        editor.putInt(pointsKey, 0);
        editor.commit();
    }

    void resetProgress(){
        SharedPreferences.Editor editor = sharedPreferencesProgress.edit();
        editor.putInt(progressKey, 1);
        editor.commit();
    }

    public void reset(){
        resetPoints();
        resetMaxPoints();
        resetProgress();
    }


}
