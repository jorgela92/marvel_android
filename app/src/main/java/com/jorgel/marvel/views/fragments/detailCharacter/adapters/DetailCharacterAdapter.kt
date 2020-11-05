package com.jorgel.marvel.views.fragments.detailCharacter.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jorgel.marvel.R
import com.jorgel.marvel.models.*
import kotlinx.android.synthetic.main.cell_header.view.*
import kotlinx.android.synthetic.main.cell_header.view.imageCellHeader
import kotlinx.android.synthetic.main.cell_list_detail.view.*
import kotlinx.android.synthetic.main.cell_list_detail.view.cellBodyListsDetail
import kotlinx.android.synthetic.main.cell_list_detail.view.cellHeaderListDetail

interface DetailCharacterAdapterCallback {
    fun openLink(title: String ,url: String)
}

class DetailCharacterAdapter(private val context: Context, private val data: Data, private val delegate: DetailCharacterAdapterCallback) :
        RecyclerView.Adapter<DetailCharacterAdapter.BaseViewHolder<*>>() {
    private enum class TypeCells(val value: Int) {
        HEADER(0), COMICS(1), SERIES(2), STORIES(3), EVENTS(4), URLS(5)
    }

    override fun getItemCount(): Int {
        return TypeCells.values().size
    }

    override fun getItemViewType(position: Int): Int {
        return TypeCells.values()[position].value
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when (viewType) {
            TypeCells.HEADER.value -> HeaderViewHolder(LayoutInflater.from(context).inflate(R.layout.cell_header, parent, false))
            TypeCells.COMICS.value -> ComicsViewHolder(LayoutInflater.from(context).inflate(R.layout.cell_list_detail, parent, false))
            TypeCells.SERIES.value -> SeriesViewHolder(LayoutInflater.from(context).inflate(R.layout.cell_list_detail, parent, false))
            TypeCells.STORIES.value -> StoriesViewHolder(LayoutInflater.from(context).inflate(R.layout.cell_list_detail, parent, false))
            TypeCells.EVENTS.value -> EventsViewHolder(LayoutInflater.from(context).inflate(R.layout.cell_list_detail, parent, false))
            TypeCells.URLS.value -> UrlsViewHolder(LayoutInflater.from(context).inflate(R.layout.cell_list_detail, parent, false))
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is HeaderViewHolder -> holder.bind(data.results[0])
            is ComicsViewHolder -> holder.bind(data.results[0].comics)
            is SeriesViewHolder -> holder.bind(data.results[0].series)
            is StoriesViewHolder -> holder.bind(data.results[0].stories)
            is EventsViewHolder -> holder.bind(data.results[0].events)
            is UrlsViewHolder -> holder.bind(data.results[0].urls)
            else -> throw IllegalArgumentException()
        }
    }

    abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: T)
    }

    inner class HeaderViewHolder(itemView: View) : BaseViewHolder<Results>(itemView) {
        override fun bind(item: Results) {
            Glide
                    .with(context)
                    .load(item.thumbnail.path.replace("http", "https") + "." + item.thumbnail.extension)
                    .centerCrop()
                    .placeholder(R.drawable.ic_marvellogo)
                    .into(itemView.imageCellHeader)

            itemView.detailCellHeader.text = item.description
            itemView.titleCellHeader.text = item.name
        }
    }

    inner class ComicsViewHolder(itemView: View) : BaseViewHolder<Comics>(itemView) {
        override fun bind(item: Comics) {
            itemView.titleListDetail.text = context.getText(R.string.comics)
            itemView.recyclerListDetail.layoutManager = LinearLayoutManager(context)
            itemView.recyclerListDetail.adapter = ListItemsDetailAdapter(context, item.items)
            itemView.cellHeaderListDetail.setOnClickListener {
                if (item.items.isEmpty()) {
                    return@setOnClickListener
                }
                if (itemView.cellBodyListsDetail.visibility == View.GONE) {
                    itemView.cellBodyListsDetail.visibility = View.VISIBLE
                    itemView.imageCellHeader.setImageResource(R.drawable.ic_arrow_up)
                } else {
                    itemView.cellBodyListsDetail.visibility = View.GONE
                    itemView.imageCellHeader.setImageResource(R.drawable.ic_arrow_down)
                }
            }
        }
    }

    inner class SeriesViewHolder(itemView: View) : BaseViewHolder<Series>(itemView) {
        override fun bind(item: Series) {
            itemView.titleListDetail.text = context.getText(R.string.series)
            itemView.recyclerListDetail.layoutManager = LinearLayoutManager(context)
            itemView.recyclerListDetail.adapter = ListItemsDetailAdapter(context, item.items)
            itemView.cellHeaderListDetail.setOnClickListener {
                if (item.items.isEmpty()) {
                    return@setOnClickListener
                }
                if (itemView.cellBodyListsDetail.visibility == View.GONE) {
                    itemView.cellBodyListsDetail.visibility = View.VISIBLE
                    itemView.imageCellHeader.setImageResource(R.drawable.ic_arrow_up)
                } else {
                    itemView.cellBodyListsDetail.visibility = View.GONE
                    itemView.imageCellHeader.setImageResource(R.drawable.ic_arrow_down)
                }
            }
        }
    }

    inner class StoriesViewHolder(itemView: View) : BaseViewHolder<Stories>(itemView) {
        override fun bind(item: Stories) {
            itemView.titleListDetail.text = context.getText(R.string.stories)
            itemView.recyclerListDetail.layoutManager = LinearLayoutManager(context)
            itemView.recyclerListDetail.adapter = ListItemsDetailAdapter(context, item.items)
            itemView.cellHeaderListDetail.setOnClickListener {
                if (item.items.isEmpty()) {
                    return@setOnClickListener
                }
                if (itemView.cellBodyListsDetail.visibility == View.GONE) {
                    itemView.cellBodyListsDetail.visibility = View.VISIBLE
                    itemView.imageCellHeader.setImageResource(R.drawable.ic_arrow_up)
                } else {
                    itemView.cellBodyListsDetail.visibility = View.GONE
                    itemView.imageCellHeader.setImageResource(R.drawable.ic_arrow_down)
                }
            }
        }
    }

    inner class EventsViewHolder(itemView: View) : BaseViewHolder<Events>(itemView) {
        override fun bind(item: Events) {
            itemView.titleListDetail.text = context.getText(R.string.events)
            itemView.recyclerListDetail.layoutManager = LinearLayoutManager(context)
            itemView.recyclerListDetail.adapter = ListItemsDetailAdapter(context, item.items)
            itemView.cellHeaderListDetail.setOnClickListener {
                if (item.items.isEmpty()) {
                    return@setOnClickListener
                }
                if (itemView.cellBodyListsDetail.visibility == View.GONE) {
                    itemView.cellBodyListsDetail.visibility = View.VISIBLE
                    itemView.imageCellHeader.setImageResource(R.drawable.ic_arrow_up)
                } else {
                    itemView.cellBodyListsDetail.visibility = View.GONE
                    itemView.imageCellHeader.setImageResource(R.drawable.ic_arrow_down)
                }
            }
        }
    }

    inner class UrlsViewHolder(itemView: View) : BaseViewHolder<List<Urls>>(itemView), ListUrlsDetailAdapterCallback {
        override fun bind(item: List<Urls>) {
            itemView.titleListDetail.text = context.getText(R.string.urls)
            itemView.recyclerListDetail.layoutManager = LinearLayoutManager(context)
            itemView.recyclerListDetail.adapter = ListUrlsDetailAdapter(context, item, this)
            itemView.imageCellHeader.setOnClickListener {
                if (item.isEmpty()) {
                    return@setOnClickListener
                }
                if (itemView.cellBodyListsDetail.visibility == View.GONE) {
                    itemView.cellBodyListsDetail.visibility = View.VISIBLE
                    itemView.imageCellHeader.setImageResource(R.drawable.ic_arrow_up)
                } else {
                    itemView.cellBodyListsDetail.visibility = View.GONE
                    itemView.imageCellHeader.setImageResource(R.drawable.ic_arrow_down)
                }
            }
        }

        override fun openLink(title: String, url: String) {
            delegate.openLink(title, url)
        }
    }
}