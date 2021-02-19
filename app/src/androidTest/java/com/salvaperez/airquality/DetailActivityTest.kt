package com.salvaperez.airquality

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.rule.ActivityTestRule
import com.salvaperez.airquality.presentation.detail.DetailActivity
import com.salvaperez.airquality.utils.CustomMatchers.Companion.withItemCount
import com.salvaperez.airquality.utils.mockedAirQuality
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.core.AllOf
import org.hamcrest.core.Is.`is`

class DetailActivityTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule<DetailActivity>(DetailActivity::class.java, true, false)

    @Before
    @Throws(Exception::class)
    fun setUp() {
        val intent = Intent()
        intent.putExtra(DetailActivity.COMPONENTS_DATA, mockedAirQuality)
        activityRule.launchActivity(intent)
    }

    @Test
    fun check_show_eight_air_quality_components() {
        onView(withId(R.id.rvComponents))
            .check(matches(withItemCount(8)))
    }

    @Test
    fun check_elements_are_displayed_and_show_correct_name(){
        onView(withId(R.id.rvComponents)).check(matches(isDisplayed()))
        onView(AllOf.allOf(isDescendantOfA(withId(R.id.rvComponents)),
                withText(`is`("CO (Carbon monoxide)"))
            )
        )
    }
}