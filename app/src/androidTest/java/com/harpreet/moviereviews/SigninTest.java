package com.harpreet.moviereviews;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
public class SigninTest {

    @Rule
    public ActivityTestRule<Startanimation> mActivityTestRule = new ActivityTestRule<>(Startanimation.class);

    @Test
    public void signinTest() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.signinlogin), withText("sign in with Desi Hungama"),
                        withParent(allOf(withId(R.id.activity_starter),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));


    }

}
