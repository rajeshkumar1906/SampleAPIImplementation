package com.rajeshkumar.sampleapiimplementation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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
    }

    override fun getItemCount(): Int {
       return items.size
    }

    inner  class ViewHolder(private val binding: ListItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item: Root) {
            binding.item = item
            binding.executePendingBindings()
        }
    }
}