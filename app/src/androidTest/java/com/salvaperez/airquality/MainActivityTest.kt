package com.salvaperez.airquality

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.salvaperez.airquality.presentation.main.MainActivity
import org.junit.Rule
import org.junit.Test


class MainActivityTest {

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java, false, false)

    @Test
    fun clickAirQualityAndNavigateToDetail() {
        activityTestRule.launchActivity(null)

        IdlingRegistry.getInstance().register(activityTestRule.activity.idlingResource);

        Espresso.onView(withId(R.id.rvAirQuality)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                2,
                ViewActions.click()
            )
        )
    }
}