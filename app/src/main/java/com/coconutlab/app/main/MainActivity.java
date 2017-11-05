package com.coconutlab.app.main;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.coconutlab.app.R;
import com.coconutlab.app.TabEnum;
import com.coconutlab.app.helper.MyViewPager;
import com.coconutlab.app.helper.UserManager;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;


/**
 * 메인화면 설정
 *
 * 1. (상단) toolbar 설정
 * 2. (왼쪽) drawer 설정
 * 3. (하단) tab 설정 (페이지1, 페이지2)
 *
 * 페이지1. 학습 리스트
 * 페이지2. 그룹 랭킹 페이지
 *
 */
public class MainActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener{

    public static String TAG = "COCONUTLAB";

    //viewpager 구성 요소
    MyViewPager viewPager;
    FragmentPagerItems pages;
    public final static int pageCount = 2; //페이지 수
    onFragmentMessageReceived mListener, mListener2; //페이지 이벤트 리스너

    //상단 툴바 위젯
    DrawerLayout drawer;
    TextView levelText;
    UserManager userManager;
    ImageView levelBadge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_drawer); //서비스가 바인딩된 후 controlactivity 가 생성

        //toolbar 설정
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(null);
        setSupportActionBar(toolbar);
        invalidateOptionsMenu();



        //drawer 설정 : 메뉴 설정
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        //tab 설정 :
        TabEnum tabEnum = getDemo();
        ViewGroup tab = (ViewGroup) findViewById(R.id.tab);
        tab.addView(LayoutInflater.from(this).inflate(tabEnum.layoutResId, tab, false));

        //tab 설정 : 셋업
        viewPager = (MyViewPager)findViewById(R.id.viewpager);
        final SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        tabEnum.setup(viewPagerTab);

        //tab 설정 : page 추가
        pages = new FragmentPagerItems(this);
        pages.add(FragmentPagerItem.of(getString(tabEnum.tabs()[0]), Fragment1.class));
        pages.add(FragmentPagerItem.of(getString(tabEnum.tabs()[1]), Fragment2.class));


        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), pages);




        viewPager.setOffscreenPageLimit(pageCount); //최대 페이지 값 유지
        viewPager.setPagingEnabled(false); //슬라이드로 페이지 넘기는 것 방지

        //tab 설정 : 어답터 설정
        viewPager.setAdapter(adapter);
        viewPagerTab.setViewPager(viewPager);

        try {
            //0 ~ 1 은 위에서 만든 페이지 수와 같아야 함
            Fragment mFragment = adapter.getItem(0);
            Fragment mFragment2 = adapter.getItem(1);
            mListener = (onFragmentMessageReceived) mFragment;
            mListener2 = (onFragmentMessageReceived) mFragment2;
        }
        catch (ClassCastException e) {
          throw new ClassCastException("must implement onMessageReceived Listener");
        }


        //TODO : 꼭 메인 액티비티에서 해야하나. dp 설정하는거면 SplashActivity 에서 해도 될거 같은데
//        //WidgetSet 을 위해서 dp 설정
//        WidgetSet.setScale(getResources().getDisplayMetrics().density);

        //UserManager 클래스로 사용자 정보 초기화
        userManager = UserManager.getIntance(); //싱글톤 인스턴스를 생성한다
        //TODO : init 도 한번만 하면 안되나 : 이거는 appclication context 를 가지고 있어야해서 매번 초기화시켜야한다
        userManager.init(getApplicationContext()); //인스턴스 초기화를 한다 (context)


        //위젯 초기화
        levelText = (TextView)findViewById(R.id.levelText); //사용자 레벨
        TextView nickname = (TextView)findViewById(R.id.user_nickname); //사용자 닉네임
        nickname.setText(userManager.getNickname() + "(" + userManager.getMygroup() + ")");
        levelBadge = (ImageView)findViewById(R.id.levelBadge);

    }

    private TabEnum getDemo() {
        return TabEnum.values()[0];
    }


    /**
     * 뒤로가기를 눌렀을 때의 동작을 설정한다
     */
    @Override
    public void onBackPressed() {
        //drawer 가 열려있으면 닫는다
        if (drawer.isDrawerOpen(GravityCompat.START)) drawer.closeDrawer(GravityCompat.START);
        //drawer 가 열려있지 않으면 뒤로가기를 한다
        else super.onBackPressed();
    }

    /**
     * 상단 우측 숨김 메뉴를 inflate 한다
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.reset) {
            Toast.makeText(MainActivity.this, "경험치를 초기화합니다", Toast.LENGTH_SHORT).show();
            userManager.reset();
            return true;
        }
        //로그아웃을 하면 자동 로그인 해제에 포인트를 초기화한다 (혹시라도 다른 계정으로 로그인할 수 있으니까)
        else if(id == R.id.logout){
            Toast.makeText(MainActivity.this, "로그아웃합니다. 다시 시작해주세요", Toast.LENGTH_SHORT).show();
            userManager.setAutoLogin(false);
            userManager.reset();
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * 메뉴 아이템 눌렀을 때 발생하는 이벤트 처리
     * @param item
     * @return
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        }

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * 켜질 때 항상 레이아웃을 초기화시킨다
     */
    @Override
    protected void onResume() {
        Log.d(TAG, "onResume");
        //TODO onViewCreated 로 이동
        super.onResume();
        //test : 현재 올바른 max point 인지 확인
        Log.e(TAG, "max point : " + Integer.toString(userManager.getMaxPoints()));
        //Log.e(TAG, "points : " + Integer.toString(userManager.getPoints()));

        refreshMainActivity();
    }

    /**
     * 사용자의 레벨과 뱃지 색깔을 업데이트 해준다
     */
    private void refreshMainActivity(){
        switch (userManager.getMaxPoints()){
            case 100:
                levelBadge.setImageDrawable(getResources().getDrawable(R.drawable.level1));
                levelText.setText("Lv.1");
                break;
            case 200:
                levelBadge.setImageDrawable(getResources().getDrawable(R.drawable.level2));
                levelText.setText("Lv.2");
                break;
            case 300:
                levelBadge.setImageDrawable(getResources().getDrawable(R.drawable.level3));
                levelText.setText("Lv.3");
                break;
            case 400:
                levelBadge.setImageDrawable(getResources().getDrawable(R.drawable.level4));
                levelText.setText("Lv.4");
                break;
            case 500:
                levelBadge.setImageDrawable(getResources().getDrawable(R.drawable.level5));
                levelText.setText("Lv.5");
                break;
            case 600:
                levelBadge.setImageDrawable(getResources().getDrawable(R.drawable.level6));
                levelText.setText("Lv.6");
                break;
        }
    }


    @Override
    protected  void onPause() {
        Log.d(TAG, "onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }



    //17.2.10
    public interface onFragmentMessageReceived{
        void onMessageReceived(byte[] data);
    }
}
