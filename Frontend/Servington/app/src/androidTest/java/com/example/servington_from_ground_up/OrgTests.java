package com.example.servington_from_ground_up;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class OrgTests {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void orgTests() {
        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.spinner2),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatSpinner.perform(click());

        DataInteraction appCompatCheckedTextView = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(1);
        appCompatCheckedTextView.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.username),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("Orgy"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.password),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("Orgy"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.loginButton), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        materialButton.perform(click());

//        ViewInteraction textView = onView(
//                allOf(withId(R.id.idVolText), withText("ID: 5"),
//                        withParent(withParent(withId(R.id.fragmentContainerView))),
//                        isDisplayed()));
//        textView.check(matches(withText("ID: 5")));

//       

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.homeBtn), withText("Home"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                0),
                        isDisplayed()));
        materialButton2.perform(click());

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.welcomeOrgText), withText("Welcome, Orgy!"),
                        withParent(withParent(withId(R.id.fragmentContainerView))),
                        isDisplayed()));
        textView5.check(matches(withText("Welcome, Orgy!")));

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.idOrgText), withText("ID: 5"),
                        withParent(withParent(withId(R.id.fragmentContainerView))),
                        isDisplayed()));
        textView6.check(matches(withText("ID: 5")));

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.emailOrgText), withText("email: (null)"),
                        withParent(withParent(withId(R.id.fragmentContainerView))),
                        isDisplayed()));
        textView7.check(matches(withText("email: (null)")));

        ViewInteraction textView8 = onView(
                allOf(withId(R.id.phoneOrgText), withText("phone: (null)"),
                        withParent(withParent(withId(R.id.fragmentContainerView))),
                        isDisplayed()));
        textView8.check(matches(withText("phone: (null)")));

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.postsBtn), withText("Posts"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                1),
                        isDisplayed()));
        materialButton3.perform(click());

        ViewInteraction button = onView(
                allOf(withId(R.id.homeBtn), withText("HOME"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction button2 = onView(
                allOf(withId(R.id.OrgViewPosts), withText("VIEW YOUR POSTS"),
                        withParent(withParent(withId(R.id.fragmentContainerView))),
                        isDisplayed()));
        button2.check(matches(isDisplayed()));

        ViewInteraction button3 = onView(
                allOf(withId(R.id.createposts), withText("CREATE A POST"),
                        withParent(withParent(withId(R.id.fragmentContainerView))),
                        isDisplayed()));
        button3.check(matches(isDisplayed()));

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.createposts), withText("Create a post"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragmentContainerView),
                                        0),
                                0),
                        isDisplayed()));
        materialButton4.perform(click());

        ViewInteraction editText = onView(
                allOf(withId(R.id.create_posts_title), withText("Title"),
                        withParent(withParent(withId(R.id.fragmentContainerView))),
                        isDisplayed()));
        editText.check(matches(withText("Title")));

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.create_post_date), withText("date"),
                        withParent(withParent(withId(R.id.fragmentContainerView))),
                        isDisplayed()));
        editText2.check(matches(withText("date")));

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.create_post_description), withText("description"),
                        withParent(withParent(withId(R.id.fragmentContainerView))),
                        isDisplayed()));
        editText3.check(matches(withText("description")));

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.create_posts_title), withText("Title"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragmentContainerView),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("test post"));

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.create_posts_title), withText("test post"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragmentContainerView),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText4.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.create_post_date), withText("date"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragmentContainerView),
                                        0),
                                4),
                        isDisplayed()));
        appCompatEditText5.perform(replaceText("test date"));

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.create_post_date), withText("test date"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragmentContainerView),
                                        0),
                                4),
                        isDisplayed()));
        appCompatEditText6.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.create_post_description), withText("description"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragmentContainerView),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText7.perform(replaceText("test description"));

        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.create_post_description), withText("test description"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragmentContainerView),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText8.perform(closeSoftKeyboard());

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.button_create_post), withText("Create Post"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragmentContainerView),
                                        0),
                                3),
                        isDisplayed()));
        materialButton5.perform(click());

        ViewInteraction editText4 = onView(
                allOf(withId(R.id.create_posts_title), withText("test post"),
                        withParent(withParent(withId(R.id.fragmentContainerView))),
                        isDisplayed()));
        editText4.check(matches(withText("test post")));

        ViewInteraction editText5 = onView(
                allOf(withId(R.id.create_post_date), withText("test date"),
                        withParent(withParent(withId(R.id.fragmentContainerView))),
                        isDisplayed()));
        editText5.check(matches(withText("test date")));

        ViewInteraction editText6 = onView(
                allOf(withId(R.id.create_post_description), withText("test description"),
                        withParent(withParent(withId(R.id.fragmentContainerView))),
                        isDisplayed()));
        editText6.check(matches(withText("test description")));

        ViewInteraction materialButton6 = onView(
                allOf(withId(R.id.postsBtn), withText("Posts"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                1),
                        isDisplayed()));
        materialButton6.perform(click());

        ViewInteraction materialButton7 = onView(
                allOf(withId(R.id.OrgViewPosts), withText("View your posts"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragmentContainerView),
                                        0),
                                1),
                        isDisplayed()));
        materialButton7.perform(click());

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.orgRecycler),
                        withParent(withParent(withId(R.id.fragmentContainerView))),
                        isDisplayed()));
        recyclerView.check(matches(isDisplayed()));

        ViewInteraction textView9 = onView(
                allOf(withId(R.id.postcardtitle), withText("test post"),
                        withParent(withParent(withId(R.id.orgRecycler))),
                        isDisplayed()));
        textView9.check(matches(withText("test post")));

        ViewInteraction textView10 = onView(
                allOf(withId(R.id.postdesc), withText("test description"),
                        withParent(withParent(withId(R.id.orgRecycler))),
                        isDisplayed()));
        textView10.check(matches(withText("test description")));

        ViewInteraction textView11 = onView(
                allOf(withId(R.id.postcarddate), withText("test date"),
                        withParent(withParent(withId(R.id.orgRecycler))),
                        isDisplayed()));
        textView11.check(matches(withText("test date")));

        ViewInteraction materialButton8 = onView(
                allOf(withId(R.id.dmBtn), withText("DM"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                2),
                        isDisplayed()));
        materialButton8.perform(click());

        ViewInteraction materialButton9 = onView(
                allOf(withId(R.id.homeBtn), withText("Home"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                0),
                        isDisplayed()));
        materialButton9.perform(click());

        ViewInteraction materialButton10 = onView(
                allOf(withId(R.id.logoutOrgBtn), withText("Logout"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout2),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                5)),
                                1),
                        isDisplayed()));
        materialButton10.perform(click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
