package com.chunbae.narchive.presentation.ui.gallery.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chunbae.narchive.data.data.GalleryData
import com.chunbae.narchive.databinding.ItemCustomGalleryImagesFormBinding

class GalleryAdapter(private val onClick: (Int) -> Unit, private val onOverClicked : () -> Unit) :
    RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {
    var galleryData = mutableListOf<GalleryData>()

    inner class GalleryViewHolder(private val binding: ItemCustomGalleryImagesFormBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GalleryData) {
            binding.galleryData = item
            binding.root.setOnClickListener {
                if(galleryData.count{ it.isChecked} == 10 && !item.isChecked) {
                    onOverClicked.invoke()
                } else {
                    binding.checkbox.isChecked = binding.checkbox.isChecked.not()
                    onClick.invoke(adapterPosition)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GalleryAdapter.GalleryViewHolder {
        return GalleryViewHolder(
            ItemCustomGalleryImagesFormBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun getItemCount(): Int = galleryData.size

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.bind(galleryData[position])
    }
}