package com.gachon.app.course1_1.course1_1_1;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.gachon.app.R;
import com.gachon.app.helper.MainPagerAdapter;
import com.gachon.app.helper.PageHelper;
import com.gachon.app.helper.ViewFactoryCS;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Course1_1_1Step0 extends Fragment {

    //항상 추가
    View root; // 부모 액티비티
    ViewFactoryCS viewFactory;

    ArrayList<Fragment> cards = new ArrayList<>();
    //layouts

    RelativeLayout[] cardCover = new RelativeLayout[3];
    int cardBackgroundColor;

    Handler mHandler = new Handler();

    ArrayList<Integer> slideCardNum = new ArrayList<>();


    public Course1_1_1Step0() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //항상 추가
        root = inflater.inflate(R.layout.fragment_g_step0, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //최상단 루트 레이아웃
        cardBackgroundColor = getResources().getColor(R.color.codingstarter_background);

        LinearLayout layout = (LinearLayout) root.findViewById(R.id.fragment_g_step0);
        viewFactory = new ViewFactoryCS(layout);

        //space 추가
//        viewFactory.addSpace(0.5f);

        //animation card 생성
        //FrameLayout animCard = viewFactory.createCard(3.0f, new int[]{0,0,0,PageHelper.defaultMargin});
        viewFactory.createAnimationCard(3.0f, R.raw.course1_1_1_step0, new int[]{0,0,0, PageHelper.defaultMargin});

        //viewpager card 생성
        //FrameLayout slideCard = viewFactory.createCard(1.0f, new int[]{0,0,0,PageHelper.defaultMargin});

        //slide card 생성
//        LinearLayout slideCard_linear = (LinearLayout)getActivity().getLayoutInflater().inflate(R.layout.slidecard, null);
//        MyViewPager viewPager = (MyViewPager) slideCard_linear.findViewById(R.id.slideCard_viewPager);

//        MainPagerAdapter pagerAdapter = viewFactory.createSlideCard(1.0f, new int[]{0,0,0,0}, viewPager, slideCard_linear);
        //MyViewPager viewPager = new MyViewPager(getContext());
        final ViewPager viewPager = new ViewPager(getContext());
        viewPager.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        MainPagerAdapter pagerAdapter = viewFactory.createSlideCard(1.0f, new int[]{0,0,0,0}, viewPager);
        slideCardNum.add(0);

        Activity parentActivity = getActivity();
        viewFactory.addCardOnSlideCard("프로그램은 수학적인 문제를 해결하기 위해서 만들어졌다.", pagerAdapter, parentActivity);
        //viewFactory.addCardOnSlideCard("이런 의문이 들었다. 1 + 2 를 계산한 결과를 저장하고 싶은데, 컴퓨터에 어떻게 저장하지?", pagerAdapter, parentActivity);
        viewFactory.addCardOnSlideCard("그래서 이런 공간을 마련한 것이 '변수'다!", pagerAdapter, parentActivity);
        viewFactory.addCardOnSlideCard("다음", pagerAdapter, parentActivity);

        //final AutoResizeTextView autoResizeTextView = (AutoResizeTextView)viewFactory.createWidget("TextView", new String[] {"1"});

        //거꾸로 입력
//        viewFactory.addNextCard(slideCard, getActivity()); //다음페이지로 넘어가는 카드 추가
//        viewFactory.addText("그래서 이런 공간을 마련한 것이 '변수'다!", slideCard);
//        viewFactory.addText("이런 의문이 들었다. 1 + 2 를 계산한 결과를 저장하고 싶은데, 컴퓨터에 어떻게 저장하지?", slideCard);
//        viewFactory.addText("프로그램은 수학적인 문제를 해결하기 위해서 만들어졌다.", slideCard);

        //space 추가
        viewFactory.addSpace(0.5f);



        /* 페이지 넘아가는 버튼 */

        //image button
        ImageButton goNext = (ImageButton)root.findViewById(R.id.goNext);
        goNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int thisPage = viewPager.getCurrentItem();
                int pageNum = viewPager.getChildCount();

                if (thisPage < pageNum-1) {
                    viewPager.setCurrentItem(++thisPage);
                }
                else{
                    Toast.makeText(getActivity().getApplicationContext(), "next", Toast.LENGTH_SHORT).show();
                    ViewFactoryCS.onGoNext onGoNext = (ViewFactoryCS.onGoNext)getActivity();
                    onGoNext.onPressNext();
                }
            }
        });

        ImageButton goPrev= (ImageButton)root.findViewById(R.id.goPrevious);
        goPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int thisPage = viewPager.getCurrentItem();

                if (thisPage > 0) {
                    viewPager.setCurrentItem(--thisPage);
                }
                else{
                    ViewFactoryCS.onGoPrevious onGoPrev= (ViewFactoryCS.onGoPrevious)getActivity();
                    onGoPrev.onPressPrev();
                }

            }
        });


//        ViewPager viewPager = new ViewPager(root.getContext());
//        viewPager.setAdapter(new PageAdapter(getActivity().getSupportFragmentManager()));
//        slideCard.addView(viewPager);

        //ViewPager viewPagerCard



        //cardcover 로딩
//        LayoutInflater inflater = (LayoutInflater)root.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        for(int i = 0 ; i < 3; i++){
//            //카드 생성
//            textCard[i] = viewFactory.createCard(1.0f, new int[]{0,0,0, PageHelper.defaultMargin});
//        }
//        //텍스트 추가
//        viewFactory.addSimpleText("프로그램은 수학적인 문제를 해결하기 위해서 만들어졌다.", 18, textCard[0]);
//        viewFactory.addSimpleText("이런 의문이 들었다. 1 + 2 를 계산한 결과를 저장하고 싶은데, 컴퓨터에 어떻게 저장하지?", 18, textCard[1]);
//        viewFactory.addSimpleText("그래서 이런 공간을 마련한 것이 '변수'다!", 18, textCard[2]);
//
//        //카드로 덮기
//        for(int i = 0 ; i < 3; i++){
//            //card 커버 로드
//            cardCover[i] = new RelativeLayout(getContext());
//            inflater.inflate(R.layout.cardcover, cardCover[i]);
//            //카드 눌렀을 때 카드 사라지기
//            textCard[i].setTag(i);
//            textCard[i].setOnClickListener(new onCardClicked());
//            //card 로 덮기
//            textCard[i].addView(cardCover[i]);
//        }

    }

    public interface onPressGoNext{
        public void onPressed();
    }

//    private class PageAdapter extends FragmentStatePagerAdapter{
//        public PageAdapter(android.support.v4.app.FragmentManager fm)
//        {
//            super(fm);
//        }
//        @Override
//        public android.support.v4.app.Fragment getItem(int position)
//        {
//            switch(position)
//            {
//                case 0:
//                    //return new FirstFragment();
//                case 1:
//                    //return new SecondFragment();
//                case 2:
//                    //return new ThirdFragment();
//                default:
//                    return null;
//            }
//        }
//        @Override
//        public int getCount()
//        {
//            return 3;
//        }
//    }


//    class onCardClicked implements View.OnClickListener{
//        @Override
//        public void onClick(View v) {
//            int tag = ((Integer)v.getTag());
//            switch (tag){
//                case 0:
//                    YoYo.with(Techniques.FadeOut).duration(PageHelper.cardOpenDelay).playOn(cardCover[0]);
//                    break;
//                case 1:
//                    YoYo.with(Techniques.FadeOut).duration(PageHelper.cardOpenDelay).playOn(cardCover[1]);
//                    break;
//                case 2:
//                    YoYo.with(Techniques.FadeOut).duration(PageHelper.cardOpenDelay).playOn(cardCover[2]);
//                    break;
//            }
//        }
//    }
}
