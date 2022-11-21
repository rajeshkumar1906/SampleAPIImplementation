package com.rajeshkumar.sampleapiimplementation

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rajeshkumar.sampleapiimplementation.adapter.CustomListAdapter
import com.rajeshkumar.sampleapiimplementation.databinding.ActivityMainBinding
import com.rajeshkumar.sampleapiimplementation.model.Root
import com.rajeshkumar.sampleapiimplementation.model.ServiceViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), RandomWorker.SetSyncData,CustomListAdapter.OnItemClickListener {
    companion object {
        lateinit var activity: MainActivity
    }

    private val serviceViewModel: ServiceViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        activity = this
//        setContent {
//           val viewModel: HiltViewModel<>(Ser)
//        }
        serviceViewModel.initiateBackGroundTask()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        val observer: Observer<List<Root>> = Observer { t ->
            binding.recyclerView.adapter = CustomListAdapter(t)
        }
        serviceViewModel.getData().observe(this, observer)
    }

    override fun syncData(mutableLiveData: MutableLiveData<List<Root>>) {
        val observer: Observer<List<Root>> = Observer { t ->
            binding.recyclerView.adapter = CustomListAdapter(t)

        }
        Handler(Looper.getMainLooper()).post {
            mutableLiveData.observe(this, observer)
        }

    }

    override fun itemOnClick() {
        val intent = Intent(this,ActivityTwo::class.java)
        startActivity(intent)
    }
}

