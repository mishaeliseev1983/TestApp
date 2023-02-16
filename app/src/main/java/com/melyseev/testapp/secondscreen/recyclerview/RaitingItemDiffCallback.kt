package com.melyseev.testapp.secondscreen.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.melyseev.testapp.data.repository.DetailRaiting

class RaitingItemDiffCallback : DiffUtil.ItemCallback<DetailRaiting>() {
    override fun areItemsTheSame(oldItem: DetailRaiting, newItem: DetailRaiting): Boolean {
       return oldItem.image == newItem.image
    }

    override fun areContentsTheSame(oldItem: DetailRaiting, newItem: DetailRaiting): Boolean {
       return oldItem == newItem
    }
}
