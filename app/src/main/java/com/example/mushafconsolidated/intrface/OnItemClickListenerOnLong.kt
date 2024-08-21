package com.example.mushafconsolidated.intrfaceimport

import android.view.View


open interface OnItemClickListenerOnLong {
    fun onItemClick(v: View, position: Int)
    fun onItemLongClick(position: Int, v: View)
}