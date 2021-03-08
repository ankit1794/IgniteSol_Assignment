package com.ankit.ignitesolassignment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ankit.ignitesolassignment.R
import com.ankit.ignitesolassignment.`interface`.OnBookItemClick
import com.ankit.ignitesolassignment.model.Results
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.collections.ArrayList

class BooksAdapter(booksList: ArrayList<Results>, context: Context, listner: OnBookItemClick) :
    RecyclerView.Adapter<BooksAdapter.BooksViewHolder>(),
    Filterable {
    var mBookList: ArrayList<Results> = booksList
    var mContext: Context? = context
    var booksFilterList = mBookList
    var mListner = listner


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.book_card, parent, false)
        return BooksViewHolder(v)
    }

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        holder.book_name?.setText(booksFilterList.get(position).title)
        if (!booksFilterList.get(position).authors.isEmpty()) {
            holder.auther_name?.setText(booksFilterList.get(position).authors.get(0).name)
        }

        if (booksFilterList.get(position).formats?.image_jpeg != null) {
            holder.iv_default_thumbnail!!.visibility = View.GONE
            Picasso.get()
                .load(booksFilterList.get(position).formats.image_jpeg)
                .into(holder.iv_thumbnail);
        } else {
            holder.iv_default_thumbnail!!.visibility = View.VISIBLE

        }

        holder.itemView.setOnClickListener {
            mListner.onItemClick(position)
        }

    }

    override fun getItemCount(): Int {
        return booksFilterList.size
    }

    class BooksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var book_name: TextView? = null
        var auther_name: TextView? = null
        var iv_thumbnail: ImageView? = null
        var iv_default_thumbnail: ImageView? = null

        init {
            book_name = itemView.findViewById(R.id.bookName)
            auther_name = itemView.findViewById(R.id.autherName)
            iv_thumbnail = itemView.findViewById(R.id.iv_thumbnail)
            iv_default_thumbnail = itemView.findViewById(R.id.iv_default_thumbnail)
        }
    }

    override fun getFilter(): Filter {

        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    booksFilterList = mBookList
                } else {
                    val resultList = ArrayList<Results>()
                    for (row in mBookList) {

                        if (row.title.toLowerCase(Locale.ROOT).contains(
                                charSearch.toLowerCase(
                                    Locale.ROOT
                                )
                            )
                        ) {
                            resultList.add(row)
                        }
                    }
                    booksFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = booksFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                booksFilterList = results?.values as ArrayList<Results>
                notifyDataSetChanged()
            }

        }
    }


}