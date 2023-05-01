package com.example.servington_from_ground_up;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.containsString;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

/**
 * Instrumented tests for Account Create related functions
 *
 * @author Connor Ferris
 */
@RunWith(AndroidJUnit4.class)
public class CreateAccountTest {

    @Rule
    public ActivityScenarioRule<CreateAccountActivity> activityScenarioRule
            = new ActivityScenarioRule<>(CreateAccountActivity.class);

    @Test
    public void createOrg() throws InterruptedException {
        onView(withId(R.id.newUsername)).perform(typeText("Long haired bozo"));
        onView(withId(R.id.newPassword)).perform(typeText("AH AH AH"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.spinner)).perform(click());
        TimeUnit.SECONDS.sleep(1);
        onData(anything()).atPosition(1).perform(click());


        TimeUnit.SECONDS.sleep(1);
        onView(withId(R.id.confirmButton)).perform(click());

        onView(withId(R.id.spinner2)).check(matches(withSpinnerText(containsString("Volunteer"))));
    }

}
