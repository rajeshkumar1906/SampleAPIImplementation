package com.rajeshkumar.sampleapiimplementation

import android.app.Application
import android.content.Context
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.DelegatingWorkerFactory
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class SampleApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory
    override fun getApplicationContext(): Context {
        return this
    }

    override fun getWorkManagerConfiguration(): Configuration {
    return Configuration.Builder()
         .setWorkerFactory(workerFactory)
         .build()
    }


}