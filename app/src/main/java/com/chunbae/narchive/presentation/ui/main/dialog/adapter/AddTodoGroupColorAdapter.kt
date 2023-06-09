package com.chunbae.narchive.presentation.ui.main.dialog.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chunbae.narchive.data.data.GroupColorData
import com.chunbae.narchive.data.data.GroupData
import com.chunbae.narchive.databinding.ItemDialogAddTodoGroupColorsBinding
import com.chunbae.narchive.presentation.util.TodoColor

class AddTodoGroupColorAdapter(private val selectColor : (String) -> Unit, private var colorList : MutableList<GroupColorData>) : RecyclerView.Adapter<AddTodoGroupColorAdapter.AddTodoGroupColorViewHolder>() {
    inner class AddTodoGroupColorViewHolder(private val binding : ItemDialogAddTodoGroupColorsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : GroupColorData) {
            binding.color = item
            binding.root.setOnClickListener {
                selectColor.invoke(item.color)
                initSelect()
                item.isSelect = true
                notifyItemRangeChanged(0, colorList.size)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddTodoGroupColorViewHolder {
        return AddTodoGroupColorViewHolder(ItemDialogAddTodoGroupColorsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = colorList.size

    override fun onBindViewHolder(holder: AddTodoGroupColorViewHolder, position: Int) {
        holder.bind(colorList[position])
    }

    private fun initSelect() {
        for(i in colorList) {
            i.isSelect = false
        }
    }
}