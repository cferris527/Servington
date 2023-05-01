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
public class MainTest2 {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void mainTest2() {
        ViewInteraction editText = onView(
                allOf(withId(R.id.username), withText("Username"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        //editText.check(matches(withText("Username")));

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.password), withText("Password"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        //editText2.check(matches(withText("Password")));

        ViewInteraction spinner = onView(
                allOf(withId(R.id.spinner2),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        spinner.check(matches(isDisplayed()));

        ViewInteraction button = onView(
                allOf(withId(R.id.loginButton), withText("LOGIN"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction button2 = onView(
                allOf(withId(R.id.createButton), withText("SIGN UP"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        button2.check(matches(isDisplayed()));

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.username),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.username),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("test"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.password),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("test"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.loginButton), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.welcomeVolText), withText("Welcome, test!"),
                        withParent(withParent(withId(R.id.fragmentContainerView))),
                        isDisplayed()));
        textView.check(matches(withText("Welcome, test!")));

//        ViewInteraction textView2 = onView(
//                allOf(withId(R.id.idVolText), withText("ID: 1"),
//                        withParent(withParent(withId(R.id.fragmentContainerView))),
//                        isDisplayed()));
//        textView2.check(matches(withText("ID: 1")));
//
//        ViewInteraction textView3 = onView(
//                allOf(withId(R.id.emailVolText), withText("test@test.net"),
//                        withParent(withParent(withId(R.id.fragmentContainerView))),
//                        isDisplayed()));
//        textView3.check(matches(withText("test@test.net")));
//
//        ViewInteraction textView4 = onView(
//                allOf(withId(R.id.phoneVolText), withText("7174904461"),
//                        withParent(withParent(withId(R.id.fragmentContainerView))),
//                        isDisplayed()));
//        textView4.check(matches(withText("7174904461")));
//
//        ViewInteraction textView5 = onView(
//                allOf(withId(R.id.DNvolText), withText("Test boy"),
//                        withParent(withParent(withId(R.id.fragmentContainerView))),
//                        isDisplayed()));
//        textView5.check(matches(withText("Test boy")));

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.settingsVolBtn), withText("Settings"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout2),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                1)),
                                0),
                        isDisplayed()));
        materialButton2.perform(click());

        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.spinna),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragmentContainerView),
                                        0),
                                2),
                        isDisplayed()));
        //appCompatSpinner.perform(click());

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.oldField), withText("Old Password"),
                        withParent(withParent(withId(R.id.fragmentContainerView))),
                        isDisplayed()));
        //editText3.check(matches(isDisplayed()));

        ViewInteraction editText4 = onView(
                allOf(withId(R.id.newField), withText("New Password"),
                        withParent(withParent(withId(R.id.fragmentContainerView))),
                        isDisplayed()));
        //editText4.check(matches(isDisplayed()));

        ViewInteraction button3 = onView(
                allOf(withId(R.id.changeBtn), withText("CHANGE"),
                        withParent(withParent(withId(R.id.fragmentContainerView))),
                        isDisplayed()));
        button3.check(matches(isDisplayed()));

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.backBtn), withText("Back"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragmentContainerView),
                                        0),
                                5),
                        isDisplayed()));
        materialButton3.perform(click());

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.picVolBtn), withText("Picture"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout2),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                1)),
                                1),
                        isDisplayed()));
        materialButton4.perform(click());

        ViewInteraction imageButton = onView(
                allOf(withId(R.id.dermPic),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        imageButton.check(matches(isDisplayed()));

        ViewInteraction imageButton2 = onView(
                allOf(withId(R.id.ferrisPic),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        imageButton2.check(matches(isDisplayed()));

        ViewInteraction imageButton3 = onView(
                allOf(withId(R.id.mitraPic),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        imageButton3.check(matches(isDisplayed()));

        ViewInteraction imageButton4 = onView(
                allOf(withId(R.id.jaguarPic),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        imageButton4.check(matches(isDisplayed()));

        ViewInteraction imageButton5 = onView(
                allOf(withId(R.id.rigbyPic),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        imageButton5.check(matches(isDisplayed()));

        ViewInteraction imageButton6 = onView(
                allOf(withId(R.id.defaultPic),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        imageButton6.check(matches(isDisplayed()));

        ViewInteraction imageButton7 = onView(
                allOf(withId(R.id.dogPic),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        imageButton7.check(matches(isDisplayed()));

        ViewInteraction imageButton8 = onView(
                allOf(withId(R.id.spongebobPic),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        imageButton8.check(matches(isDisplayed()));

        ViewInteraction imageButton9 = onView(
                allOf(withId(R.id.peterPic),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        imageButton9.check(matches(isDisplayed()));

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.backPicBtn), withText("Back"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout3),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                1)),
                                0),
                        isDisplayed()));
        materialButton5.perform(click());

        ViewInteraction materialButton6 = onView(
                allOf(withId(R.id.postsBtn), withText("Posts"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                1),
                        isDisplayed()));
        materialButton6.perform(click());

        ViewInteraction editText5 = onView(
                allOf(withId(R.id.postinput), withText("Enter Title"),
                        withParent(withParent(withId(R.id.fragmentContainerView))),
                        isDisplayed()));
        editText5.check(matches(withText("Enter Title")));

        ViewInteraction spinner2 = onView(
                allOf(withId(R.id.postselect),
                        withParent(withParent(withId(R.id.fragmentContainerView))),
                        isDisplayed()));
        spinner2.check(matches(isDisplayed()));

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.postcardtitle), withText("test"),
                        withParent(withParent(withId(R.id.recyclerView))),
                        isDisplayed()));
        textView6.check(matches(withText("test")));

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.postdesc), withText("null"),
                        withParent(withParent(withId(R.id.recyclerView))),
                        isDisplayed()));
        //textView7.check(matches(withText("null")));

        ViewInteraction textView8 = onView(
                allOf(withId(R.id.postcarddate), withText("null"),
                        withParent(withParent(withId(R.id.recyclerView))),
                        isDisplayed()));
        textView8.check(matches(withText("null")));

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.recyclerView),
                        withParent(withParent(withId(R.id.fragmentContainerView))),
                        isDisplayed()));
        recyclerView.check(matches(isDisplayed()));

        ViewInteraction appCompatSpinner2 = onView(
                allOf(withId(R.id.postselect),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragmentContainerView),
                                        0),
                                2),
                        isDisplayed()));
        appCompatSpinner2.perform(click());

        DataInteraction checkedTextView = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(1);
        checkedTextView.perform(click());

        ViewInteraction materialButton7 = onView(
                allOf(withId(R.id.VolSelect), withText("Button"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragmentContainerView),
                                        0),
                                3),
                        isDisplayed()));
        materialButton7.perform(click());

        ViewInteraction textView9 = onView(
                allOf(withId(R.id.ReportTitle), withText("Please enter the title of the post"),
                        withParent(withParent(withId(R.id.fragmentContainerView))),
                        isDisplayed()));
        textView9.check(matches(withText("Please enter the title of the post")));

        ViewInteraction editText6 = onView(
                allOf(withId(R.id.reportTitleInput), withText("Post Title"),
                        withParent(withParent(withId(R.id.fragmentContainerView))),
                        isDisplayed()));
        editText6.check(matches(withText("Post Title")));

        ViewInteraction editText7 = onView(
                allOf(withId(R.id.reportDescInput),
                        withParent(withParent(withId(R.id.fragmentContainerView))),
                        isDisplayed()));
        editText7.check(matches(isDisplayed()));

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.reportTitleInput), withText("Post Title"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragmentContainerView),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText4.perform(replaceText("test report"));

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.reportTitleInput), withText("test report"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragmentContainerView),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText5.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.reportDescInput),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragmentContainerView),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText6.perform(replaceText("test report description"), closeSoftKeyboard());

        ViewInteraction editText8 = onView(
                allOf(withId(R.id.reportTitleInput), withText("test report"),
                        withParent(withParent(withId(R.id.fragmentContainerView))),
                        isDisplayed()));
        editText8.check(matches(withText("test report")));

        ViewInteraction editText9 = onView(
                allOf(withId(R.id.reportDescInput), withText("test report description"),
                        withParent(withParent(withId(R.id.fragmentContainerView))),
                        isDisplayed()));
        editText9.check(matches(withText("test report description")));

        ViewInteraction materialButton8 = onView(
                allOf(withId(R.id.postsBtn), withText("Posts"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                1),
                        isDisplayed()));
        materialButton8.perform(click());

        ViewInteraction materialButton9 = onView(
                allOf(withId(R.id.postsBtn), withText("Posts"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                1),
                        isDisplayed()));
        materialButton9.perform(click());

        ViewInteraction materialButton10 = onView(
                allOf(withId(R.id.searchBtn), withText("Search"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                2),
                        isDisplayed()));
        materialButton10.perform(click());

        ViewInteraction editText10 = onView(
                allOf(withId(R.id.keywordsearch), withText("Keyword"),
                        withParent(withParent(withId(R.id.fragmentContainerView))),
                        isDisplayed()));
        editText10.check(matches(withText("Keyword")));

        ViewInteraction button4 = onView(
                allOf(withId(R.id.SearchBtn), withText("SEARCH"),
                        withParent(withParent(withId(R.id.fragmentContainerView))),
                        isDisplayed()));
        button4.check(matches(isDisplayed()));

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.keywordsearch), withText("Keyword"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragmentContainerView),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText7.perform(click());

        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.keywordsearch), withText("Keyword"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragmentContainerView),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText8.perform(click());

        ViewInteraction appCompatEditText9 = onView(
                allOf(withId(R.id.keywordsearch), withText("Keyword"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragmentContainerView),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText9.perform(replaceText("test"));

        ViewInteraction appCompatEditText10 = onView(
                allOf(withId(R.id.keywordsearch), withText("test"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragmentContainerView),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText10.perform(closeSoftKeyboard());

        ViewInteraction materialButton11 = onView(
                allOf(withId(R.id.SearchBtn), withText("Search"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragmentContainerView),
                                        0),
                                2),
                        isDisplayed()));
        materialButton11.perform(click());

        ViewInteraction linearLayout = onView(
                allOf(withParent(allOf(withId(R.id.SearchRecy),
                                withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                        isDisplayed()));
        //linearLayout.check(matches(isDisplayed()));

        ViewInteraction textView10 = onView(
                allOf(withId(R.id.postcardtitle), withText("test"),
                        withParent(withParent(withId(R.id.SearchRecy))),
                        isDisplayed()));
        textView10.check(matches(withText("test")));

        ViewInteraction textView11 = onView(
                allOf(withId(R.id.postdesc), withText("null"),
                        withParent(withParent(withId(R.id.SearchRecy))),
                        isDisplayed()));
        //textView11.check(matches(withText("null")));

        ViewInteraction textView12 = onView(
                allOf(withId(R.id.postcarddate), withText("null"),
                        withParent(withParent(withId(R.id.SearchRecy))),
                        isDisplayed()));
        textView12.check(matches(withText("null")));

        ViewInteraction linearLayout2 = onView(
                allOf(withParent(allOf(withId(R.id.SearchRecy),
                                withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                        isDisplayed()));
        //linearLayout2.check(matches(isDisplayed()));

        ViewInteraction materialButton12 = onView(
                allOf(withId(R.id.dmBtn), withText("DM"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                3),
                        isDisplayed()));
        materialButton12.perform(click());

        ViewInteraction button5 = onView(
                allOf(withId(R.id.messageBtn), withText("ENTER DM"),
                        withParent(withParent(withId(R.id.fragmentContainerView))),
                        isDisplayed()));
        button5.check(matches(isDisplayed()));

        ViewInteraction materialButton13 = onView(
                allOf(withId(R.id.messageBtn), withText("Enter DM"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragmentContainerView),
                                        0),
                                0),
                        isDisplayed()));
        materialButton13.perform(click());

        ViewInteraction editText11 = onView(
                allOf(withId(R.id.messageTxt), withText("Message"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class))),
                        isDisplayed()));
        //editText11.check(matches(withText("Message")));

        ViewInteraction button6 = onView(
                allOf(withId(R.id.sendBtn), withText("SEND"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class))),
                        isDisplayed()));
        //button6.check(matches(isDisplayed()));

        ViewInteraction appCompatEditText11 = onView(
                allOf(withId(R.id.messageTxt),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        1),
                                0),
                        isDisplayed()));
//        appCompatEditText11.perform(replaceText("test message"), closeSoftKeyboard());
//
//        ViewInteraction textView13 = onView(
//                allOf(withText("User:test has Joined the Chat"),
//                        withParent(allOf(withId(R.id.messageLayout),
//                                withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
//                        isDisplayed()));
//        textView13.check(matches(withText("User:test has Joined the Chat")));
//
//        ViewInteraction editText12 = onView(
//                allOf(withId(R.id.messageTxt), withText("test message"),
//                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class))),
//                        isDisplayed()));
//        editText12.check(matches(withText("test message")));

        ViewInteraction materialButton14 = onView(
                allOf(withId(R.id.homeBtn), withText("Home"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                0),
                        isDisplayed()));
        //materialButton14.perform(click());
//
//        ViewInteraction materialButton15 = onView(
//                allOf(withId(R.id.homeBtn), withText("Home"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withClassName(is("android.widget.LinearLayout")),
//                                        1),
//                                0),
//                        isDisplayed()));
//        materialButton15.perform(click());
//
//        ViewInteraction materialButton16 = onView(
//                allOf(withId(R.id.logoutVolBtn), withText("Logout"),
//                        childAtPosition(
//                                allOf(withId(R.id.linearLayout2),
//                                        childAtPosition(
//                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
//                                                1)),
//                                2),
//                        isDisplayed()));
//        materialButton16.perform(click());
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
