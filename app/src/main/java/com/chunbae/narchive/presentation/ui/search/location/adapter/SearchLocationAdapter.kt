package com.chunbae.narchive.presentation.ui.search.location.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chunbae.narchive.data.data.LocationData
import com.chunbae.narchive.databinding.ItemSearchLocationResultFormBinding

class SearchLocationAdapter(private val onClick : (LocationData) -> Unit) : ListAdapter<LocationData, SearchLocationAdapter.SearchLocationViewHolder>(
    diffCallback){

    inner class SearchLocationViewHolder(private val binding : ItemSearchLocationResultFormBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : LocationData) {
            binding.locationData = item
            binding.root.setOnClickListener { onClick.invoke(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchLocationViewHolder {
        return SearchLocationViewHolder(ItemSearchLocationResultFormBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: SearchLocationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<LocationData>() {

            override fun areItemsTheSame(oldItem: LocationData, newItem: LocationData): Boolean {
                return oldItem.road_address_name == newItem.road_address_name
            }

            override fun areContentsTheSame(oldItem: LocationData, newItem: LocationData): Boolean {
                return oldItem == newItem
            }
        }
    }
}