package com.gachon.app;

/**
 * Created by garyNoh on 17. 1. 26..
 * 메인화면
 * 1. 잠금/열림 버튼
 * 2. 자동/수동 모드
 * 3. 배터리
 * 4. drawer 메뉴
 *
 * reference : https://github.com/wasabeef/awesome-android-ui
 **/

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;


public class MainActivity extends AppCompatActivity{


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
        setContentView(R.layout.activity_main); //서비스가 바인딩된 후 controlactivity 가 생성

        //17.2.7 : 특정 탭 모양에 따른 enum value 가져오기
        TabEnum tabEnum = getDemo();

        //17.2.7 : 툴바 설정
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(null);

        setSupportActionBar(toolbar);
        //TODO gachon : delete menu
        invalidateOptionsMenu();

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
