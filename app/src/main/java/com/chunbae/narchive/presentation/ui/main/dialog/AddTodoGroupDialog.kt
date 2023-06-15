package com.chunbae.narchive.presentation.ui.main.dialog

import android.content.Context
import android.content.DialogInterface
import android.graphics.Point
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.constraintlayout.widget.Group
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.chunbae.narchive.R
import com.chunbae.narchive.data.data.GroupColorData
import com.chunbae.narchive.databinding.DialogAddTodoGroupBinding
import com.chunbae.narchive.presentation.ui.main.dialog.adapter.AddTodoGroupColorAdapter
import com.chunbae.narchive.presentation.ui.main.todo.viewmodel.WriteTodoViewModel
import com.chunbae.narchive.presentation.ui.todogroup.viewmodel.TodoGroupManageViewModel

class AddTodoGroupDialog : DialogFragment() {
    private lateinit var binding : DialogAddTodoGroupBinding
    private val viewModel : TodoGroupManageViewModel by activityViewModels()
    private lateinit var TAG : String
    private val colorAdapter by lazy {
        AddTodoGroupColorAdapter(::selectColor, colorList)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_add_todo_group, container, false)

        initBinding()
        checkTag()
        setColorRV()

        return binding.root
    }

    private fun checkTag() {
        TAG = this.tag!!

        if(TAG == "EDIT") {
            setEdit()
        }
    }

    override fun onResume() {
        super.onResume()
        val windowManager = requireActivity().getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val params: ViewGroup.LayoutParams? = dialog?.window?.attributes
        val deviceWidth = size.x
        params?.width = (deviceWidth * 0.95).toInt()
        params?.height = WindowManager.LayoutParams.WRAP_CONTENT
        dialog?.window?.attributes = params as WindowManager.LayoutParams
    }

    private fun initBinding() {
        binding.viewModel = viewModel
        binding.dialog = this
    }

    private fun setEdit() {
        viewModel.editGroup.observe(viewLifecycleOwner) {
            viewModel.newGroupTitle.value = it?.groupTitle
            it?.groupColor?.let { it1 -> viewModel.setNewAddColor(it1) }
            colorList.find { list -> list.color == it?.groupColor }?.isSelect = true
        }
    }

    private fun setColorRV() {
        binding.dialogAddTodoGroupRvColors.adapter = colorAdapter
        binding.dialogAddTodoGroupRvColors.itemAnimator = null
    }

    private fun selectColor(color : String) {
        viewModel.setNewAddColor(color)
    }

    fun onSaveGroup() {
        viewModel.addNewGroup(TAG)
        dismissDialog()
    }

    fun dismissDialog() {
        dismiss()
    }
    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        viewModel.initAddNew()
    }

    /** Dummy */
    private var colorList : MutableList<GroupColorData> = mutableListOf("RED", "ORANGE", "YELLOW", "GREEN", "BLUE", "NAVY", "PURPLE", "BLACK", "PINK", "GRAY").map { GroupColorData(it) } as MutableList

}