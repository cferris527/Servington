package com.example.servington_from_ground_up;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

/**
 * Instrumented tests for Volunteer related functions
 *
 * @author Connor Ferris
 */
@RunWith(AndroidJUnit4.class)
public class VolunteerTests {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule
            = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void login() throws InterruptedException {
        onView(withId(R.id.username)).perform(typeText("test"));
        onView(withId(R.id.password)).perform(typeText("test"));
        Espresso.closeSoftKeyboard();
        TimeUnit.SECONDS.sleep(1);
        onView(withId(R.id.loginButton)).perform(click());
    }

    @Test
    public void clickThru() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        //Home Frag
        onView(withId(R.id.settingsVolBtn)).perform(click());
        onView(withId(R.id.backBtn)).perform(click());

        onView(withId(R.id.picVolBtn)).perform(click());
        onView(withId(R.id.dermPic)).perform(click());
        onView(withId(R.id.ferrisPic)).perform(click());
        onView(withId(R.id.mitraPic)).perform(click());
        onView(withId(R.id.jaguarPic)).perform(click());
        onView(withId(R.id.defaultPic)).perform(click());
        onView(withId(R.id.dogPic)).perform(click());
        onView(withId(R.id.spongebobPic)).perform(click());
        onView(withId(R.id.peterPic)).perform(click());
        onView(withId(R.id.rigbyPic)).perform(click());
        onView(withId(R.id.backPicBtn));

        //Posts Frag
        onView(withId(R.id.postsBtn)).perform(click());
        onView(withId(R.id.refresh)).perform(click());

        //Search Frag
        onView(withId(R.id.searchBtn)).perform(click());
        onView(withId(R.id.SearchBtn)).perform(click());

        //Dm Frag
        onView(withId(R.id.dmBtn)).perform(click());
        onView(withId(R.id.homeBtn)).perform(click());

        onView(withId(R.id.homeBtn)).check(matches(isDisplayed()));
    }

}
