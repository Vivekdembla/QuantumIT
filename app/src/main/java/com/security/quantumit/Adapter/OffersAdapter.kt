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

class OffersAdapter(context : Context, list:ArrayList<Pair<String,String>>): RecyclerView.Adapter<OffersAdapter.OffersViewHolder>() {
    val totalOffer = list
    class OffersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val offer_image = itemView.findViewById<ImageView>(R.id.offer_image)
        val offer = itemView.findViewById<TextView>(R.id.offer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OffersViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.offers,parent,false)
        return OffersViewHolder(view)
    }

    override fun onBindViewHolder(holder: OffersViewHolder, position: Int) {
        holder.offer.text = totalOffer[position].second
        Glide.with(holder.itemView.context).load(totalOffer[position].first).error(R.drawable.common_google_signin_btn_icon_dark).into(holder.offer_image)
    }

    override fun getItemCount(): Int {
        return totalOffer.size
    }
}