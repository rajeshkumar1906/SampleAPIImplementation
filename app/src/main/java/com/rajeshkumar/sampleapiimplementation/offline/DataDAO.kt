package com.rajeshkumar.sampleapiimplementation.offline

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rajeshkumar.sampleapiimplementation.model.Root

@Dao
interface DataDAO {
    @Query("SELECT * FROM DataEntity")
    fun getAllData(): List<Root>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(args:DataEntity)
}