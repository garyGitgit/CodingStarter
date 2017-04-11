package com.gachon.app;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;


public class MainActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener{


    public static String TAG = "tag";
    //17.2.8 : pager
    ViewPager viewPager;
    FragmentPagerItems pages;
    //17.4.3 : number of page items
    public final static int pageCount = 3;

    onBluetoothMessageReceived mListener, mListener2, mListener3, mListener4;

    //17.2.18 : 설정 탭 누르는 것 방지
    static long tabClickTime = 0;
    static final long tabClickWaitingTIme = 500;


    /* ################################### start of life cycle ###################################### */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_drawer); //서비스가 바인딩된 후 controlactivity 가 생성

        //17.2.7 : 특정 탭 모양에 따른 enum value 가져오기
        TabEnum tabEnum = getDemo();

        //17.2.7 : 툴바 설정
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(null);

        setSupportActionBar(toolbar);
        //TODO gachon : delete menu
        invalidateOptionsMenu();


        //drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        //17.2.7 : 하단 탭 설정
        ViewGroup tab = (ViewGroup) findViewById(R.id.tab);
        tab.addView(LayoutInflater.from(this).inflate(tabEnum.layoutResId, tab, false));

        //17.2.7 : 뷰 페이저, 스마트 탭 설정
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        final SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        tabEnum.setup(viewPagerTab);

        //17.2.7 : 뷰페이저 페이지 설정
        pages = new FragmentPagerItems(this);

        pages.add(FragmentPagerItem.of(getString(tabEnum.tabs()[0]), Fragment1.class));
        pages.add(FragmentPagerItem.of(getString(tabEnum.tabs()[1]), Fragment2.class));
        pages.add(FragmentPagerItem.of(getString(tabEnum.tabs()[2]), Fragment3.class));

        viewPagerTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "tab touched", Toast.LENGTH_SHORT).show();
            }
        });

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), pages);

        //페이지 값 유지
        viewPager.setOffscreenPageLimit(pageCount);

        //어답터 설정
        viewPager.setAdapter(adapter);
        //17.2.9 : 슬라이드 방지, 2.16 : 안 먹힘
        viewPagerTab.setViewPager(viewPager);


        try {
            //17.4.3. : 0 ~ 3 은 위에서 만든 페이지 수와 같아야 함
            Fragment mFragment = adapter.getItem(0);
            Fragment mFragment2 = adapter.getItem(1);
            Fragment mFragment3 = adapter.getItem(2);
            //Fragment mFragment4 = adapter.getItem(3);
            //이게 null

            mListener = (onBluetoothMessageReceived) mFragment;
            mListener2 = (onBluetoothMessageReceived) mFragment2;
            //TODO : 주석을 해제하면 null point error 가 발생, 어디인지 확인해보자
            mListener3 = (onBluetoothMessageReceived) mFragment3;
            //mListener4 = (onBluetoothMessageReceived) mFragment4;
        }
        catch (ClassCastException e) {
          throw new ClassCastException("must implement onBluetoothMessageReceived Listener");
        }
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    /**
     * 켜질 때 항상 레이아웃을 초기화시킨다
     */
    @Override
    protected void onResume() {
        Log.d(TAG, "onResume");
        //17.2.7 : 레이아웃 설정을 메인에서 할 필요가 없음
        //TODO onViewCreated 로 이동
        super.onResume();
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
    public interface onBluetoothMessageReceived{
        void onMessageReceived(byte[] data);
    }
}
