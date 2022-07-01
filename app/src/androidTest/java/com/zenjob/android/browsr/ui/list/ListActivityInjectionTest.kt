package com.zenjob.android.browsr.ui.list

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class ListActivityInjectionTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Test
    fun verifyInjection() {
        ActivityScenario.launch(ListActivity::class.java).use {
            it.moveToState(Lifecycle.State.CREATED)
            it.onActivity { activity ->
                assertThat(activity.listViewModel).isNotNull()
                assertThat(activity.listViewModel.isLoading).isNotNull()
            }
        }
    }

    @Test
    fun verifyListLoading() {
        ActivityScenario.launch(ListActivity::class.java).use {
            it.moveToState(Lifecycle.State.RESUMED)
            it.onActivity { activity ->
                assertThat(activity.listViewModel).isNotNull()

                runBlocking {
                    activity.listViewModel.popularTvShowsList.collect { list ->
                        assert(list.isNotEmpty())
                    }
                }

            }
        }
    }


}