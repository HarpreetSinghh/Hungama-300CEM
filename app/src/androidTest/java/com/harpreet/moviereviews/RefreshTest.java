package com.harpreet.moviereviews;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class RefreshTest {

    @Rule
    public ActivityTestRule<Startanimation> mActivityTestRule = new ActivityTestRule<>(Startanimation.class);

    @Test
    public void refreshTest() {


        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.floatingActionButton),
                        withParent(allOf(withId(R.id.activity_starter),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));



        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.title), withText("Refresh"), isDisplayed()));


    }

}
