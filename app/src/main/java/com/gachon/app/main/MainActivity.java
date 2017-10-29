package com.gachon.app.main;

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

import com.gachon.app.R;
import com.gachon.app.TabEnum;
import com.gachon.app.helper.MyViewPager;
import com.gachon.app.helper.UserManager;
import com.gachon.app.helper.WidgetSet;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;


/**
 * 메인화면
 *
 * 페이지 1 : 학습 리스트
 * 페이지 2 : 그룹 랭킹
 */
public class MainActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener{

    public static String TAG = " COCONUT";

    //뷰 페이저
    MyViewPager viewPager;
    FragmentPagerItems pages;
    //페이지 숫자
    public final static int pageCount = 2;
    //페이지 리스너
    onFragmentMessageReceived mListener, mListener2;

    TextView levelText;
    UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_drawer); //서비스가 바인딩된 후 controlactivity 가 생성

        //특정 탭 모양에 따른 enum value 가져오기
        TabEnum tabEnum = getDemo();

        //툴바 설정
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(null);
        setSupportActionBar(toolbar);
        invalidateOptionsMenu();


        //drawer 메뉴 설정
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //하단 탭 설정
        ViewGroup tab = (ViewGroup) findViewById(R.id.tab);
        tab.addView(LayoutInflater.from(this).inflate(tabEnum.layoutResId, tab, false));

        //뷰 페이저, 스마트 탭 설정
        viewPager = (MyViewPager)findViewById(R.id.viewpager);
        final SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        tabEnum.setup(viewPagerTab);

        //뷰페이저 페이지 1,2 설정
        pages = new FragmentPagerItems(this);
        pages.add(FragmentPagerItem.of(getString(tabEnum.tabs()[0]), Fragment1.class));
        pages.add(FragmentPagerItem.of(getString(tabEnum.tabs()[1]), Fragment2.class));

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), pages);

        //페이지 값 유지
        viewPager.setOffscreenPageLimit(pageCount);

        //어답터 설정
        viewPager.setAdapter(adapter);
        viewPagerTab.setViewPager(viewPager);
        //슬라이드 방지
        viewPager.setPagingEnabled(false);

        try {
            //17.4.3. : 0 ~ 3 은 위에서 만든 페이지 수와 같아야 함
            Fragment mFragment = adapter.getItem(0);
            Fragment mFragment2 = adapter.getItem(1);

            mListener = (onFragmentMessageReceived) mFragment;
            mListener2 = (onFragmentMessageReceived) mFragment2;
        }
        catch (ClassCastException e) {
          throw new ClassCastException("must implement onBluetoothMessageReceived Listener");
        }

        //dp 설정
        WidgetSet.setScale(getResources().getDisplayMetrics().density);

        //위젯
        levelText = (TextView)findViewById(R.id.levelText);

        //툴바에 나오는 닉네임 설정
        userManager = UserManager.getIntance();
        userManager.init(getApplicationContext()); //꼭 해야한다

        TextView nickname = (TextView)findViewById(R.id.user_nickname);
        nickname.setText(userManager.getNickname() + "(" + userManager.getMygroup() + ")");

    }

    private TabEnum getDemo() {
        return TabEnum.values()[0];
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    /**
     * 켜질 때 항상 레이아웃을 초기화시킨다
     */
    //업데이트된 경험치를 반영한다
    @Override
    protected void onResume() {


        Log.d(TAG, "onResume");
        //17.2.7 : 레이아웃 설정을 메인에서 할 필요가 없음
        //TODO onViewCreated 로 이동
        super.onResume();
        Log.e(TAG, "max point : " + Integer.toString(userManager.getMaxPoints()));
        Log.e(TAG, "points : " + Integer.toString(userManager.getPoints()));
        ImageView levelBadge = (ImageView)findViewById(R.id.levelBadge);

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



    /* ################################### end of loading thread #################################### */

    //17.2.10
    public interface onFragmentMessageReceived{
        void onMessageReceived(byte[] data);
    }
}
