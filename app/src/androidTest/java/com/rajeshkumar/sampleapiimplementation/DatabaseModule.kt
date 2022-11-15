package com.rajeshkumar.sampleapiimplementation

import android.content.Context
import androidx.room.Room
import com.rajeshkumar.sampleapiimplementation.offline.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.modules.ApplicationContextModule
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Named("tested_db")
    fun getDatabaseInject(@ApplicationContext context: Context): AppDataBase{
         return Room.inMemoryDatabaseBuilder(context,AppDataBase::class.java)
             .allowMainThreadQueries()
             .build()
    }
}