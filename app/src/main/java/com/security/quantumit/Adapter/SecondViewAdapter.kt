package com.security.quantumit.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.security.quantumit.R

class SecondViewAdapter() : RecyclerView.Adapter<SecondViewAdapter.SecondViewHolder>() {
    class SecondViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.adv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SecondViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.second_view,parent,false)
        return SecondViewHolder(view)
    }

    override fun onBindViewHolder(holder: SecondViewHolder, position: Int) {
        when(position){
            0 ->{
                holder.image.setImageResource(R.drawable.uspolo)
            }
            1 -> {
                holder.image.setImageResource(R.drawable.bigbachat)
            }
            2 -> {
                holder.image.setImageResource(R.drawable.hangbags)
            }
        }
    }

    override fun getItemCount(): Int {
        return 3
    }
}