package com.rajeshkumar.sampleapiimplementation

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.rajeshkumar.sampleapiimplementation.model.Root
import com.rajeshkumar.sampleapiimplementation.offline.AppDataBase
import com.rajeshkumar.sampleapiimplementation.offline.DataDAO
import com.rajeshkumar.sampleapiimplementation.offline.DataEntity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

//@ExperimentalCoroutinesApi
//@SmallTest
//@HiltAndroidTest
class OfflineRepoTestCase {

//    @get:Rule
//    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


//    @Inject
//    @Named("tested_db")
    lateinit var database: AppDataBase
    private lateinit var dao: DataDAO


    @Before
    fun setup() {
        runBlocking {
            async(Dispatchers.Default) {
                val context = ApplicationProvider.getApplicationContext<Context>()
                database = Room.inMemoryDatabaseBuilder(context, AppDataBase::class.java).build()
//                hiltRule.inject()
                dao = database.todoData()
            }
        }


    }

    @After
    fun close() {
        database.close()
    }

    @Test
    fun writeDatabase() {

        runBlocking {

            async(Dispatchers.Default) {
                val data = DataEntity(1, "Rajesh kumar", "rajeshk.chelluri@gamil.com")
                dao.insertData(data)
                val getAllData = dao.getAllData()
                val root = Root().apply {
                    id = data.id
                    name = data.name
                    email = data.email
                }
                assertThat(root in getAllData)
            }
        }
    }
}