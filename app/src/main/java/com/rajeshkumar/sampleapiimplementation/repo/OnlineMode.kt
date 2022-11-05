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


class OnlineMode(val liveData: MutableLiveData<List<Root>>) {
    private lateinit var mContext: Context

    constructor(context: Context, liveData: MutableLiveData<List<Root>>) : this(liveData) {
        mContext = context
    }

    init {
        initData()
    }

    private fun initData() {
        val items: List<Root> = ArrayList()
        val apiService: ApiService = EndPoint.apiService()
        Log.e("ServiceViewModel", "<><>apiService<>${apiService.data}")
        apiService.data
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : Observer<List<Root>> {
                override fun onSubscribe(d: Disposable) {
                    Log.e("ServiceViewModel", "<>onSubscribe<>")
                }

                override fun onNext(t: List<Root>) {
                    Log.e("ServiceViewModel", "<>onNext<>" + t.size)
                    liveData.postValue(t)
                }

                override fun onError(e: Throwable) {
                    Log.e("ServiceViewModel", "<>onError<>" + e.message)
                }

                override fun onComplete() {
                    Log.e("ServiceViewModel", "<>onComplete<>")
                    syncData(items)
                }

            })
    }


    fun syncData(it: List<Root>) {
        try {
            val repo: DataDAO = AppDataBase.getInstance(mContext).todoData()
            for (i in 1 until it.size) {
                Log.e("Worker", "<>data inserted")
                repo.insertData(DataEntity(i, it[i].name, it[i].email))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

