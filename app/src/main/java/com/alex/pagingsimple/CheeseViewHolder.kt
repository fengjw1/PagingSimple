package com.alex.pagingsimple

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CheeseViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.cheese_item, parent, false)
){

    private val nameView = itemView.findViewById<TextView>(R.id.name)
    var cheese: Cheese? = null

    fun bindTo(cheese: Cheese){
        this.cheese = cheese
        nameView.text = cheese.name
    }

}