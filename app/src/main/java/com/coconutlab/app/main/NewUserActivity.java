package com.coconutlab.app.main;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.coconutlab.app.R;
import com.coconutlab.app.helper.UserManager;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.List;

public class NewUserActivity extends AppCompatActivity {

    //태그
    public static String TAG = "COCONUTLAB";

    // 위젯
    private EditText mNicknameView;
    private EditText mEmailView;
    private EditText mPasswordView;
    private Button mNewAccountButton;

    //사용자 정보
    private String new_email;
    private String new_password;
    private String new_nickname;

    //TODO : group 검색해서 등록하기

    UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        //위젯 로드
        mNicknameView = (EditText)findViewById(R.id.new_nickname);
        mEmailView = (EditText)findViewById(R.id.new_email);
        mPasswordView = (EditText)findViewById(R.id.new_password);
        mNewAccountButton = (Button)findViewById(R.id.createNewAccountButton);

        userManager = UserManager.getIntance();

        //버튼 입력
        mNewAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //입력 형식 체크 후 사용자 생성
                if(isNicknameValid() && isEmailValid() && isPasswordValid()){
                    createNewUser(new_nickname, new_email, new_password);
                }
            }
        });
    }


    /**
     * 새로운 계정 생성하기
     * @param new_nickname
     * @param new_email
     * @param new_password
     */
    private void createNewUser(final String new_nickname, final String new_email, final String new_password) {
        //showProgress(true); //로딩중... 시작

        //parse query 설정
        ParseQuery<ParseUser> userParseQuery = ParseUser.getQuery();
        userParseQuery.setLimit(UserManager.MAX_USER_LIMIT);

        userParseQuery.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                for(ParseUser user : objects){

                    //중복체크

                    //닉네임 확인
                    if(user.get("nickname") != null && user.get("nickname").equals(new_nickname)){
                        mNicknameView.setError("같은 이름의 닉네임이 존재합니다");
                        Toast.makeText(getApplicationContext(), "같은 이름의 닉네임이 존재합니다", Toast.LENGTH_SHORT).show();
                        //showProgress(false);
                        return;
                    }

                    //이메일 확인
                    if(user.get("email") != null && user.get("email").equals(new_email)){
                        mEmailView.setError("같은 이메일이 존재합니다");
                        Toast.makeText(getApplicationContext(), "같은 이메일이 존재합니다", Toast.LENGTH_SHORT).show();
                        //showProgress(false);
                    }
                }//end for

                //중복체크 성공 계정 생성
                ParseUser newUser = new ParseUser();
                newUser.setUsername(new_email); // 아이디(이메일) 등록
                newUser.setEmail(new_email); // 이메일 등록
                newUser.setPassword(new_password); // 비밀번호 등록
                newUser.put("nickname", new_nickname);// 닉네임 등록

                newUser.put("points", 0); //포인트 초기화

                //서버에 계정 생성
                newUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            //서버에 성공적으로 저장헀으면 같은 내용을 로컬 db 에 저장
                            userManager.setNickname(new_nickname); // 입력받은 닉네임 설정
                            userManager.setMaxPoints(100); // 초기 max 값 설정
                            userManager.setAutoLogin(true); //자동 로그인 설정

                            Toast.makeText(getApplicationContext(), "등록 성공", Toast.LENGTH_SHORT).show();
                            //showProgress(false);
                            startActivity(new Intent(NewUserActivity.this, MainActivity.class));
                            finish();
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "서버에러 : " + e.toString(), Toast.LENGTH_SHORT).show();
                            //showProgress(false);
                        }
                    }
                });
                Toast.makeText(getApplicationContext(), "새로운 계정을 만들었습니다", Toast.LENGTH_SHORT).show();
            }
        });
    }



    //TODO : 그룹 생성 기능 추가 할 때 필요
//    /**
//     * @param grupName
//     * 서버 DB 를 검색해서 그룹이 없으면 새로운 그룹을 생성하고 그렇지 않으면 그룹이 존재한다는 메시지만 던져준다
//     */
//    private void tryCreateNewGroup(final String groupName){
//        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Groups");
//        query.whereEqualTo("name", groupName);
//        query.findInBackground(new FindCallback<ParseObject>() {
//            @Override
//            public void done(List<ParseObject> objects, ParseException e) {
//                if(e == null){
//                    //그룹이 존재하지 않음
//                    if(objects.size() == 0){
//                        Toast.makeText(getApplicationContext(), groupName +  " : 그룹을 새로 만듭니다", Toast.LENGTH_SHORT).show();
//                        ParseObject newGroup = new ParseObject("Groups");
//                        newGroup.put("name", groupName);
//                        newGroup.put("points", 0);
//                        newGroup.saveInBackground();
//                    }
//                    //그룹이 존재함
//                    else{
//                        Toast.makeText(getApplicationContext(), groupName +  " : 그룹이 존재합니다", Toast.LENGTH_SHORT).show();
//                    }
//                }// end if
//                else{
//                    e.printStackTrace();
//                    Toast.makeText(getApplicationContext(), groupName +  " Parse Exception : try create new group", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }


    /**
     * 이메일 체크
     * @return
     */
    private boolean isEmailValid() {
        //TODO: Replace this with your own logic
        new_email = mEmailView.getText().toString();
        if(!new_email.contains("@") || new_email.equals("")){
            //입력받은 값 가져오기

            mEmailView.setError("이메일 형식을 지켜주세요");
            return false;
        }
        else return true;
    }

    /**
     * 비밀번호 체크
     * @return
     */
    private boolean isPasswordValid() {
        new_password = mPasswordView.getText().toString();
        //TODO: Replace this with your own logic
        if(new_password.length() < 4) {
            //입력받은 값 가져오기
            mPasswordView.setError("4자리 이상 입력해주세요");
            return false;
        }
        else return true;
    }

    /**
     * 닉네임 체크
     * @return
     */
    private  boolean isNicknameValid(){
        new_nickname = mNicknameView.getText().toString();
        if(new_nickname.equals("")){
            //입력받은 값 가져오기

            mNicknameView.setError("닉네임을 입력해주세요");
            return false;
        }
        else return true;
    }


    //TODO : progress 보여주는게 좋을 듯
    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.

        //위젯
        final View mProgressView; //로그인 중일 때 나타나는 progress
        //final View mLoginFormView; //로그인 뷰
        //mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

//            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
//            mLoginFormView.animate().setDuration(shortAnimTime).alpha(             show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
//                @Override
//                public void onAnimationEnd(Animator animation) {
////                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
//                }
//            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
//            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

}
