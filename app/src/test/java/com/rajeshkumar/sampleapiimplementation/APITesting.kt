package com.rajeshkumar.sampleapiimplementation

import android.annotation.SuppressLint
import com.rajeshkumar.sampleapiimplementation.api.ApiService
import com.rajeshkumar.sampleapiimplementation.api.EndPoint
import com.rajeshkumar.sampleapiimplementation.model.Root
import io.reactivex.Observable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import com.google.common.truth.Truth.assertThat
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import junit.framework.Assert.assertTrue

@RunWith(JUnit4::class)
class APITesting {

    @SuppressLint("CheckResult")
    @Test
    fun ApiTest(){
        val apiService: ApiService = EndPoint.apiService()
        val items:Observable<List<Root>> = apiService.data
        try{
            val response: Observable<List<Root>>? = items.distinct()
            assertThat(response.toString()).isNotEmpty()
             response?.subscribe(Consumer {
                    it.size
                 assertTrue(it.size>0)
            })

        } catch (ex: Exception){
            ex.printStackTrace()
        }


    }
}