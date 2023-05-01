package com.example.servington_from_ground_up;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

/**
 * Instrumented tests for MainActivity
 *
 * @author Connor Ferris
 */
@RunWith(AndroidJUnit4.class)
public class MainTests {

    @Rule public ActivityScenarioRule<MainActivity> activityScenarioRule
            = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void openApp() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.servington_from_ground_up", appContext.getPackageName());
    }

    @Test
    public void testMainFields() {
        onView(withId(R.id.username)).perform(typeText("Bob"));
        onView(withId(R.id.password)).perform(typeText("password"));

        onView(withText("password")).check(matches(isDisplayed()));
        onView(withText("Bob")).check(matches(isDisplayed()));
    }

    @Test
    public void testSpinner() throws InterruptedException {
        //Test initially being set to "Volunteer"
        onView(withId(R.id.spinner2)).check(matches(withSpinnerText(containsString("Volunteer"))));

        //Test "Volunteer"
        onView(withId(R.id.spinner2)).perform(click());
        TimeUnit.SECONDS.sleep(1);
        onData(anything()).atPosition(0).perform(click());
        onView(withId(R.id.spinner2)).check(matches(withSpinnerText(containsString("Volunteer"))));

        //Test "Organization"
        onView(withId(R.id.spinner2)).perform(click());
        TimeUnit.SECONDS.sleep(1);
        onData(anything()).atPosition(1).perform(click());
        onView(withId(R.id.spinner2)).check(matches(withSpinnerText(containsString("Organization"))));

        //Test "Admin"
        onView(withId(R.id.spinner2)).perform(click());
        TimeUnit.SECONDS.sleep(1);
        onData(anything()).atPosition(2).perform(click());
        onView(withId(R.id.spinner2)).check(matches(withSpinnerText(containsString("Admin"))));

    }

    @Test
    public void testLogin() throws InterruptedException {
        onView(withId(R.id.username)).perform(typeText("test"));
        onView(withId(R.id.password)).perform(typeText("test"));
        Espresso.closeSoftKeyboard();
        TimeUnit.SECONDS.sleep(1);
        onView(withId(R.id.loginButton)).perform(click());
        TimeUnit.SECONDS.sleep(4);
        onView(withId(R.id.welcomeVolText)).check(matches(isDisplayed()));
    }


}
