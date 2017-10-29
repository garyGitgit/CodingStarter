package com.gachon.app.main;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.gachon.app.R;
import com.gachon.app.helper.UserManager;
import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.ArrayList;
import java.util.List;

//import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements LoaderCallbacks<Cursor> {

    // 위젯
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private EditText editTextNickname;
    private EditText editTextMyGroup;

    // 위젯에서 입력받은 닉네임과 그룹
    private String nickname;
    private String mygroup;

    //사용자 정보 관리 매니저
    UserManager userManager;

    //태그
    public static String TAG = "codingstarter";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //로컬 위젯
        String fontName;
        Typeface typeface;
        final TextView title;
        Button mEmailSignInButton;
        Button goWithoutLoginButton;

        //액티비티 타이틀 폰트 설정
        fontName = getString(R.string.font_name);
        typeface = Typeface.createFromAsset(getAssets(), fontName);
        title = (TextView) findViewById(R.id.loginTitle);
        title.setTypeface(typeface);

        // 로그인 폼
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);

        //키보트 완료 버튼을 눌리면 발생하는 이벤트 리스너
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        //로그인 버튼을 눌렀을 때
        mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setTypeface(typeface);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        //로그인없이 시작하기로 바로 넘기는 버튼
        goWithoutLoginButton = (Button) findViewById(R.id.freeLoginButton);
        goWithoutLoginButton.setTypeface(typeface);
        goWithoutLoginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                Toast.makeText(getApplicationContext(), "로그인하지 않고 시작합니다.\n점수가 랭킹에 반영되지 않습니다.", Toast.LENGTH_SHORT).show();
                finish(); // 액티비티 종료
            }
        });

        //시작이 될 때 타이틀이 움직인다
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                YoYo.with(Techniques.Swing).duration(1000).playOn(title);
            }
        }, 1000);

        //사용자 관리 매니저 초기화
        userManager = UserManager.getIntance();
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        final String email = mEmailView.getText().toString();
        final String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        //사용자 입력이 올바르지 않은 경우
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        }
        //사용자 입력이 올바른 경우
        else {
            ParseQuery<ParseUser> userQuery = ParseUser.getQuery();
            userQuery.setLimit(UserManager.MAX_USER_LIMIT); //쿼리로 가져올 수 있는 max 값 설정
            userQuery.findInBackground(new FindCallback<ParseUser>() {
                @Override
                public void done(List<ParseUser> objects, ParseException e) {
                    Log.e(TAG, "User List size : " + Integer.toString(objects.size()));

                    //서버에 저장되어있는 사용자 리스트를 확인한다
                    showProgress(true); //로딩중... 시작
                    for (ParseUser user : objects) {
                        if (e == null) {
                            String userId = user.getUsername();

                            //입력받은 이메일과 일치하는 user 가 있으면 로그인 시도
                            if (userId != null && userId.equals(email)) {
                                //입력받은 이메일과 비밀번호를 서버로 전송
                                ParseUser.logInInBackground(email, password, new LogInCallback() {
                                    @Override
                                    public void done(ParseUser user, ParseException e) {
                                        showProgress(false); //로딩중... 종료

                                        //로그인 성공
                                        if (user != null) {
                                            //자동로그인 설정
                                            userManager.setAutoLogin(true);

                                            //기존에 있던 포인트 가져오기
                                            userManager.sync(user);

                                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                            Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_SHORT).show();
                                            finish(); // 액티비티 종료
                                        }
                                        //로그인 실패
                                        else {
                                            mPasswordView.setError("비밀번호 불일치");
                                            mPasswordView.requestFocus(); //패스워드 입력칸에 알림
                                            Toast.makeText(getApplicationContext(), "비밀번호 불일치", Toast.LENGTH_SHORT).show();
                                        }
                                    }//end done
                                }); //end loginbackground
                                //굳이?
//                                    showProgress(false);
                                return;
                            } //end if
                            //일치하지 않으면 다음 루프로 이동
                        }
                        //error : parse exception
                        else {
                            Toast.makeText(getApplicationContext(), "Parse Exception when getting user list", Toast.LENGTH_SHORT).show();
                            showProgress(false);
                            e.printStackTrace();
                            return;
                        }
                    }//end for : user list 탐색
                    // user list 에서 일치하는 사용자가 없으면 사용자 추가
                    createNewUser(email, password);
                }//end done : user query find in background
            });//end find in back ground
        }//end else : 사용자 입력이 올바른 경우
    }//end attempt Login method


    /**
     * @param email
     * @param password 새로운 사용자를 서버에 등록한다
     */
    private void createNewUser(final String email, final String password) {
        Log.e(TAG, "create new account");

        showProgress(true); //로딩중... 시작

        //입력받은 값 가져오기
        editTextNickname = (EditText) findViewById(R.id.nickname);
        editTextMyGroup = (EditText) findViewById(R.id.mygroup);
        nickname = editTextNickname.getText().toString();
        mygroup = editTextMyGroup.getText().toString();

        //nickname 이 없으면 에러
        if(nickname.equals("")){
            editTextNickname.setError("닉네임을 입력해야합니다");
            return;
        }

        //TODO : 닉네임 중복체크도 필요할듯
        ParseQuery<ParseUser> userParseQuery = ParseUser.getQuery();
        userParseQuery.setLimit(UserManager.MAX_USER_LIMIT);
        userParseQuery.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                for(ParseUser user : objects){
                    //같으면 다시 입력하라고 경고
                    if(user.get("nickname").equals(nickname)){
                        editTextNickname.setError("같은 이름의 닉네임이 존재합니다");
                        return;
                    }
                }
            }
        });

        ParseUser newUser = new ParseUser();
        newUser.setUsername(email); // 아이디(이메일) 등록
        newUser.setEmail(email); // 이메일 등록
        newUser.setPassword(password); // 비밀번호 등록
        newUser.put("nickname", nickname);// 닉네임 등록
        // 그룹 등록
        if(mygroup.equals(""))  newUser.put("group", "익명의그룹");
        else newUser.put("group", mygroup);
        newUser.put("points", 0); //포인트 초기화

        tryCreateNewGroup(mygroup); //새 그룹을 생성?

        //서버에 계정 생성
        newUser.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    //서버에 성공적으로 저장헀으면 같은 내용을 로컬 db 에 저장
                    userManager.setNickname(nickname); // 입력받은 닉네임 설정
                    userManager.setMygroup(mygroup); // 입력받은 그룹 이름 설정
                    userManager.setMaxPoints(100); // 초기 max 값 설정
                    userManager.setAutoLogin(true); //자동 로그인 설정

                    Toast.makeText(getApplicationContext(), "등록 성공", Toast.LENGTH_SHORT).show();
                    showProgress(false);
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), "서버에러 : " + e.toString(), Toast.LENGTH_SHORT).show();
                    showProgress(false);
                }
            }
        });
        Toast.makeText(getApplicationContext(), "새로운 계정을 만들었습니다", Toast.LENGTH_SHORT).show();
    }


    /**
     * @param groupName
     * 서버 DB 를 검색해서 그룹이 없으면 새로운 그룹을 생성하고 그렇지 않으면 그룹이 존재한다는 메시지만 던져준다
     */
    private void tryCreateNewGroup(final String groupName){
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Groups");
        query.whereEqualTo("name", groupName);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e == null){
                    //그룹이 존재하지 않음
                    if(objects.size() == 0){
                        Toast.makeText(getApplicationContext(), groupName +  " : 그룹을 새로 만듭니다", Toast.LENGTH_SHORT).show();
                        ParseObject newGroup = new ParseObject("Groups");
                        newGroup.put("name", groupName);
                        newGroup.put("points", 0);
                        newGroup.saveInBackground();
                    }
                    //그룹이 존재함
                    else{
                        Toast.makeText(getApplicationContext(), groupName +  " : 그룹이 존재합니다", Toast.LENGTH_SHORT).show();
                    }
                }// end if
                else{
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), groupName +  " Parse Exception : try create new group", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * @param email
     * @return
     * 이메일 체크
     */
    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    /**
     * @param password
     * @return
     * 비밀번호 체크
     */
    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() >= 4;
    }

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
        final View mLoginFormView; //로그인 뷰
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

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
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }


    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }
}

