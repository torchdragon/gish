package com.torchdragon.gish.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("adapter")
fun bindAdapter(view: RecyclerView, adapter: ListAdapter<*,*> ) {
    view.adapter = adapter
}
