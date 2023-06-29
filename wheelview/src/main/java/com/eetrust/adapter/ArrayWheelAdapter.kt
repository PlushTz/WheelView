package com.eetrust.adapter

import com.eetrust.formatter.TextFormatter

/**
 * Desc:
 * @author lijt
 * Created on 2023/6/29 23:12
 * Email:
 */
open class ArrayWheelAdapter<T> @JvmOverloads constructor(data: List<T>? = null)
    : BaseWheelAdapter<T>(data) {

    internal var textFormatter: TextFormatter? = null
    internal var formatterBlock: ((Any?) -> String)? = null
    internal var finishScrollCallback: OnFinishScrollCallback? = null
    internal var selectedItemPosition: Int = 0
    internal var itemIndexer: ItemIndexer? = null
    internal var itemIndexerBlock: ((ArrayWheelAdapter<*>, Any?) -> Int)? = null

    override fun getItemText(item: Any?): String {
        return textFormatter?.formatText(item)
            ?: (formatterBlock?.invoke(item) ?: (item?.toString() ?: ""))
    }

    override fun getItemTextByIndex(index: Int): String {
        val dataSize = getItemCount()
        if (dataSize == 0) {
            return ""
        }
        if (isCyclic) {
            var i = index % dataSize
            if (i < 0) {
                i += dataSize
            }
            return getItemText(getItemData(i))
        } else {
            if (index in 0 until dataSize) {
                return getItemText(getItemData(index))
            }
        }
        return ""
    }

    /**
     * 根据item参数信息查找所在的下标
     */
    @JvmOverloads
    fun indexOf(item: Any?, isCompareFormatText: Boolean = false): Int {
        return itemIndexer?.indexOf(this, item)
            ?: (itemIndexerBlock?.invoke(this, item)
                ?: internalIndexOf(item, isCompareFormatText))
    }

    private fun internalIndexOf(item: Any?, isCompareFormatText: Boolean): Int {
        for (i in getData().indices) {
            if (isCompareFormatText) {
                if (getItemTextByIndex(i) == item) {
                    return i
                }
            } else {
                if (getData()[i] == item) {
                    return i
                }
            }
        }
        return -1
    }

    /**
     * 根据下标获取条目数据
     */
    @Suppress("UNCHECKED_CAST")
    fun <V> getItem(position: Int): V? {
        return getItemData(position) as? V
    }

    /**
     * 获取选中条目下标
     */
    fun getSelectedPosition(): Int {
        finishScrollCallback?.onFinishScroll()
        return selectedItemPosition
    }

    /**
     * 获取选中条目数据
     */
    fun <V> getSelectedItem(): V? {
        finishScrollCallback?.onFinishScroll()
        return getItem(selectedItemPosition)
    }

    /**
     * 获取数据列表
     */
    @Suppress("UNCHECKED_CAST")
    fun <V> getDataList(): List<V>? {
        return getData() as? List<V>
    }

    internal interface OnFinishScrollCallback {

        fun onFinishScroll()
    }
}