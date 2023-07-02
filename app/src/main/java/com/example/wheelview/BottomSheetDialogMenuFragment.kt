package com.example.wheelview

import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eetrust.adapter.ArrayWheelAdapter
import com.eetrust.listeners.OnItemSelectedListener
import com.eetrust.wheelview.WheelView
import com.example.wheelview.databinding.DialogBottomSheetMenuBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


/**
 * Desc:
 * @author lijt
 * Created on 2023/6/30 22:50
 * Email:
 */
class BottomSheetDialogMenuFragment private constructor() : BottomSheetDialogFragment() {
    companion object {
        fun getInstance(): BottomSheetDialogMenuFragment {
            val bundle = Bundle()
            val bottomSheetDialogMenuFragment = BottomSheetDialogMenuFragment()
            bottomSheetDialogMenuFragment.arguments = bundle
            return bottomSheetDialogMenuFragment
        }
    }

    private lateinit var binding: DialogBottomSheetMenuBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DialogBottomSheetMenuBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
    }

    private fun initListener() {
        val data = mutableListOf<Int>()
        for (value in 0 until 60) {
            if (value % 5 == 0) {
                data.add(value)
            }
        }

        binding.wheelViewMinute.setData(data)

        binding.wheelViewMinute.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(wheelView: WheelView, adapter: ArrayWheelAdapter<*>, position: Int) {

            }
        })
        val hours = mutableListOf<Int>()
        for (value in 0 until 24) {
            hours.add(value)
        }

        binding.wheelViewHour.setData(hours)
        binding.wheelViewHour.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(wheelView: WheelView, adapter: ArrayWheelAdapter<*>, position: Int) {

            }
        })

        val dates = mutableListOf<String>()
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("MM月dd日 EEE", Locale.getDefault())
        for (i in 0..29) {
            val date: String = dateFormat.format(calendar.time)
            dates.add(date)
            calendar.add(Calendar.DATE, 1)
        }

        binding.wheelViewDate.setData(dates)
    }

    private fun getFutureDates(): List<Date> {
        val futureDates: MutableList<Date> = ArrayList<Date>()
        val calendar: Calendar = Calendar.getInstance()
        for (i in 0..29) {
            calendar.add(Calendar.DATE, 1)
            futureDates.add(calendar.time)
        }
        return futureDates
    }
}