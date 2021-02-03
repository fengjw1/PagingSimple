package com.alex.pagingsimple

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil

class CheeseAdapter: PagingDataAdapter<Cheese, CheeseViewHolder> (callBack){

    override fun onBindViewHolder(holder: CheeseViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bindTo(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheeseViewHolder =
        CheeseViewHolder(parent)

    companion object{
        private val callBack = object : DiffUtil.ItemCallback<Cheese>(){
            override fun areItemsTheSame(oldItem: Cheese, newItem: Cheese): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Cheese, newItem: Cheese): Boolean =
                oldItem == newItem
        }
    }
}