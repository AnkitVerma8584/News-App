package com.example.news.domain.util

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed class StringUtil {

    data class DynamicText(val value: String) : StringUtil()

    class StringResource(@StringRes val id: Int, vararg val args: Any) : StringUtil()

    fun asString(context: Context): String =
        when (this@StringUtil) {
            is DynamicText -> value
            is StringResource -> context.getString(id, *args)
        }

    @Composable
    fun asString(): String {
        return when (this) {
            is DynamicText -> value
            is StringResource -> stringResource(id, args)
        }
    }
}
