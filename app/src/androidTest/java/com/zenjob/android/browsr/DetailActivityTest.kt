package com.zenjob.android.browsr

import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.zenjob.android.browsr.data.Movie
import com.zenjob.android.browsr.ui.detail.DetailActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class DetailActivityTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var activityRule: ActivityScenarioRule<DetailActivity> =
        ActivityScenarioRule(DetailActivity::class.java)

    private lateinit var detailActivity: DetailActivity

    @Before
    fun init() {
        activityRule.scenario.onActivity {
            detailActivity = it
        }.moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun movieTitle_emptyInitially() {
        Espresso.onView(withId(R.id.tvTitle))
            .check(
                ViewAssertions.matches(
                    Matchers.allOf(
                        ViewMatchers.isDisplayed(),
                        withText("test")
                    )
                )
            )
    }

    @Test
    fun movieTitle_settingCorrect() {

        val mockMovie = Movie.mock()
        activityRule.scenario.onActivity {
            it.showMovieDetails(mockMovie)
        }

        Espresso.onView(withId(R.id.tvTitle))
            .check(
                ViewAssertions.matches(
                    Matchers.allOf(
                        ViewMatchers.isDisplayed(),
                        withText(mockMovie.title)
                    )
                )
            )
    }

}