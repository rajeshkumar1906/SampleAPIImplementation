package com.rajeshkumar.sampleapiimplementation.repo

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.rajeshkumar.sampleapiimplementation.model.Root
import com.rajeshkumar.sampleapiimplementation.offline.AppDataBase
import com.rajeshkumar.sampleapiimplementation.offline.DataDAO

class OfflineRepo(val context: Context,val liveData: MutableLiveData<List<Root>>) {
    init {
        initData()
    }
    fun initData(){
        val repo : DataDAO = AppDataBase.getInstance(context).todoData()
        liveData.postValue(repo.getAllData())
    }
}