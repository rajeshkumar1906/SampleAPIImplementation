package com.rajeshkumar.sampleapiimplementation

import android.annotation.SuppressLint
import android.os.*
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.*
import com.rajeshkumar.sampleapiimplementation.adapter.CustomListAdapter
import com.rajeshkumar.sampleapiimplementation.model.Root
import com.rajeshkumar.sampleapiimplementation.model.ServiceViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),RandomWorker.SetSyncData{
    companion object{
       lateinit var activity: MainActivity
    }
     val serviceViewModel: ServiceViewModel by viewModels()
    lateinit var  recyclerView: RecyclerView

    @SuppressLint("InvalidPeriodicWorkRequestInterval")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recycler_view)
        activity = this
        serviceViewModel.initiateBackGroundTask(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val observer: Observer<List<Root>> = Observer { t ->
            recyclerView.adapter = CustomListAdapter(t)
        }
        serviceViewModel.getData().observe(this,observer)
    }

    override fun syncData(mutableLiveData: MutableLiveData<List<Root>>){
        val observer: Observer<List<Root>> = Observer { t ->
            Log.e("MainAcitivity","<>syncing"+t)
            recyclerView.adapter = CustomListAdapter(t)

        }
        Handler(Looper.getMainLooper()).post {
            mutableLiveData.observe(this,observer)
        }

    }
    class IncomingHandler : Handler.Callback {


        override fun handleMessage(msg: Message): Boolean {
            when (msg.what) {
//                MyService.MSG_SET_INT_VALUE -> textIntValue.setText("Int Message: " + msg.arg1)
//                MyService.MSG_SET_STRING_VALUE -> {
//                    val str1: String = msg.getData().getString("str1")
//                    textStrValue.setText("Str Message: $str1")
//                }

            }

            return true
        }
    }
}

