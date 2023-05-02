package com.chunbae.narchive.presentation.ui.main.calendar.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.chunbae.narchive.R
import com.chunbae.narchive.data.data.CalendarData
import com.chunbae.narchive.data.data.TodoData
import com.chunbae.narchive.databinding.FragmentCalendarBinding
import com.chunbae.narchive.databinding.ItemCalendarDayBinding
import com.chunbae.narchive.presentation.ui.main.MainViewModel
import com.chunbae.narchive.presentation.ui.main.calendar.adapter.CalendarTodoAdapter
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.view.MonthDayBinder
import com.kizitonwose.calendar.view.ViewContainer
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

class CalendarFragment : Fragment() {
    private lateinit var binding: FragmentCalendarBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_calendar, container, false)

        initCalendar()

        return binding.root
    }

    private val daysOfWeek = listOf(
        DayOfWeek.MONDAY,
        DayOfWeek.TUESDAY,
        DayOfWeek.WEDNESDAY,
        DayOfWeek.THURSDAY,
        DayOfWeek.FRIDAY,
        DayOfWeek.SATURDAY,
        DayOfWeek.SUNDAY,
    )

    private fun initCalendar() {
        val daysOfWeek = daysOfWeek
        val currentMonth = YearMonth.now()
        val startMonth = currentMonth.minusMonths(100)
        val endMonth = currentMonth.plusMonths(100)
        setupMonthCalendar(startMonth, endMonth, currentMonth, daysOfWeek)
    }

    inner class DayViewContainer(view: View) : ViewContainer(view) {
        lateinit var day: CalendarDay
        val textView = ItemCalendarDayBinding.bind(view).itemCalendarDayTvDate

        init {
            view.setOnClickListener { moveToTodo() }
        }
    }

    private fun setupMonthCalendar(
        startMonth: YearMonth,
        endMonth: YearMonth,
        currentMonth: YearMonth,
        daysOfWeek: List<DayOfWeek>,
    ) {

        binding.fgCalendarViewCalendar.apply {
            dayBinder = returnDayBinder()
            monthScrollListener = { updateTitle() }
            setup(startMonth, endMonth, daysOfWeek.first())
            scrollToMonth(currentMonth)
        }
    }

    private fun returnDayBinder(): MonthDayBinder<DayViewContainer> =
        object : MonthDayBinder<DayViewContainer> {
            override fun create(view: View) = DayViewContainer(view)
            override fun bind(container: DayViewContainer, data: CalendarDay) {
                container.day = data
                val calBinding = DataBindingUtil.bind<ItemCalendarDayBinding>(container.view)
                calBinding?.let {
                    bindDate(data.date, it, data.position == DayPosition.MonthDate)
                    bindCalendarData(data.date, it)
                }
            }
        }

    private fun updateTitle() {
        val month = binding.fgCalendarViewCalendar.findFirstVisibleMonth()?.yearMonth ?: return
        binding.fgCalendarTvYearMonth.text =
            resources.getString(R.string.calendar_year_month).format(month.year, month.monthValue)
    }

    private fun bindDate(
        date: LocalDate,
        calBinding: ItemCalendarDayBinding,
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

    private fun bindCalendarData(date: LocalDate, calBinding: ItemCalendarDayBinding) {
        calBinding.calendarData = calendarDummy().find { it.date.convertStringToDate() == date }
        calBinding.itemCalendarDayRvTodo.adapter = CalendarTodoAdapter(::moveToTodo).also {
            calendarDummy().find { it.date.convertStringToDate() == date }?.todoList.let { it2 ->
                if (it2 != null) {
                    it.todoItems = it2.todoList.toMutableList()
                }
            }
        }
    }

    private fun String.convertStringToDate(): LocalDate =
        LocalDate.parse(this, DateTimeFormatter.ofPattern("yyyy-MM-dd"))

    private fun moveToTodo() {
        viewModel.setCalClickedTrue()
    }

    /** Dummy */
    private fun calendarDummy(): List<CalendarData> = mutableListOf(
        CalendarData("2023-05-01", true, todoDummy()),
        CalendarData("2023-05-10", true, todoDummy()),
        CalendarData("2023-05-14", true, todoDummy()),
        CalendarData("2023-05-22", true, todoDummy()),
        CalendarData("2023-05-27", true, todoDummy())

    )

    private fun todoDummy(): TodoData = TodoData("", listOf(TodoData.TodoList("13:00", "14:00", "일기 쓰기", "기본", "RED", false)))
}