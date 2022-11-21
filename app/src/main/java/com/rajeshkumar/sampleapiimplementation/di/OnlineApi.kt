package com.rajeshkumar.sampleapiimplementation.di

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.rajeshkumar.sampleapiimplementation.model.Root
import dagger.Binds
import io.reactivex.Observable


interface OnlineApi {
    fun getData() : Observable<List<Root>>
}