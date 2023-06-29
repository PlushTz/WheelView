package com.eetrust.listeners

import com.eetrust.adapter.ArrayWheelAdapter
import com.eetrust.wheelview.WheelView

/**
 * Desc:滚动停止后的选中监听器
 * @author lijt
 * Created on 2023/6/29 22:59
 * Email:
 */
interface OnItemSelectedListener {
    fun onItemSelected(wheelView: WheelView, adapter: ArrayWheelAdapter<*>, position: Int)
}