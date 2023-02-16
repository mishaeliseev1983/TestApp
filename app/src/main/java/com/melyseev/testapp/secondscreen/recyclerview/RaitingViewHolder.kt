package com.melyseev.testapp.secondscreen.recyclerview

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.melyseev.testapp.R

class RaitingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val image = itemView.findViewById<ImageView>(R.id.imageRaiting)
    val title = itemView.findViewById<TextView>(R.id.titleRaiting)
}


