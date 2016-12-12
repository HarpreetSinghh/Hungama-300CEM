package com.harpreet.moviereviews;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;


@RunWith(AndroidJUnit4.class)
public class RegisterTest {

    @Rule
    public ActivityTestRule<Startanimation> mActivityTestRule = new ActivityTestRule<>(Startanimation.class);

    @Test
    public void registerTest() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.signinlogin), withText("sign in with Desi Hungama"),
                        withParent(allOf(withId(R.id.activity_starter),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));

try {
Thread.sleep(3500);}
catch (Exception e){
}

        onView(withId(R.id.not_reg)).perform(click());

        onView(withId(R.id.editfirstname)).perform(replaceText("harpreet"), closeSoftKeyboard());
        onView(withId(R.id.editlastname)).perform(replaceText("singh"), closeSoftKeyboard());
        onView(withId(R.id.editemail)).perform(replaceText("harpreet96singh@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.editusername)).perform(replaceText("singhh34"), closeSoftKeyboard());
        onView(withId(R.id.editpassword)).perform(replaceText("gurfateh6"), closeSoftKeyboard());
        onView(withId(R.id.editconformpassword)).perform(replaceText("gurfateh6"), closeSoftKeyboard());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.btnsave), withText("btnsave"), isDisplayed()));


    }

}