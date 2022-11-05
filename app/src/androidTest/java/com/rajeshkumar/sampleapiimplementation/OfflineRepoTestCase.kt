package com.rajeshkumar.sampleapiimplementation

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.rajeshkumar.sampleapiimplementation.offline.AppDataBase
import com.rajeshkumar.sampleapiimplementation.offline.DataDAO
import com.rajeshkumar.sampleapiimplementation.offline.DataEntity
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OfflineRepoTestCase: TestCase() {
   private lateinit var database: AppDataBase
   private lateinit var dao: DataDAO


   @Before
   public fun setup(){
       val context = ApplicationProvider.getApplicationContext<Context>()
       database = Room.inMemoryDatabaseBuilder(context,AppDataBase::class.java).build()
       dao = database.todoData()
   }

    @After
    public fun close(){
        database.close()
    }

    @Test
    public fun writeDatabase() = runBlocking {
        val data = DataEntity(1,"Rajesh kumar","rajeshk.chelluri@gamil.com")
        dao.insertData(data)
        val getAllData = dao.getAllData()
//        assertThat(getAllData.contains(data)).isTrue()
    }
}