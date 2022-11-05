package com.rajeshkumar.sampleapiimplementation

import com.rajeshkumar.sampleapiimplementation.api.ApiService
import com.rajeshkumar.sampleapiimplementation.api.EndPoint
import com.rajeshkumar.sampleapiimplementation.model.Root
import io.reactivex.Observable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class APITesting {

    @Test
    fun ApiTest(){
        val apiService: ApiService = EndPoint.apiService()
        val items:Observable<List<Root>> = apiService.data
        try{
            val response: Observable<List<Root>>? = items.distinct()
            assert(response?.isEmpty!!.blockingGet())
        } catch (ex: Exception){
            ex.printStackTrace()
        }
    }
}