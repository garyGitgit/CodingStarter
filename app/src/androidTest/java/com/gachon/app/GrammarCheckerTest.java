package com.gachon.app;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import com.gachon.app.helper.GrammarChecker;

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
public class GrammarCheckerTest {

//    private Calculator calculator;
//    private UserManager userManager;

    @Before
    public void setUp(){

//        calculator = new Calculator();
//        userManager = new UserManager();
    }

    @Test
    public void doTest(){
        boolean validity = GrammarChecker.checkVariableValidity("num1");
        assertThat(validity, is(true));
        validity = GrammarChecker.checkVariableValidity("1num");
        assertThat(validity, is(false));
        validity = GrammarChecker.checkVariableValidity("변수");
        assertThat(validity, is(false));
        validity = GrammarChecker.checkVariableValidity("NUM");
        assertThat(validity, is(true));
        validity = GrammarChecker.checkVariableValidity("NUM**");
        assertThat(validity, is(false));
        validity = GrammarChecker.checkVariableValidity("_num");
        assertThat(validity, is(true));
        validity = GrammarChecker.checkVariableValidity("num_1");
        assertThat(validity, is(true));
        validity = GrammarChecker.checkVariableValidity("nu*1");
        assertThat(validity, is(false));
        validity = GrammarChecker.checkVariableValidity("12345");
        assertThat(validity, is(false));
        validity = GrammarChecker.checkVariableValidity("     ");
        assertThat(validity, is(false));
    }
}
