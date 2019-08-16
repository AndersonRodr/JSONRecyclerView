package com.example.ktlproject.service

import android.content.Context
import com.example.ktlproject.R
import com.example.ktlproject.domain.Book

class ServiceBook {

    fun getCategories(book: Book, ctx: Context): String {
        var strCategories: String = ""
        for (i in 0 until book.categories!!.size) {
            strCategories += book.categories!!.get(i) + ", "
        }
        if (strCategories.isNotEmpty()){
            val newStr = strCategories.substring(0, strCategories.length - 2)
            return newStr
        }
        return ctx.getString(R.string.noCat)
    }

    fun getAuthors(book: Book, ctx: Context): String {
        var strAuthors: String = ""
        for (i in 0 until book.authors!!.size) {
            strAuthors += book.authors!!.get(i) + ", "
        }
        if (strAuthors.isNotEmpty()){
            val newStr = strAuthors.substring(0, strAuthors.length - 2)
            return newStr
        }
        return ctx.getString(R.string.noAut)
    }

    fun title(book: Book, ctx: Context): String{
        if (book.title!! != null || book.title!!.isNotEmpty()){
            return book.title!!
        }
        return ctx.getString(R.string.noTit)
    }

    fun isbn(book: Book, ctx: Context): String{
        if (book.isbn!! != null || book.isbn!!.isNotEmpty()){
            return book.isbn!!
        }
        return ctx.getString(R.string.noIs)
    }

    fun pageC(book: Book): Int {
        if (book.pageCount!! != null){
            return book.pageCount!!
        }
        return 0
    }
}