package com.eetrust.listeners

import com.eetrust.wheelview.WheelView

/**
 * Desc: [WheelView] 滚动变化监听器
 * @author lijt
 * Created on 2023/6/29 23:19
 * Email:
 */
interface OnScrollChangedListener {
    fun onScrollChanged(wheelView: WheelView, scrollOffsetY: Int)

    fun onScrollStateChanged(wheelView: WheelView, state: Int)
}