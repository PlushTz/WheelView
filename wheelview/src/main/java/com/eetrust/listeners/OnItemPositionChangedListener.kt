package com.eetrust.listeners

import com.eetrust.wheelview.WheelView

/**
 * Desc: [WheelView]
 * @author lijt
 * Created on 2023/6/29 23:18
 * Email:
 */
interface OnItemPositionChangedListener {
    fun onItemChanged(wheelView: WheelView, oldPosition: Int, newPosition: Int)
}