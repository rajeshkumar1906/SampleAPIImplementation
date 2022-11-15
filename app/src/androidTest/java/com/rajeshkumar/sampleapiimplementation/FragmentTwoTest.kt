package com.rajeshkumar.sampleapiimplementation

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers
import com.google.common.base.Verify.verify
import com.google.common.truth.Truth
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class FragmentTwoTest{

    @Test
    fun button_click_moving_to_next_screen(){
        GlobalScope.launch {
            val navigationController = TestNavHostController(
                ApplicationProvider.getApplicationContext()
            ).apply {
                setGraph(R.navigation.navigation)
                setCurrentDestination(R.id.fragmentTwo)
            }

            val initialFragment = launchFragmentInContainer<FragmentTwo>()
            initialFragment.onFragment{ fragment->
                Navigation.setViewNavController(fragment.requireView(),navigationController)
            }

            Espresso.onView(ViewMatchers.withId(R.id.button)).perform(ViewActions.click())

//          val backStack = navigationController.backStack
//
//           val currentView =backStack.last()

            val nextFragment = navigationController.currentDestination
            val currentDestination = nextFragment?.id
            Truth.assertThat(currentDestination).isEqualTo(R.id.thired_fragment)
        }

    }

}