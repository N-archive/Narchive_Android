package com.chunbae.narchive.presentation.ui.main.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.chunbae.narchive.R
import com.chunbae.narchive.databinding.DialogWriteTodoGroupBinding
import com.chunbae.narchive.presentation.ui.main.todo.adapter.TodoWriteGroupAdapter
import com.chunbae.narchive.presentation.ui.main.todo.viewmodel.WriteTodoViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class WriteTodoGroupDialog : BottomSheetDialogFragment() {
    private lateinit var binding : DialogWriteTodoGroupBinding
    private val viewModel : WriteTodoViewModel by activityViewModels()

    private val groupAdapter by lazy {
        TodoWriteGroupAdapter(::onClick)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_write_todo_group, container, false)

        initBinding()
        initRV()
        observe()

        return binding.root
    }

    private fun initBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun initRV() {
        binding.dialogContentWriteRvSelector.adapter = groupAdapter

    }

    private fun observe() {
        viewModel.userGroupList.observe(viewLifecycleOwner) {
            groupAdapter.groupList = it
            groupAdapter.notifyDataSetChanged()
        }
    }

    private fun onClick(position : Int) {
        if(position == groupAdapter.itemCount - 1) {
            AddTodoGroupDialog().show(requireActivity().supportFragmentManager, "ADD_GROUP")
        } else {
            viewModel.setGroupSelect(position)
        }
        dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.groupDialogOpen()
    }

}