package com.podcast.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.example.podcast.R
import kotlinx.android.synthetic.main.custom_error_view.view.*

class CustomError : FrameLayout {

    private lateinit var ctx: Context

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context)
    }

    private fun init(context: Context) {
        ctx = context
        inflate(context, R.layout.custom_error_view, this)
        visibility = GONE
    }

    fun setListener(errorListener: ErrorCallBack) {
        txtRetry.setOnClickListener {
            errorListener.onRetryClick()
        }
    }

    fun setErrorMessage(message: String? = ctx.resources.getString(R.string.error_general)) {
        errorViewMainLayout.visibility = VISIBLE
        visibility = VISIBLE
        errorLayout.visibility = VISIBLE
        progressBar.visibility = GONE
        txtMessage.text = message
        txtRetry.visibility = VISIBLE
    }

    fun showLoading() {
        errorLayout.visibility = GONE
        progressBar.visibility = VISIBLE
        errorViewMainLayout.visibility = VISIBLE
        visibility = VISIBLE
    }

    fun hideLoading() {
        errorViewMainLayout.visibility = GONE
        visibility = GONE
        errorLayout.visibility = GONE
        progressBar.visibility = GONE
    }

    fun showEmptyView() {
        errorViewMainLayout.visibility = VISIBLE
        visibility = VISIBLE
        errorLayout.visibility = VISIBLE
        progressBar.visibility = GONE
        txtMessage.text = ctx.getString(R.string.data_empty)
        txtRetry.visibility = GONE

    }

    interface ErrorCallBack {
        fun onRetryClick()
    }
}