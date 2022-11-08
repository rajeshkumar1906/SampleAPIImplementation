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
    private val mutableLiveData = MutableLiveData<List<Root>>()
    private val application1: Application = application
    fun getData(): LiveData<List<Root>> {
        loadData()
        return mutableLiveData
    }

    private fun loadData(): LiveData<List<Root>> {
        LoadDataRepo(application1.applicationContext, mutableLiveData)
        return mutableLiveData
    }

    @SuppressLint("InvalidPeriodicWorkRequestInterval")
    fun initiateBackGroundTask() {
        val constraints: Constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val workRequest: WorkRequest = PeriodicWorkRequest.Builder(
            RandomWorker::class.java, 5,
            TimeUnit.SECONDS
        )
            .setConstraints(constraints)
            .build()
        val workManager: WorkManager = WorkManager.getInstance()
        workManager.enqueue(workRequest)
    }

}