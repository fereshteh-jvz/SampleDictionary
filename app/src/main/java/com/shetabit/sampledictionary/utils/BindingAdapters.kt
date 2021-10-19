package com.shetabit.sampledictionary.utils

import android.opengl.Visibility
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter

class BindingAdapters {
    @BindingAdapter("isGone")
    fun bindIsGone(view: ImageView, isGone: Boolean?) {
        if (isGone == null || isGone) {
            view.visibility = View.GONE
        } else {
            view.visibility = View.VISIBLE
        }
    }
}