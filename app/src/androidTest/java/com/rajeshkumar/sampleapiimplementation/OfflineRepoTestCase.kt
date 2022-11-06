package com.rajeshkumar.sampleapiimplementation

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.rajeshkumar.sampleapiimplementation.model.Root
import com.rajeshkumar.sampleapiimplementation.offline.AppDataBase
import com.rajeshkumar.sampleapiimplementation.offline.DataDAO
import com.rajeshkumar.sampleapiimplementation.offline.DataEntity
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OfflineRepoTestCase: TestCase() {
   private lateinit var database: AppDataBase
   private lateinit var dao: DataDAO


   @Before
    fun setup(){
       val context = ApplicationProvider.getApplicationContext<Context>()
       database = Room.inMemoryDatabaseBuilder(context,AppDataBase::class.java).build()
       dao = database.todoData()
   }

    @After
     fun close(){
        database.close()
    }

    @Test
     fun writeDatabase() = runBlocking {
        val data = DataEntity(1,"Rajesh kumar","rajeshk.chelluri@gamil.com")
        dao.insertData(data)
        val getAllData = dao.getAllData()
        val root = Root().apply {
            id = data.id
            name = data.name
            email = data.email
        }
        assert(getAllData.contains(root))
    }
}