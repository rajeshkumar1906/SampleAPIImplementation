package com.rajeshkumar.sampleapiimplementation.di

import com.rajeshkumar.sampleapiimplementation.offline.AppDataBase
import javax.inject.Inject


class OfflineRepoModule @Inject constructor( private val appDataBase: AppDataBase) {
    fun getAppDB()  = appDataBase

}