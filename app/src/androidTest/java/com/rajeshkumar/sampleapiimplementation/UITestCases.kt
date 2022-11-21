package com.rajeshkumar.sampleapiimplementation




import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test


class UITestCases {

    @get:Rule
   public val activityRule = ActivityTestRule(MainActivity::class.java,true,true)

    @Test
    fun recyclerViewVisible(){
        Espresso.onView(ViewMatchers.withId(R.id.recycler_view))
            .inRoot(
                RootMatchers.withDecorView(
                    Matchers.`is`(activityRule.activity.window.decorView)
                )
            )
            .check(matches(isDisplayed()))
    }

    @Test
    fun recyclerViewScroll(){
        val recyclerView = activityRule.activity.findViewById<RecyclerView>(R.id.recycler_view)
        val itemCount = recyclerView.adapter?.itemCount
        Espresso.onView(ViewMatchers.withId(R.id.recycler_view))
            .inRoot(
                RootMatchers.withDecorView(
                    Matchers.`is`(activityRule.activity.window.decorView)
                )
            )
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(itemCount!! - 1))
    }

    @Test
    fun testCaseForRecyclerItemView() {
        Espresso.onView(ViewMatchers.withId(R.id.recycler_view))
            .inRoot(
                RootMatchers.withDecorView(
                    Matchers.`is`(activityRule.activity.window.decorView)
                )
            )
            .check(
                matches(
                    withViewAtPosition(
                        1, Matchers.allOf(
                            ViewMatchers.withId(R.id.txtName), isDisplayed()
                        )
                    )
                )
            )
    }

    @Test
    fun testItemVerification(){
        Espresso.onView(ViewMatchers.withId(R.id.recycler_view))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2,click()))

        val name = "Mohini Singh"
        Espresso.onView(withText(name)).check(matches(isDisplayed()))
    }

    private fun withViewAtPosition(position: Int, itemMatcher: Matcher<View?>): Matcher<View?>? {
        return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description?) {
                itemMatcher.describeTo(description)
            }

            override fun matchesSafely(recyclerView: RecyclerView): Boolean {
                val viewHolder = recyclerView.findViewHolderForAdapterPosition(position)
                return viewHolder != null && itemMatcher.matches(viewHolder.itemView)
            }
        }
    }
}