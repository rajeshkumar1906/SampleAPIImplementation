package com.rajeshkumar.sampleapiimplementation




import android.app.Activity
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.UiThreadTestRule
import com.rajeshkumar.sampleapiimplementation.adapter.CustomListAdapter
import com.rajeshkumar.sampleapiimplementation.model.Root
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class AdapterTesting {

    lateinit var mContext: Context
    @get:Rule
    public val uiThreadTestRule = UiThreadTestRule()



    @get:Rule
    public var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule<MainActivity>(MainActivity::class.java)
    lateinit var viewHolder: RecyclerView.ViewHolder

    @Before
    fun setup(){
        mContext = activityRule.activity
    }

    @Test
    fun recyclerViewVisibilityTest(){
//        uiThreadTestRule.runOnUiThread(Runnable {
            val alRoot = ArrayList<Root>()
            val root = Root().apply {
                this.id = 101
                this.name = "Rajesh kumar"
                this.email = "rajeshk.chelluri@gmail.com"
            }
            alRoot.add(root)
            val customListAdapter = CustomListAdapter(alRoot)
//            var recyclerView : RecyclerView? = null
////            val scenario = ActivityScenario.launch(MainActivity::class.java)
////            scenario.onActivity {
////            recyclerView = RecyclerView(mContext)
//            recyclerView.layoutManager = LinearLayoutManager(mContext)
//            }
//            val viewHolder = customListAdapter.onCreateViewHolder(recyclerView!!,0)
//            customListAdapter.onBindViewHolder(viewHolder,0)
        val recyclerView = mock(RecyclerView::class.java)
        val viewHolder = customListAdapter.onCreateViewHolder(recyclerView!!,0)
            Espresso.onView(withId(R.id.recycler_view))
                .inRoot(
                    RootMatchers.withDecorView(
                        Matchers.`is`(
                            (mContext as Activity).getWindow().getDecorView()
                        )
                    )
                )
                .check(matches(isDisplayed()))
    }

    @Test
    fun testCaseForRecyclerClick() {
        Espresso.onView(withId(R.id.recycler_view))
            .inRoot(
                RootMatchers.withDecorView(
                    Matchers.`is`((mContext as Activity).getWindow().getDecorView())
                )
            )
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder >(0, click()))
    }

    @Test
    fun testCaseForRecyclerScroll() {
        // Get total item of RecyclerView
        val recyclerView: RecyclerView =
            (mContext as Activity).findViewById(R.id.recycler_view)
        val itemCount = 10

        // Scroll to end of page with position
        Espresso.onView(withId(R.id.recycler_view))
            .inRoot(
                RootMatchers.withDecorView(
                    Matchers.`is`((mContext as Activity).getWindow().getDecorView())
                )
            )
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(itemCount - 1))
    }

}