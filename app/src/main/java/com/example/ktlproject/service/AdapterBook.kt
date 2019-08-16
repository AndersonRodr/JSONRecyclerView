package com.example.ktlproject.service

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ktlproject.R
import com.example.ktlproject.domain.Book
import java.util.ArrayList

class AdapterBook(listBooks: ArrayList<Book>) : RecyclerView.Adapter<AdapterBook.ListBookViewHolder>(),
    View.OnClickListener {
    private var listBooks = ArrayList<Book>()
    private var authors = ""
    private var listener: View.OnClickListener? = null


    init {
        this.listBooks = listBooks
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListBookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        view.setOnClickListener(this)
        return ListBookViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListBookViewHolder, position: Int) {
        holder.title!!.text = listBooks[position].title
        for (author in listBooks[position].authors!!) {
            authors += author + ", "
        }
        val newStr = authors.substring(0, authors.length - 2)
        holder.subTitle!!.text = newStr
        authors = ""
    }

    override fun getItemCount(): Int {
        return listBooks.size
    }

    fun setOnClickListener(listener: View.OnClickListener) {
        this.listener = listener
    }

    override fun onClick(view: View) {
        if (listener != null) {
            listener!!.onClick(view)
        }
    }

    class ListBookViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView? = null
        var subTitle: TextView? = null
        init {
            title = itemView.findViewById(R.id.textView)
            subTitle = itemView.findViewById(R.id.textView2)
        }

    }

}