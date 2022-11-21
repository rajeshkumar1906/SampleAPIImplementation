package com.rajeshkumar.sampleapiimplementation.repo

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.rajeshkumar.sampleapiimplementation.di.OfflineRepoModule
import com.rajeshkumar.sampleapiimplementation.model.Root
import com.rajeshkumar.sampleapiimplementation.offline.AppDataBase
import com.rajeshkumar.sampleapiimplementation.offline.DataDAO
import javax.inject.Inject

class OfflineRepo(val context: Context,val liveData: MutableLiveData<List<Root>>, val offlineRepoModule: OfflineRepoModule) {

//    @Inject
//    lateinit var appDataBase: AppDataBase
//      @Inject
//      lateinit var offlineRepoModule: OfflineRepoModule

    fun initData(){
//        val repo : DataDAO = AppDataBase.getInstance(context).todoData()
        val repo : DataDAO = offlineRepoModule.getAppDB().todoData()
        liveData.postValue(repo.getAllData())
    }
}