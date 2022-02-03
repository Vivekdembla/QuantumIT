package com.security.quantumit.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.security.quantumit.R

class HomeAdapter(context : Context, list:ArrayList<Pair<String,ArrayList<Pair<String,String>>>>)
    : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    val homeItem = list
    val context = context
    class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val category_type = itemView.findViewById<TextView>(R.id.category_type)
        val recyclerView = itemView.findViewById<RecyclerView>(R.id.itemRecyclerView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.categories_item,parent,false)
        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        when(position){
            0 -> {
                holder.category_type.visibility = View.GONE
                holder.recyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
                holder.recyclerView.adapter = SearchAdapter()
            }
            1 -> {
                holder.category_type.visibility = View.GONE
                holder.recyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
                holder.recyclerView.adapter = SecondViewAdapter()
            }
            else -> {
                holder.category_type.text = homeItem[position-2].first
                holder.recyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
                if(position==2){
                    holder.category_type.visibility = View.GONE
                    holder.recyclerView.adapter = OffersAdapter(context,homeItem[position-2].second)
                }
                else if(position == 3)
                    holder.recyclerView.adapter = RedWhiteAdapter(context,homeItem[position-2].second)
                else if(position == 4||position ==5){
                    holder.recyclerView.adapter = NormalAdapter(context,homeItem[position-2].second)
                }else{
                    holder.recyclerView.adapter = NormalAdapter(context,homeItem[position-2].second)
                    holder.recyclerView.layoutManager = GridLayoutManager(context,2)
                }
            }
        }
        holder.recyclerView.hasFixedSize()

    }

    override fun getItemCount(): Int {
        return homeItem.size+2
    }

}