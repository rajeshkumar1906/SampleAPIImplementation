package com.rajeshkumar.sampleapiimplementation.model

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.*
import androidx.work.*
import com.rajeshkumar.sampleapiimplementation.RandomWorker
import com.rajeshkumar.sampleapiimplementation.di.NetworkConnectionModule
import com.rajeshkumar.sampleapiimplementation.di.RepositoryModule
import com.rajeshkumar.sampleapiimplementation.repo.LoadDataRepo
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
    private val networkConnectionModule: NetworkConnectionModule

) : ViewModel(),
    LifecycleObserver {
    private val mutableLiveData = MutableLiveData<List<Root>>()
    private val application1: Application = application

    //    init {
//        repositoryModule.getApi()
//    }
    fun getData(): LiveData<List<Root>> {
//        loadData()
        if (networkConnectionModule.isNetworkNetworkConnected())
            apiImplementation()
        else
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
        WorkManager.getInstance(application1.applicationContext).enqueue(workRequest)
    }

    private fun apiImplementation() {
        viewModelScope.launch(Dispatchers.IO) {

            val apiService: Observable<List<Root>> = repositoryModule.getData()
            apiService.subscribe { result ->
                run {
                    mutableLiveData.postValue(result)
                }

            }
//            apiService.data
//                        ?.subscribeOn(Schedulers.io())
//            ?.observeOn(AndroidSchedulers.mainThread())

//                .subscribe()
//            ?.subscribe(object : Observer<List<Root>> {
//                override fun onSubscribe(d: Disposable) {
//                    Log.e("ServiceViewModel", "<>onSubscribe<>")
//                }
//
//                override fun onNext(t: List<Root>) {
//                    Log.e("ServiceViewModel", "<>onNext<>" + t.size)
//                    liveData.postValue(t)
//                }
//
//                override fun onError(e: Throwable) {
//                    Log.e("ServiceViewModel", "<>onError<>" + e.message)
//                }
//
//                override fun onComplete() {
//                    Log.e("ServiceViewModel", "<>onComplete<>")
//                    syncData(items)
//                }
//
//            })
//    }

        }
    }

}