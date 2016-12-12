package com.harpreet.moviereviews;


import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.core.deps.guava.util.concurrent.ExecutionError;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;

import com.harpreet.moviereviews.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;


@RunWith(AndroidJUnit4.class)
public class LoginTest {

    @Rule
    public ActivityTestRule<Startanimation> mActivityTestRule = new ActivityTestRule<>(Startanimation.class);

    @Test
    public void loginTest() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.signinlogin), withText("sign in with Desi Hungama"),
                        withParent(allOf(withId(R.id.activity_starter),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
try{
Thread.sleep(3500);}
catch (Exception e){

}
        onView(withId(R.id.signinlogin)).perform(click());
        onView(withId(R.id.eduser)).perform(replaceText("singhh34"), closeSoftKeyboard());


        onView(withId(R.id.edpass)).perform(replaceText("gurfateh6"), closeSoftKeyboard());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.login), withText("Login"), isDisplayed()));


    }

}
