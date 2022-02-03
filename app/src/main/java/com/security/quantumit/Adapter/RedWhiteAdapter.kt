package com.security.quantumit.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.security.quantumit.Adapter.RedWhiteAdapter.Red_White_ViewHolder
import com.security.quantumit.R


class RedWhiteAdapter(context : Context, item : ArrayList<Pair<String,String>>): RecyclerView.Adapter<Red_White_ViewHolder>() {
    var totalItems = item

    class Red_White_ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val item_image = itemView.findViewById<ImageView>(R.id.object_image)
        val deal = itemView.findViewById<TextView>(R.id.deal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Red_White_ViewHolder {
        val view :View= LayoutInflater.from(parent.context).inflate(R.layout.red_white, parent, false)
        return Red_White_ViewHolder(view)
    }

    override fun onBindViewHolder(holder: Red_White_ViewHolder, position: Int) {
        holder.deal.text = totalItems[position].second
        Glide.with(holder.itemView.context).load(totalItems[position].first)
            .error(R.drawable.common_google_signin_btn_icon_dark)
            .into(holder.item_image)
    }

    override fun getItemCount(): Int {
        return totalItems.size
    }

}