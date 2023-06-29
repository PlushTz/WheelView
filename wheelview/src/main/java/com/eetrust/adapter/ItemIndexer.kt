package com.eetrust.adapter

/**
 * Desc: item 索引器，用来自定义 indexOf 逻辑
 * @author lijt
 * Created on 2023/6/29 23:12
 * Email:
 */
interface ItemIndexer {
    fun indexOf(adapter: ArrayWheelAdapter<*>, item: Any?): Int
}