package com.coconutlab.app.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.coconutlab.app.main.intro_pages.IntroPage1;
import com.coconutlab.app.main.intro_pages.IntroPage2;
import com.coconutlab.app.main.intro_pages.IntroPage3;
import com.github.paolorotolo.appintro.AppIntro;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

/**
 * 코드 출처 : https://github.com/apl-devs/AppIntro
 */
public class IntroActivity extends AppIntro {

    FragmentPagerItems pages;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Note here that we DO NOT use setContentView();

        // Add your slide fragments here.
        // AppIntro will automatically generate the dots indicator and buttons.
        //TODO : 내 이미지로 대체
//
//
//
//        Fragment firstFragment = getSupportFragmentManager().findFragmentById(R.id.intro_1);
//        addSlide(firstFragment);
//        addSlide(secondFragment);
//        addSlide(thirdFragment);

        Fragment introPage1 = new IntroPage1();
        Fragment introPage2 = new IntroPage2();
        Fragment introPage3 = new IntroPage3();


        addSlide(introPage1);
        addSlide(introPage2);
        addSlide(introPage3);


//        String title = "코코넛랩";
//        String description = "누구나 쉽게 코딩을 배우, 코딩 교육의 출발점";
        // Instead of fragments, you can also use our default slide
        // Just set a title, description, background and image. AppIntro will do the rest.

//        addSlide(newInstance(title, description, 0, Color.parseColor("#00C8A8")));
//        addSlide(newInstance("title2", description, 0, Color.parseColor("#00C8A8")));
//        addSlide(newInstance("title3", description, 0, Color.parseColor("#00C8A8")));

        // OPTIONAL METHODS
        // Override bar/separator color.
        //setBarColor(Color.parseColor("#3F51B5"));
        //setSeparatorColor(Color.parseColor("#2196F3"));

        // Hide Skip/Done button.
        showSkipButton(true);
        setProgressButtonEnabled(true);

        // Turn vibration on and set intensity.
        // NOTE: you will probably need to ask VIBRATE permission in Manifest.
        setVibrate(true);
        setVibrateIntensity(30);
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        // Do something when users tap on Skip button.
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        // Do something when users tap on Done button.
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.
    }
}
