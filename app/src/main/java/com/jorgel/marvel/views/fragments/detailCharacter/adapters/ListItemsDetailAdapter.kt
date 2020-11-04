package com.jorgel.marvel.views.fragments.detailCharacter.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jorgel.marvel.R
import com.jorgel.marvel.models.Items
import kotlinx.android.synthetic.main.cell_list_item.view.*


class ListItemsDetailAdapter(private val context: Context, private val data:  List<Items>) :
        RecyclerView.Adapter<ListItemsDetailAdapter.ListItemsDetailHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemsDetailHolder {
        return ListItemsDetailHolder(LayoutInflater.from(context).inflate(R.layout.cell_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ListItemsDetailHolder, position: Int) {
        holder.bindItems(position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ListItemsDetailHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(position: Int) {
            itemView.titleListItem.text = data[position].name
            if (position == data.size - 1) {
                itemView.separatorView.visibility = View.INVISIBLE
            }
        }
    }
}