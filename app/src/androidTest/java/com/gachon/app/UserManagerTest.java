package com.gachon.app;

import android.app.Application;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import android.test.ApplicationTestCase;

import com.gachon.app.helper.UserManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@SmallTest
/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class UserManagerTest extends ApplicationTestCase<Application> {

    UserManager userManager;

    public UserManagerTest() {
        super(Application.class);


    }

    @Before
    public void setup(){
        userManager = new UserManager(InstrumentationRegistry.getContext());
        userManager.reset();

    }

    @Test
    public void doTest(){
        assertEquals(0, userManager.getPoints());
        assertEquals(100, userManager.getMaxPoints());
        userManager.addPoints();
        assertEquals(0, userManager.getPoints());
        assertEquals(200, userManager.getMaxPoints());
        userManager.addPoints();
        assertEquals(100, userManager.getPoints());
        assertEquals(200, userManager.getMaxPoints());
        userManager.addPoints();
        assertEquals(0, userManager.getPoints());
        assertEquals(300, userManager.getMaxPoints());
        userManager.addPoints();
        assertEquals(100, userManager.getPoints());
        assertEquals(300, userManager.getMaxPoints());
    }
}