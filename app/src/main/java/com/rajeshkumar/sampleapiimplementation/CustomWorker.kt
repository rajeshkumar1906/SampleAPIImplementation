package com.rajeshkumar.sampleapiimplementation

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.work.WorkerParameters
import com.rajeshkumar.sampleapiimplementation.model.Root

class CustomWorker(override val context: Context, override val workerParams: WorkerParameters):RandomWorker(context, workerParams){

    fun getMutableData():MutableLiveData<List<Root>>{
     return mutableLiveData
    }

}