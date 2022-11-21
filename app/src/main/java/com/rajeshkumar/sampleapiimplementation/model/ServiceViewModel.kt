package com.rajeshkumar.sampleapiimplementation.model

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.util.Log
import androidx.core.content.PackageManagerCompat.LOG_TAG
import androidx.lifecycle.*
import androidx.work.*
import com.rajeshkumar.sampleapiimplementation.MainActivity
import com.rajeshkumar.sampleapiimplementation.RandomWorker
import com.rajeshkumar.sampleapiimplementation.di.NetworkConnectionModule
import com.rajeshkumar.sampleapiimplementation.di.OfflineRepoModule
import com.rajeshkumar.sampleapiimplementation.di.RepositoryModule
import com.rajeshkumar.sampleapiimplementation.offline.DataDAO
import com.rajeshkumar.sampleapiimplementation.offline.DataEntity
import com.rajeshkumar.sampleapiimplementation.repo.OfflineRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Observable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject


@HiltViewModel
class ServiceViewModel @Inject constructor(
    application: Application,
    private val repositoryModule: RepositoryModule,
    private val networkConnectionModule: NetworkConnectionModule,
    private val offlineRepoModule: OfflineRepoModule

) : ViewModel(),
    LifecycleObserver {
    private val mutableLiveData = MutableLiveData<List<Root>>()
    private val application1: Application = application
    private var mOfflineRepo: DataDAO = offlineRepoModule.getAppDB().todoData()


    fun getData(): LiveData<List<Root>> {
        if (networkConnectionModule.isNetworkNetworkConnected())
            apiImplementation()
        else
            getDataFromDB()
        return mutableLiveData
    }

    private fun loadData(): LiveData<List<Root>> {
//        LoadDataRepo(application1.applicationContext, mutableLiveData)
        OfflineRepo(application1, mutableLiveData, offlineRepoModule).initData()
        return mutableLiveData
    }

    @SuppressLint("InvalidPeriodicWorkRequestInterval")
    fun initiateBackGroundTask(activity: Activity) {
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
       val workManager: WorkManager =  WorkManager.getInstance(application1.applicationContext)
        workManager.enqueue(workRequest)
//        workManager.getWorkInfoByIdLiveData(workRequest.id)
        workManager.getWorkInfoByIdLiveData(workRequest.id)
            .observe(activity as MainActivity, Observer<WorkInfo?> { workInfo ->
                if (workInfo != null) {
                    Log.d("ServiceViewModle", "WorkInfo received: state: " + workInfo.state)

//                    val message = workInfo.outputData.getString(ServerRequestsWorker.KEY_MESSAGE)
//                    Log.d("ServiceViewModle", "message: $message")
                }
            })



//        .observe(activity as MainActivity, Observer {
//              workInfo ->
//                workInfo?.run {
//
//                    Log.e("ServiceViewModle","<>workinfo state<>"+workInfo.state)
////                    apiImplementation()
//
////                    if (workInfo.state.isFinished) {
////                        Log.e("Service View model ","<>work info finished")
////                        apiImplementation()
////                    } else {
////                        Log.e("Service View model ","<>work info not finished")
////                    }
//
////                    apiImplementation()
//                }
//
//            })
    }

    private fun apiImplementation() {
        viewModelScope.launch(Dispatchers.IO) {

            val apiService: Observable<List<Root>> = repositoryModule.getData()
            apiService.subscribe { result ->
                run {
                    mutableLiveData.postValue(result)
                    syncData(result)
                }

            }

        }
    }

    private fun getDataFromDB() {
        viewModelScope.launch(Dispatchers.Default) {
//            val repo: DataDAO = offlineRepoModule.getAppDB().todoData()
            mutableLiveData.postValue(mOfflineRepo.getAllData())
        }
    }

    private fun syncData(it: List<Root>) {
        try {
//            val repo: DataDAO = offlineRepoModule.getAppDB().todoData()
            for (i in 1 until it.size) {
                Log.e("Worker", "<>data inserted")
                mOfflineRepo.insertData(DataEntity(i, it[i].name, it[i].email))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}