package com.jorgel.marvel.views.fragments.detailCharacter.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jorgel.marvel.R
import com.jorgel.marvel.models.Urls
import kotlinx.android.synthetic.main.cell_list_item.view.*
import java.util.*

interface ListUrlsDetailAdapterCallback {
    fun openLink(title: String, url: String)
}

class ListUrlsDetailAdapter(private val context: Context, private val data:  List<Urls>, private val delegate: ListUrlsDetailAdapterCallback) :
        RecyclerView.Adapter<ListUrlsDetailAdapter.ListUrlsDetailHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListUrlsDetailHolder {
        return ListUrlsDetailHolder(LayoutInflater.from(context).inflate(R.layout.cell_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ListUrlsDetailHolder, position: Int) {
        holder.bindItems(position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ListUrlsDetailHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(position: Int) {
            itemView.titleListItem.text = data[position].type.capitalize(Locale.getDefault())
            if (position == data.size - 1) {
                itemView.separatorView.visibility = View.INVISIBLE
            }
            itemView.setOnClickListener {
                delegate.openLink(data[position].type.capitalize(Locale.getDefault()), data[position].url)
            }
        }
    }
}