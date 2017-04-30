package com.gachon.app;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by garyNoh on 2017. 4. 30..
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class TestSample {

    private Calculator calculator;

    @Before
    public void setUp(){
        calculator = new Calculator();
    }

    @Test
    public void doTest(){
        int result = calculator.add(1,2);
        assertThat(result, is(3));
    }
}
