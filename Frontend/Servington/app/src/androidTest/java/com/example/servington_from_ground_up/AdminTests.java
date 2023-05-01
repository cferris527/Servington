package com.example.servington_from_ground_up;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.anything;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

@RunWith(AndroidJUnit4.class)
public class AdminTests {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule
            = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void initialize() throws InterruptedException {
        onView(withId(R.id.username)).perform(typeText("Admin1"));
        onView(withId(R.id.password)).perform(typeText("password"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.spinner2)).perform(click());
        TimeUnit.SECONDS.sleep(1);
        onData(anything()).atPosition(2).perform(click());

        TimeUnit.SECONDS.sleep(1);
        onView(withId(R.id.loginButton)).perform(click());
    }

    @Test
    public void moveThru() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        onView(withId(R.id.adminsBtn)).perform(click());

        //Admin page
        onView(withId(R.id.username3)).perform(typeText("Demo"));
        onView(withId(R.id.password3)).perform(typeText("Demo"));
        Espresso.closeSoftKeyboard();
        TimeUnit.SECONDS.sleep(1);
        onView(withId(R.id.createAdmin2)).perform(click());
        onView(withId(R.id.refreshAdmins)).perform(click());
        onView(withId(R.id.reportsBtn)).perform(click());

        //Reports Page
        TimeUnit.SECONDS.sleep(2);
        onView(withId(R.id.banBtn)).perform(click());
        onView(withId(R.id.banUserBtn)).perform(click());
        onView(withId(R.id.banName)).perform(typeText("test"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.bannedBtn)).perform(click());

        onView(withId(R.id.unbanName)).perform(typeText("test"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.unbannedBtn)).perform(click());

        onView(withId(R.id.backBtn2)).perform(click());







    }

    @Test
    public void logout() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        onView(withId(R.id.logoutBtn)).perform(click());
    }



}
