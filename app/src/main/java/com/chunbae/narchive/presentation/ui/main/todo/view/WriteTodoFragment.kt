package com.chunbae.narchive.presentation.ui.main.todo.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.chunbae.narchive.R
import com.chunbae.narchive.databinding.FragmentWriteTodoBinding
import com.chunbae.narchive.databinding.ItemTodoCalendarDayBinding
import com.chunbae.narchive.databinding.ItemWriteTodoCaldendarBinding
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.view.MonthDayBinder
import com.kizitonwose.calendar.view.ViewContainer
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth

class WriteTodoFragment : Fragment() {
    private lateinit var binding: FragmentWriteTodoBinding

    private val todoViewModel: WriteTodoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_write_todo, container, false)

        initBinding()
        initCalendar()
        initPicker()
        observeCalendarState()

        return binding.root
    }

    private fun initBinding() {
        binding.fragment = this
        binding.todoViewModel = todoViewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun initCalendar() {
        val daysOfWeek = daysOfWeek()
        val currentMonth = YearMonth.now()
        val startMonth = currentMonth.minusMonths(100)
        val endMonth = currentMonth.plusMonths(100)
        setupMonthCalendar(startMonth, endMonth, currentMonth, daysOfWeek)
    }

    private fun initPicker() {
        binding.fgWriteTodoLayoutStartTime.apply {
            itemWriteTodoPickerHour.apply {
                minValue = 0
                maxValue = 24
                value = todoViewModel.startHour.value!!.toInt()
                setOnValueChangedListener { _, _, cur -> todoViewModel.setTime(cur.toString(), true)}
            }

            itemWriteTodoPickerMin.apply {
                displayedValues = getMinArray()
                maxValue = 11
                setOnValueChangedListener { _, _, cur -> todoViewModel.setTime(getMinArray()[cur], false)}
            }
        }

        binding.fgWriteTodoLayoutEndTime.apply {
            itemWriteTodoPickerHour.apply {
                minValue = 0
                maxValue = 24
                value = todoViewModel.startHour.value!!.toInt()
                setOnValueChangedListener { _, _, cur -> todoViewModel.setTime(cur.toString(), true)}
            }

            itemWriteTodoPickerMin.apply {
                displayedValues = getMinArray()
                maxValue = 11
                setOnValueChangedListener { _, _, cur -> todoViewModel.setTime(getMinArray()[cur], false)}
            }
        }
    }

    private fun observeCalendarState() {
        todoViewModel.isStartDay.observe(viewLifecycleOwner) {
            if (it == 1) {
                binding.fgWriteTodoLayoutStartCalendar.root.visibility = View.VISIBLE
                binding.fgWriteTodoLayoutEndCalendar.root.visibility = View.GONE
                binding.fgWriteTodoLayoutStartTime.root.visibility = View.GONE
                binding.fgWriteTodoLayoutEndTime.root.visibility = View.GONE
            } else if(it == 2) {
                binding.fgWriteTodoLayoutStartCalendar.root.visibility = View.GONE
                binding.fgWriteTodoLayoutEndCalendar.root.visibility = View.VISIBLE
                binding.fgWriteTodoLayoutStartTime.root.visibility = View.GONE
                binding.fgWriteTodoLayoutEndTime.root.visibility = View.GONE
            } else if(it == 3) {
                binding.fgWriteTodoLayoutStartCalendar.root.visibility = View.GONE
                binding.fgWriteTodoLayoutEndCalendar.root.visibility = View.GONE
                binding.fgWriteTodoLayoutStartTime.root.visibility = View.VISIBLE
                binding.fgWriteTodoLayoutEndTime.root.visibility = View.GONE
            } else if(it == 4) {
                binding.fgWriteTodoLayoutStartCalendar.root.visibility = View.GONE
                binding.fgWriteTodoLayoutEndCalendar.root.visibility = View.GONE
                binding.fgWriteTodoLayoutStartTime.root.visibility = View.GONE
                binding.fgWriteTodoLayoutEndTime.root.visibility = View.VISIBLE
            }
            else {
                binding.fgWriteTodoLayoutStartCalendar.root.visibility = View.GONE
                binding.fgWriteTodoLayoutEndCalendar.root.visibility = View.GONE
                binding.fgWriteTodoLayoutStartTime.root.visibility = View.GONE
                binding.fgWriteTodoLayoutEndTime.root.visibility = View.GONE
            }
        }
    }

    private fun dateClicked(date: LocalDate) {
        todoViewModel.setDate(date)
    }

    inner class DayViewContainer(view: View) : ViewContainer(view) {
        lateinit var day: CalendarDay
        val textView = ItemTodoCalendarDayBinding.bind(view).itemCalendarDayTvDate

        init {
            view.setOnClickListener {
                if (day.position == DayPosition.MonthDate) {
                    dateClicked(date = day.date)
                }
            }
        }
    }

    private fun setupMonthCalendar(
        startMonth: YearMonth,
        endMonth: YearMonth,
        currentMonth: YearMonth,
        daysOfWeek: List<DayOfWeek>,
    ) {
        binding.fgWriteTodoLayoutStartCalendar.itemWriteTodoCalendarCalendar.apply {
            dayBinder = returnDayBinder()
            monthScrollListener = { updateTitle(binding.fgWriteTodoLayoutStartCalendar) }
            setup(startMonth, endMonth, daysOfWeek.first())
            scrollToMonth(currentMonth)
        }

        binding.fgWriteTodoLayoutEndCalendar.itemWriteTodoCalendarCalendar.apply {
            dayBinder = returnDayBinder()
            monthScrollListener = { updateTitle(binding.fgWriteTodoLayoutEndCalendar) }
            setup(startMonth, endMonth, daysOfWeek.first())
            scrollToMonth(currentMonth)
        }
    }

    private fun returnDayBinder(): MonthDayBinder<DayViewContainer> =
        object : MonthDayBinder<DayViewContainer> {
            override fun create(view: View) = DayViewContainer(view)
            override fun bind(container: DayViewContainer, data: CalendarDay) {
                container.day = data
                val calBinding = DataBindingUtil.bind<ItemTodoCalendarDayBinding>(container.view)
                calBinding?.let {
                    bindDate(data.date, it, data.position == DayPosition.MonthDate)
                }
            }
        }

    private fun updateTitle(mBinding: ItemWriteTodoCaldendarBinding) {
        val month =
            mBinding.itemWriteTodoCalendarCalendar.findFirstVisibleMonth()?.yearMonth ?: return
        mBinding.itemWriteTodoCalendarTvYearMonth.text =
            resources.getString(R.string.calendar_year_month).format(month.year, month.monthValue)
    }

    private fun bindDate(
        date: LocalDate,
        calBinding: ItemTodoCalendarDayBinding,
        isSelectable: Boolean
    ) {
        calBinding.itemCalendarDayTvDate.text = date.dayOfMonth.toString()
        if (!isSelectable) {
            calBinding.itemCalendarDayTvDate.setTextColor(
                resources.getColor(
                    R.color.color_a4a4a4,
                    null
                )
            )
            calBinding.itemCalendarDayTvDate.background = null
        }
        if (LocalDate.now() == date) {
            calBinding.itemCalendarDayTvDate.setTextColor(resources.getColor(R.color.white, null))
            calBinding.itemCalendarDayTvDate.background =
                ResourcesCompat.getDrawable(resources, R.drawable.bg_calendar_current_day, null)
        }
    }


    private fun getMinArray() : Array<String>  {
        return mutableListOf<String>().apply {
            for (i in 0 until 60 step 5) {
                this.add(String.format("%02d", i))
            }
        }.toTypedArray()
    }

}