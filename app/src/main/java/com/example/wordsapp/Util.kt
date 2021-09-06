package com.example.wordsapp

import android.content.Context
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


object Util {
  fun MenuItem.setLayoutItem(
    context: Context,
    isLinear: Boolean
  ) {
    title = context.getString(
      if(isLinear) R.string.change_to_grid_layout
      else R.string.change_to_linear_layout
    )
    icon = ContextCompat.getDrawable(
      context,
      if(isLinear) R.drawable.ic_grid_layout
      else R.drawable.ic_linear_layout
    )
  }

  fun RecyclerView.changeLayout(isLinear: Boolean) {
    layoutManager = if(isLinear) LinearLayoutManager(context)
    else GridLayoutManager(context, 2)
  }
}