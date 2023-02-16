package com.melyseev.testapp.secondscreen.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.melyseev.testapp.R
import com.melyseev.testapp.data.repository.DetailRaiting

class RaitingsAdapter : ListAdapter<DetailRaiting, RaitingViewHolder>(RaitingItemDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RaitingViewHolder {
        val layout = R.layout.raitings_layout
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return RaitingViewHolder(view)

    }

    override fun onBindViewHolder(holder: RaitingViewHolder, position: Int) {
        val element = getItem(position)
        holder.title.text = element.title

        Glide.with(holder.itemView.context)
            .load(element.image)
            .centerCrop()
            .into(holder.image)
    }

    fun change(source: List<DetailRaiting>) {
        submitList(source)
    }
}
