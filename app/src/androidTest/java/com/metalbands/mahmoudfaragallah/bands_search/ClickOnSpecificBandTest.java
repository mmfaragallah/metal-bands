package com.metalbands.mahmoudfaragallah.bands_search;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import com.metalbands.mahmoudfaragallah.R;
import com.metalbands.mahmoudfaragallah.RecyclerViewItemCountAssertion;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.greaterThan;

/**
 * Created by Mahmoud on 15-12-2017.
 */

public class ClickOnSpecificBandTest {

    private IdlingResource idlingResource;

    @Rule
    public ActivityTestRule<BandsSearchScreen> bandsSearchScreenTestRule =
            new ActivityTestRule<BandsSearchScreen>(BandsSearchScreen.class);

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
    public void click_on_specific_band_test() {

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
                onView(ViewMatchers.withId(R.id.bands_list))
                .perform(
                        RecyclerViewActions.actionOnItem(
                                hasDescendant(withText("Progressive/Thrash/Death Metal")), click()));

    }
}
