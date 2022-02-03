package com.security.quantumit.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.security.quantumit.R

class NormalAdapter(context: Context, list: ArrayList<Pair<String,String>>) : RecyclerView.Adapter<NormalAdapter.NormalViewHolder>() {

    val normalItem = list
    class NormalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.image_of_item)
        val discount = itemView.findViewById<TextView>(R.id.discount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NormalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.normal,parent,false)
        return NormalViewHolder(view)
    }

    override fun onBindViewHolder(holder: NormalViewHolder, position: Int) {
        Glide.with(holder.itemView.context).load(normalItem[position].first).error(R.drawable.google).into(holder.image)
        holder.discount.text = normalItem[position].second
    }

    override fun getItemCount(): Int {
        return normalItem.size
    }
}