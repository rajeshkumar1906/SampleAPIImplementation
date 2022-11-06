package com.rajeshkumar.sampleapiimplementation

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import com.rajeshkumar.sampleapiimplementation.adapter.CustomListAdapter
import com.rajeshkumar.sampleapiimplementation.model.Root
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule


@RunWith(JUnit4::class)
class AdapterTesting {

    lateinit var context: Context


    @Before
    fun setup(){
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun recyclerViewAdapterTesting(){
        val alRoot = ArrayList<Root>()
        val root = Root().apply {
            this.id = 101
            this.name = "Rajesh kumar"
            this.email = "rajeshk.chelluri@gmail.com"
        }
        alRoot.add(root)

        val customListAdapter = CustomListAdapter(alRoot)
        val recyclerView = RecyclerView(context)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val viewHolder = customListAdapter.onCreateViewHolder(recyclerView,0)
        customListAdapter.onBindViewHolder(viewHolder,0)
        assert(viewHolder.bind(root).equals(alRoot))
    }

}