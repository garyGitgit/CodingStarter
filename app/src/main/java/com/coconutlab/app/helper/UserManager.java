package com.coconutlab.app.helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

/**
 * Created by garyNoh on 2017. 6. 4..
 *
 * 사용자 정보를 관리하는 매니저
 * 디자인 패턴 : 싱글톤
 *
 *
 * <주의>
 * shared preference 가 있는 이유 : 인터넷 연결 없이도 학습을 하고 자신의 포인트가 증가하는 것을 보기 위해서
 * 그래서 저장을 하는 곳이 2군데 있어서 불편하긴 하지만 필요하다고 생각한다
 * 인터넷 연결이 되지 않을 때 업데이트가 되지 않는데 이 부분은 어떻게 해결? (인터넷 연결이 되었을 때 한꺼번에 업데이트하는 방식)
 *
 * 관리하는 데이터
 * 현재 포인트, 최대 포인트, 현재 진도율, 자동로그인상태, 사용자 정보(닉네임, 그룹)
 */

public class UserManager {
    public static int MAX_USER_LIMIT = 10000;
    Context context;

    private ParseUser user;

    //로컬 db에 저장되는 정보들
//    private SharedPreferences sharedPreferencesPoints;
//    private SharedPreferences sharedPreferencesMax;
//    private SharedPreferences sharedPreferencesProgress;
//    private SharedPreferences sharedPreferencesAutoLogin;
    private SharedPreferences sharedPreferencesUserInfo;

    //로컬 db에 저장할 데이터의 키 값
    private static String pointsKey = "userPoints";
    private static String maxKey = "maxPoints";
    private static String progressKey = "userProgress";
    private static String loginKey = "autologin";
    private static String nicknameKey = "nickname";
    private static String mygroupKey = "mygroup";
//    private static String userKey = "user";

    private static String TAG = "codingstarter";
    ParseObject mygroupObject;

    /*
    싱글톤 디자인 패턴
    initialization on demand holder idiom
    jvm 의 class loader 매커니즘과 class 의 load 시점을 이용하여 내부 class 를 생성시킴으로 thread 간 동기화
    문제를 해결한다
    출처 : https://blog.seotory.com/post/2016/03/java-singleton-pattern
     */
    private UserManager(){}
    private static class Singleton{
        private static final UserManager instance = new UserManager();
    }
    public static UserManager getIntance(){return Singleton.instance; }

    /**
     * 싱글톤인 usermanager 를 초기화시켜줌 처음 getInstance 를 할 때 꼭 해야한다
     * @param context
     */
    public void init(Context context){

        //user 객체 초기화
        this.context = context;
        user = ParseUser.getCurrentUser();
        if(user != null) user.get("group");


        //로컬 db 초기화
        sharedPreferencesUserInfo = context.getSharedPreferences(pointsKey, 0);
        sharedPreferencesUserInfo = context.getSharedPreferences(maxKey, 0);
        sharedPreferencesUserInfo = context.getSharedPreferences(progressKey, 0);
        sharedPreferencesUserInfo = context.getSharedPreferences(loginKey, 0);
        sharedPreferencesUserInfo = context.getSharedPreferences(nicknameKey, 0);
        sharedPreferencesUserInfo = context.getSharedPreferences(mygroupKey, 0);

        //그룹 객체 초기화
        ParseQuery<ParseObject> query = new ParseQuery<>("Groups");
        query.whereEqualTo("name", getMygroup());
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> results, ParseException e) {
                if (e == null) {
                    for (ParseObject x : results) {
                        mygroupObject = x;
                    }
                } else {
                    // error
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * @param isAutoLogin
     * 자동로그인 여부 설정 true / false
     */
    public void setAutoLogin(boolean isAutoLogin){
        SharedPreferences.Editor editor = sharedPreferencesUserInfo.edit();
        editor.putBoolean(loginKey, isAutoLogin); // max 를 초깃값으로 초기화
        editor.commit();
    }

    /**
     * 자동 로그인 상태인지 아닌지 확인
     * @return
     */
    public boolean isAutoLogin(){
        return sharedPreferencesUserInfo.getBoolean(loginKey, false);
    }

    /**
     * 내 그룹의 이름을 반환
     * @return
     */
    public String getMygroup() { return sharedPreferencesUserInfo.getString(mygroupKey, "No group"); }

    /**
     * 내 그룹을 설정
     * @param groupName
     */
    public void setMygroup(String groupName) {
        //로컬 db 업데이트
        SharedPreferences.Editor editor = sharedPreferencesUserInfo.edit();
        editor.putString(mygroupKey, groupName);
        editor.commit();
    }

    /**
     * 서버로 그룹 이름 업데이트
     * @param newGroupName
     */
    public void updateMygroup(String newGroupName){
        //서버 db 업데이트
        if(user != null) {
            user.put("group", newGroupName);
            user.saveInBackground();
        }
        //TODO : 그룹객체에서도 업데이트 (당장 필요하진 않음)
    }

    /**
     * 내 닉네임을 가져오기
     * @return
     */
    public String getNickname(){
        return sharedPreferencesUserInfo.getString(nicknameKey, "No nickname");
    }

    /**
     * 내 닉네임 설정하기
     * @param nickName
     */
    public void setNickname(String nickName){
        //로컬 db 업데이트
        SharedPreferences.Editor editor = sharedPreferencesUserInfo.edit();
        editor.putString(nicknameKey, nickName);
        editor.commit();
    }

    public void updateNickname(String newNickname){
        //서버 db 업데이트
        if(user != null) {
            user.put("nickname", newNickname);
            user.saveInBackground();
        }
    }

    /**
     * 로그인 한 후 서버에서 정보를 가져와 동기화시킨다
     * 아직 로그인한 상태가 아니므로 user 를 param 으로 받아야한다
     * @param user
     */
    public void sync(ParseUser user){
        //이름과 그룹 동기화
        setNickname((String)user.get("nickname"));
        setMygroup((String)user.get("group"));

        //포인트 동기화
        int syncPoints = (Integer)user.get("points");
        setPoints(syncPoints, 0);
        //포인트에 따라서 max point 값 만들기
        setMaxPoints((int)(Math.ceil(syncPoints/100.0)*100));
    }

    /**
     * 포인트를 로컬 db 에서 가져온다
     * @return
     */
    public int getPoints(){
        return sharedPreferencesUserInfo.getInt(pointsKey, 0);
    }

    /**
     * MAX 포인트를 로컬 db 에서 가져온다
     * @return
     */
    public int getMaxPoints(){
        return sharedPreferencesUserInfo.getInt(maxKey, 0);
    }

    /**
     * mode 에 따라서 포인트를 설정한다
     * @param points
     * @param mode : 0 또는 1 - 0이면 로컬에만 업데이트, 1은 서버까지 업데이트
     */
    public void setPoints(int points, int mode){
        //로컬에 저장
        SharedPreferences.Editor editor = sharedPreferencesUserInfo.edit();
        editor.putInt(pointsKey, points);
        editor.commit();

        if(mode == 1) updatePoints(points);
    }

    public void incrementPoints(){
        setPoints(getPoints()+50, 1);
    }

    /**
     * max 가 찼는지 확인하고 업데이트 한다
     * @return
     */
    public boolean isMaxPoints(){
        return getPoints() == getMaxPoints();
    }

    public void incrementMaxPoints(){
        setMaxPoints(getMaxPoints()+100);
    }

    /**
     * group 의 포인트를 하나 증가시켜준다
     */
    public void incrementMyGroupPoints(){
        if(mygroupObject == null) return;
        mygroupObject.put("points", (Integer)mygroupObject.get("points")+50);
        mygroupObject.saveInBackground();
    }

    /**
     * 사용자의 포인트를 업데이트 한다
     */
    public void updatePoints(int points){

        //로그인 없이 시작하기로 하면 nickname 과 group 이 없기 때문에 점수를 누적시키지 않고 종료한다.
        if(user == null) return;
        //개인 포인트 적립
        user.put("points", points);
        user.saveInBackground();
    }


    /**
     * 최대 포인트를 설정한다
     * @param maxPoints
     */
    public void setMaxPoints(int maxPoints){
        SharedPreferences.Editor editor = sharedPreferencesUserInfo.edit();
        editor.putInt(maxKey, maxPoints); // max 를 초깃값으로 초기화
        editor.commit();
    }


    /**
     * 진도율을 하나 증가시킨다
     */
    public void incrementProgress(){ setProgress(getProgress()+1); }


    /**
     * 프로그레스를 업데이트한다
     */
    public void setProgress(int progress){
        SharedPreferences.Editor editor = sharedPreferencesUserInfo.edit();
        editor.putInt(progressKey, progress); // max 를 초깃값으로 초기화
        editor.commit();
    }

    /**
     * 현재 진도율을 반환한다
     * @return
     */
    //하나의 기기여도 다른 아이디일 수 있으니 아이디에 해당하는 progress 를 가져와야겠다
    public int getProgress(){return sharedPreferencesUserInfo.getInt(progressKey, 0);}


    /**
     * 로그아웃할 때 필요한 정보들을 모두 제거해준다
     * 왜냐하면 로그인 없이 시작하기 할 때
     */
    public void reset(){
        setNickname("No name");
        setMygroup("No group");
        setPoints(0, 0);
        setMaxPoints(100);
        setProgress(1);
    }
}
