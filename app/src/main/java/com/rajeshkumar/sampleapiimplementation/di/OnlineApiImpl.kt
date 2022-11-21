package com.rajeshkumar.sampleapiimplementation.di

import android.provider.DocumentsContract
import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.rajeshkumar.sampleapiimplementation.api.ApiService
import com.rajeshkumar.sampleapiimplementation.model.Root
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class OnlineApiImpl @Inject constructor(apiService: ApiService): OnlineApi {

    val apiService = apiService
    override fun getData(): Observable<List<Root>>  = apiService.data
}