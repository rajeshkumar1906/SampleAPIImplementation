package com.rajeshkumar.sampleapiimplementation.di

import android.content.Context
import android.net.ConnectivityManager
import androidx.room.Room
import com.rajeshkumar.sampleapiimplementation.api.ApiService
import com.rajeshkumar.sampleapiimplementation.offline.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModel {


    @Provides
    @Singleton
    fun getApi(): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://gorest.co.in/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideOnlineRepo(apiService: OnlineApiImpl): OnlineApi {
        return apiService
    }

    @Provides
    @Singleton
    fun provideAppDataBase(@ApplicationContext context: Context): AppDataBase {

        return Room.databaseBuilder(
            context,
            AppDataBase::class.java, "app_database.db"
        ).allowMainThreadQueries()
            .build()


    }


}