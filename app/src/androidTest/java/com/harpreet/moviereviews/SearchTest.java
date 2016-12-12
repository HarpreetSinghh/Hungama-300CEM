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
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;


@RunWith(AndroidJUnit4.class)
public class SearchTest {

    @Rule
    public ActivityTestRule<Startanimation> mActivityTestRule = new ActivityTestRule<>(Startanimation.class);

    @Test
    public void searchTest() {


        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.floatingActionButton),
                        withParent(allOf(withId(R.id.activity_starter),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));


        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.title), withText("Refresh"), isDisplayed()));


        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.action_search), withContentDescription("Search"), isDisplayed()));


        ViewInteraction editText = onView(
                allOf(withClassName(is("android.widget.EditText")),
                        withParent(allOf(withId(android.R.id.custom),
                                withParent(withClassName(is("android.widget.FrameLayout"))))),
                        isDisplayed()));
        editText.perform(replaceText("Devdas"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(android.R.id.button1), withText("OK"), isDisplayed()));


    }

}
