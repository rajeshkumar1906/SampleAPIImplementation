package com.rajeshkumar.sampleapiimplementation

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.rajeshkumar.sampleapiimplementation.model.Root
import com.rajeshkumar.sampleapiimplementation.repo.SyncData

open class RandomWorker(open val context: Context, workerParams: WorkerParameters) :
    CoroutineWorker(context, workerParams) {
    open val mutableLiveData = MutableLiveData<List<Root>>()

    @SuppressLint("RestrictedApi")
    override suspend fun doWork(): Result {
        try {

            SyncData(context, mutableLiveData)
            Handler(Looper.getMainLooper()).post {
                Toast.makeText(applicationContext, "syncing data ", Toast.LENGTH_LONG)
                    .show()
            }
            try {
                Log.e("MainActivity instance", "<><>" + MainActivity.activity)
                val syncData: SetSyncData = MainActivity.activity
                syncData.syncData(mutableLiveData)


            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("MainActivity instance", "<><>" + e.message)
            }

            return Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return Result.failure()
    }

    interface SetSyncData {
        fun syncData(mutableLiveData: MutableLiveData<List<Root>>)
    }

}