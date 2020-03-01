package com.szeptun.shoppinglist.commons

import android.view.View
import androidx.databinding.BindingAdapter
import com.szeptun.shoppinglist.entity.ListState

@BindingAdapter("viewVisibility")
fun View.viewVisibility(listState: ListState?) {
    listState?.let {
        visibility = when (listState) {
            ListState.ACTIVE -> View.VISIBLE
            ListState.ARCHIVE -> View.GONE
        }
    }
}