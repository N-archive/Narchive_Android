package com.chunbae.narchive.presentation.ui.main.feed.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.chunbae.narchive.R
import com.chunbae.narchive.data.data.FeedData
import com.chunbae.narchive.databinding.ItemFeedNormalDiaryBinding
import com.chunbae.narchive.databinding.ItemFeedSimpleDiaryBinding
import com.google.android.material.chip.Chip

class FeedAdapter (private val onClicked : (Int) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var feedDatas = mutableListOf<FeedData>()
    private lateinit var context : Context

    inner class NormalDiaryViewHolder(private val binding : ItemFeedNormalDiaryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : FeedData) {
            binding.feedData = item
            binding.root.setOnClickListener { onClicked.invoke(item.feedIdx) }
        }
    }

    inner class SimpleDiaryViewHolder(private val binding : ItemFeedSimpleDiaryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : FeedData) {
            binding.feedData = item
            item.keywords?.let {
                if (binding.itemFeedSimpleDiaryRvKeywords.childCount == 0) {
                    for (i in it) {
                        binding.itemFeedSimpleDiaryRvKeywords.addView(Chip(context).apply {
                            text = i
                            isCloseIconVisible = false
                            chipBackgroundColor = ResourcesCompat.getColorStateList(
                                resources,
                                R.color.color_B2F0F4,
                                null
                            )
                            setTextColor(
                                ResourcesCompat.getColorStateList(
                                    resources,
                                    R.color.white,
                                    null
                                )
                            )
                        })
                    }
                }
            }
            binding.root.setOnClickListener { onClicked.invoke(item.feedIdx) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        //:TODO 추후에 아이템 없을때 ViewHolder도 추가하기
        context = parent.context
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