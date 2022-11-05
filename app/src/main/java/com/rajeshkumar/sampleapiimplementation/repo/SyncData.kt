package com.rajeshkumar.sampleapiimplementation.repo

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.rajeshkumar.sampleapiimplementation.api.ApiService
import com.rajeshkumar.sampleapiimplementation.api.EndPoint
import com.rajeshkumar.sampleapiimplementation.model.Root
import com.rajeshkumar.sampleapiimplementation.offline.AppDataBase
import com.rajeshkumar.sampleapiimplementation.offline.DataDAO
import com.rajeshkumar.sampleapiimplementation.offline.DataEntity
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.lang.Exception
import java.util.ArrayList

class SyncData(val context: Context,val data: MutableLiveData<List<Root>>) {

    init {
        initData()
    }

    private fun initData() {
        var items: List<Root> = ArrayList(100)
        val apiService: ApiService = EndPoint.apiService()
        Log.e("SyncData", "<><>apiService<>${apiService.data}")
        apiService.data
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : Observer<List<Root>> {
                override fun onSubscribe(d: Disposable) {
                    Log.e("SyncData", "<>onSubscribe<>")
                }

                override fun onNext(t: List<Root>) {
                    Log.e("SyncData", "<>onNext<>" + t[0].name)
                    data.postValue(t)
                    items = t
                }

                override fun onError(e: Throwable) {
                    Log.e("SyncData", "<>onError<>" + e.message)
                }

                override fun onComplete() {
                    Log.e("SyncData", "<>onComplete<>")
                    syncData(items)
                }

            })
    }

    fun syncData(it: List<Root>) {
        try {
            val repo: DataDAO = AppDataBase.getInstance(context).todoData()
            for (i in 1 until it.size) {
                Log.e("Worker", "<>data inserted")
                repo.insertData(DataEntity(i, it[i].name, it[i].email))
            }
        } catch (e: Exception){
            e.printStackTrace()
        }

    }
}