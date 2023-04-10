package com.chunbae.narchive.presentation.ui.main.feed.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chunbae.narchive.data.data.FeedData
import com.chunbae.narchive.databinding.ItemFeedNormalDiaryBinding
import com.chunbae.narchive.databinding.ItemFeedSimpleDiaryBinding

class FeedAdapter () : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var feedDatas = mutableListOf<FeedData>()

    inner class NormalDiaryViewHolder(private val binding : ItemFeedNormalDiaryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : FeedData) {
            binding.feedData = item
        }
    }

    inner class SimpleDiaryViewHolder(private val binding : ItemFeedSimpleDiaryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : FeedData) {
            binding.feedData = item
            binding.itemFeedSimpleDiaryRvKeywords.adapter = item.keywords?.let { SimpleDiaryKeywordAdapter(it) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        //:TODO 추후에 아이템 없을때 ViewHolder도 추가하기
        return when(viewType) {
            1 -> SimpleDiaryViewHolder(ItemFeedSimpleDiaryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else -> NormalDiaryViewHolder(ItemFeedNormalDiaryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(feedDatas.isEmpty()) 0 else {
            if(feedDatas[position].isSimple == "T") 1 else 2
        }
    }

    override fun getItemCount(): Int = feedDatas.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is NormalDiaryViewHolder -> holder.bind(feedDatas[position])
            is SimpleDiaryViewHolder -> holder.bind(feedDatas[position])
        }
    }
}