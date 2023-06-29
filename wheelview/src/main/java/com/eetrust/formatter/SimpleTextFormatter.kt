package com.eetrust.formatter

import java.util.Locale

/**
 * Desc: 通用的[TextFormatter]
 * 如果自定义的实体类需要别的属性则直接重新 [text] 方法即可
 * @author lijt
 * Created on 2023/6/29 23:03
 * Email:
 */
open class SimpleTextFormatter<T> @JvmOverloads constructor(private val format: String = "%s") :
    TextFormatter {

    override fun formatText(item: Any?): String {
        return item?.let { data ->
            val value = data as? T
            value?.let {
                String.format(Locale.getDefault(), format, text(it))
            } ?: ""
        } ?: ""
    }

    protected open fun text(item: T): Any {
        return item as Any
    }
}