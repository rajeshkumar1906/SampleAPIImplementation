package com.rajeshkumar.sampleapiimplementation.repo

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.rajeshkumar.sampleapiimplementation.Utilities
import com.rajeshkumar.sampleapiimplementation.model.Root
import com.rajeshkumar.sampleapiimplementation.offline.AppDataBase

class LoadDataRepo(val context: Context,val mutableLiveData: MutableLiveData<List<Root>>) {
    init {
        getData()
    }
    private fun getData(){
//        if(Utilities.isNetworkConnected(context)){
//            loadOnlineData()
//        } else {
            loadOfflineData()
//        }
    }

    private fun loadOnlineData(){
        OnlineMode(mutableLiveData)
    }

    private fun loadOfflineData(){
//       OfflineRepo(context,mutableLiveData).initData()
    }
}