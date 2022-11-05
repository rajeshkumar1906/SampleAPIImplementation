package com.rajeshkumar.sampleapiimplementation.model

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.*
import androidx.work.*
import com.rajeshkumar.sampleapiimplementation.RandomWorker
import com.rajeshkumar.sampleapiimplementation.repo.LoadDataRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class ServiceViewModel @Inject constructor(application: Application) : ViewModel(),
    LifecycleObserver {
    val mutableLiveData = MutableLiveData<List<Root>>()
    val application1: Application = application
    fun getData(): LiveData<List<Root>> {
        loadData()
        return mutableLiveData
    }

    fun loadData(): LiveData<List<Root>> {
        LoadDataRepo(application1.applicationContext, mutableLiveData)
        return mutableLiveData
    }

    fun updateUI(): LiveData<List<Root>> {

        return mutableLiveData
    }

    @SuppressLint("InvalidPeriodicWorkRequestInterval")
    fun initiateBackGroundTask(lifecycleOwner: LifecycleOwner) {
        val constraints: Constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val workRequest: WorkRequest = PeriodicWorkRequest.Builder(
            RandomWorker::class.java, 1,
            TimeUnit.SECONDS
        )
            .setConstraints(constraints)
            .build()
       val workManager:WorkManager =  WorkManager.getInstance()
        workManager.enqueue(workRequest)

//        workManager.getWorkInfoByIdLiveData(workRequest.id)
//            .observe(lifecycleOwner, Observer { workStatus: WorkInfo ->
//                Log.e("ServiceViewModel","<>workStatus "+workStatus.state)
//                Log.e("ServiceViewModel","<>workStatus "+workStatus.outputData.keyValueMap)
//                if (workStatus.state == WorkInfo.State.SUCCEEDED) {
//                    val mutableLiveData:MutableLiveData<Root> = workStatus.outputData.keyValueMap.get("data") as MutableLiveData<Root>
//                    Log.e("ServiceViewModel","<>mutableLiveData<>"+mutableLiveData)
//                //                    val arrayItems:Array<String> = workStatus.outputData.getStringArray("number") as Array<String>
////                    val items:List<String> = arrayItems.asList()
//                }
//            })
    }

}