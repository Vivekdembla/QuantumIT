package com.security.quantumit.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.security.quantumit.R


class MoreAdapter(context : Context, list : ArrayList<Pair<String, String>>) : RecyclerView.Adapter<MoreAdapter.MoreViewHolder>() {

    val moreItems = list
    class MoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.item_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.more,parent,false)
        return MoreViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoreViewHolder, position: Int) {
        var requestOptions = RequestOptions()
        requestOptions = requestOptions.transforms(GranularRoundedCorners(3f,3f,0f,0f))
        Glide.with(holder.itemView.context).load(moreItems[position].first).apply(requestOptions).into(holder.image)
    }

    override fun getItemCount(): Int {
        return moreItems.size
    }
}