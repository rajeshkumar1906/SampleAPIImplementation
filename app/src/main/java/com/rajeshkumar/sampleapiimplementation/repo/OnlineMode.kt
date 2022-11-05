package com.rajeshkumar.sampleapiimplementation.repo

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.rajeshkumar.sampleapiimplementation.SampleApplication
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


class OnlineMode(val liveData: MutableLiveData<List<Root>>) {
    var items: List<Root> = ArrayList<Root>()
    private lateinit var mContext: Context

    constructor(context: Context, liveData: MutableLiveData<List<Root>>) : this(liveData) {
        mContext = context
    }

    init {
        initData()
    }

    fun initData() {
        val items: List<Root> = ArrayList()
        val apiService: ApiService = EndPoint.apiService()
        Log.e("ServiceViewModel", "<><>apiService<>${apiService.data}")
        var count: Int = 0
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
    //                    items[count].name = t[count].name
    //                    items[count].email = t[count].email
    //                    items[count].id = t[count].id
    //                    //setupOfflineData(t)
    //                    count++
    //                    setData(t)
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

    fun setData(listItems: List<Root>) {
        items = listItems;
    }

    fun getData(): List<Root> {
        return items
    }


    fun syncData(it: List<Root>) {
        try {
            val repo: DataDAO = AppDataBase.getInstance(mContext).todoData()
            for (i in 1 until it.size) {
                Log.e("Worker", "<>data inserted")
                repo.insertData(DataEntity(i, it[i].name, it[i].email))
            }
        } catch (e:Exception){
            e.printStackTrace()
        }

//        val observer: androidx.lifecycle.Observer<List<Root>> = androidx.lifecycle.Observer {
//            kotlin.run {
//                for (i in 1 until it.size) {
//                    Log.e("Worker", "<>data inserted")
//                    repo.insertData(DataEntity(i, it[i].name, it[i].email))
//                }
//            }
//        }
//        mutableLiveData.observe(this,observer)

    }

    fun setupOfflineData(items: List<Root>) {

        val context: Context = SampleApplication().applicationContext
        Log.e("Online mode", "<>Online mode<>$context")
        val db = Room.databaseBuilder(context, AppDataBase::class.java, "sample.db").build()
        for (i in 1..items.size) {
            Log.e("Onlinemode", "<>data inserted")
            db.todoData().insertData(DataEntity(i, items[i].name, items[i].email))
        }
    }
}

