package com.metalbands.mahmoudfaragallah.bands_search;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.metalbands.mahmoudfaragallah.R;
import com.metalbands.mahmoudfaragallah.RecyclerViewItemCountAssertion;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.greaterThan;

/**
 * Created by Mahmoud on 15-12-2017.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class BandsSearchScreenTest {

    private IdlingResource idlingResource;

    @Rule
    public ActivityTestRule<BandsSearchScreen> bandsSearchScreenTestRule =
            new ActivityTestRule<BandsSearchScreen>(BandsSearchScreen.class);

    @Test
    public void searchScreen_loads() {
        Espresso
                .onView(ViewMatchers.withId(R.id.bands_list))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }

    @Before
    public void registerIdlingResource() {

        idlingResource = bandsSearchScreenTestRule.getActivity().getIdlingResource();
        // register activity idling resource
        IdlingRegistry
                .getInstance()
                .register(bandsSearchScreenTestRule.getActivity().getIdlingResource());
    }


    @After
    public void unregisterIdlingResource() {
        if (idlingResource != null) {
            IdlingRegistry
                    .getInstance()
                    .unregister(idlingResource);
        }
    }


    @Test
    public void empty_results_test() {

        Espresso
                .onView(ViewMatchers.withId(R.id.search_view))
                .perform(click());

        Espresso
                .onView(ViewMatchers.withId(R.id.search_src_text))
                .perform(typeText("nobands"), closeSoftKeyboard());

        // checking recycle view children count without using IdlingResource
        Espresso
                .onView(ViewMatchers.withId(R.id.bands_list))
                .check(new RecyclerViewItemCountAssertion(0));

    }

    @Test
    public void search_for_specific_band_test() {

        Espresso
                .onView(ViewMatchers.withId(R.id.search_view))
                .perform(click());

        Espresso
                .onView(ViewMatchers.withId(R.id.search_src_text))
                .perform(typeText("trinity test"), closeSoftKeyboard());

        // checking recycle view children count without using IdlingResource
        Espresso
                .onView(ViewMatchers.withId(R.id.bands_list))
                .check(new RecyclerViewItemCountAssertion(greaterThan(0)));

        Espresso.
                onView(withText("Trinity Test")).check(matches(isDisplayed()));

        Espresso.
                onView(withText("United States")).check(matches(isDisplayed()));

        Espresso.
                onView(withText("Progressive/Thrash/Death Metal")).check(matches(isDisplayed()));

    }
}