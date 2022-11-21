package com.rajeshkumar.sampleapiimplementation.di

import com.rajeshkumar.sampleapiimplementation.offline.AppDataBase

interface OfflineApi {
 fun getOfflineApi(): AppDataBase
}