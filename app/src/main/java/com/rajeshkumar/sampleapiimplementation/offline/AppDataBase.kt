package com.rajeshkumar.sampleapiimplementation.offline

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Binds
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


@Database(entities = [DataEntity::class], version = 1)
abstract class AppDataBase  : RoomDatabase() {
    abstract fun todoData():DataDAO

//    companion object {
//        private var INSTANCE: AppDataBase? = null
//
//        fun getInstance(): AppDataBase {
//            if (INSTANCE == null) {
//                synchronized(AppDataBase::class) {
//                    INSTANCE = Room.databaseBuilder(mC,
//                        AppDataBase::class.java, "app_database.db").allowMainThreadQueries()
//                        .build()
//                }
//            }
//            return INSTANCE as AppDataBase
//        }
//    }

//    fun getInstance(): AppDataBase {
//        if (INSTANCE == null) {
//            synchronized(AppDataBase::class) {
//                INSTANCE = Room.databaseBuilder(mContext,
//                    AppDataBase::class.java, "app_database.db").allowMainThreadQueries()
//                    .build()
//            }
//        }
//        return INSTANCE as AppDataBase
//    }
}