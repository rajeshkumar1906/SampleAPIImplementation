package com.rajeshkumar.sampleapiimplementation.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.rajeshkumar.sampleapiimplementation.ActivityTwo
import com.rajeshkumar.sampleapiimplementation.MainActivity
import com.rajeshkumar.sampleapiimplementation.R
import com.rajeshkumar.sampleapiimplementation.databinding.ListItemBinding
import com.rajeshkumar.sampleapiimplementation.model.Root

class CustomListAdapter(private val items:List<Root>): RecyclerView.Adapter<CustomListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener{
            Toast.makeText(MainActivity.activity,"item clicked",Toast.LENGTH_LONG).show()
             val onItemClickListener: OnItemClickListener = (MainActivity.activity)
            onItemClickListener.itemOnClick()

        }
    }

    override fun getItemCount(): Int {
       return items.size
    }

    inner  class ViewHolder( val binding: ListItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item: Root) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

    interface OnItemClickListener{
        fun itemOnClick()
    }
}