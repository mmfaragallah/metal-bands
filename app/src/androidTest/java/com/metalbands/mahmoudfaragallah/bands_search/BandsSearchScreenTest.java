package com.metalbands.mahmoudfaragallah.bands_search;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.metalbands.mahmoudfaragallah.R;
import com.metalbands.mahmoudfaragallah.RecyclerViewItemCountAssertion;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;

/**
 * Created by Mahmoud on 15-12-2017.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class BandsSearchScreenTest {

    @Rule
    public ActivityTestRule<BandsSearchScreen> bandsSearchScreenTestRule =
            new ActivityTestRule<BandsSearchScreen>(BandsSearchScreen.class);

    @Test
    public void searchScreen_loads() {
        Espresso
                .onView(ViewMatchers.withId(R.id.bands_list))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

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

}