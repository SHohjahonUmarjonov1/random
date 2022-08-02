package com.example.random2

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(private val func:(String)->Set<String>):ListAdapter<String, ItemAdapter.ItemHolder>(diff_utill){
    inner class ItemHolder(view : CardView):  RecyclerView.ViewHolder(view){
        val text=view.findViewById<TextView>(R.id.item)
        fun bind(item:String,position: Int){
            text.text="${position+1}. $item"

            text.setOnLongClickListener {
                PopupMenu(text.context,text).apply {
                    menu.add("Delete")
                    setOnMenuItemClickListener {
                        val items=func(item)
                        submitList(items.toList())
                        return@setOnMenuItemClickListener true
                    }
                    show()
                }
                return@setOnLongClickListener true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val li=LayoutInflater.from(parent.context)
        val view=li.inflate(R.layout.items_list,parent,false)
        return ItemHolder(view as CardView)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(getItem(position),position)
    }

    companion object{
        val diff_utill=object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String)=oldItem==newItem

            override fun areContentsTheSame(oldItem: String, newItem: String)=oldItem==newItem

        }
    }
}