package com.rajeshkumar.sampleapiimplementation

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SampleApplication:Application() {

        override fun getApplicationContext():Context{
            return this
        }


}