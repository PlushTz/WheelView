package com.eetrust.adapter

/**
 * Desc:
 * @author lijt
 * Created on 2023/6/29 23:00
 * Email:
 */
abstract class BaseWheelAdapter<T>(data: List<T>? = null) {
    private val dataList: MutableList<T> = mutableListOf()
    private var isRangeData: Boolean = false
    internal var isCyclic: Boolean = false
    private val rangeDataList: MutableList<T> by lazy { mutableListOf<T>() }

    init {
        data?.let {
            setData(it)
        }
    }

    fun getItemCount(): Int {
        return if (!isCyclic && isRangeData) rangeDataList.size else dataList.size
    }

    internal fun getRealItemCount(): Int {
        return dataList.size
    }

    internal fun getItemData(position: Int): T? {
        return if (!isCyclic && isRangeData){
            if (inRange(position,rangeDataList)) rangeDataList[position] else null
        }else{
            if (inRange(position,dataList)) dataList[position] else null
        }
    }

    fun setData(data: List<T>) {
        dataList.clear()
        dataList.addAll(data)
    }

    fun setDataRange(min: Int, max: Int) {
        if (min < 0 || max < 0 || min > max
            || !inRange(min, dataList) || !inRange(max, dataList)) {
            isRangeData = false
            rangeDataList.clear()
            return
        }
        isRangeData = true
        rangeDataList.clear()
        for (pos in min..max){
            rangeDataList.add(dataList[pos])
        }
    }

    fun getData(): List<T> {
        return if (!isCyclic && isRangeData) rangeDataList else dataList
    }

    protected fun inRange(position: Int, dataList: List<T>): Boolean {
        return dataList.isNotEmpty() && position in dataList.indices
    }

    internal abstract fun getItemText(item: Any?): String

    internal abstract fun getItemTextByIndex(index: Int): String
}