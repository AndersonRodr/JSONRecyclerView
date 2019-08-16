package com.example.ktlproject.gui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.ktlproject.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_informations_book.*

class InformationsBookActivity : AppCompatActivity() {

    var title: String = ""
    var authors: String = ""
    var isbn: String = ""
    var page: String = ""
    var date: String = ""
    var status: String = ""
    var categories: String = ""
    var sDesc: String = ""
    var lDesc: String = ""
    var url: String = ""
    var image: ImageView? = null;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_informations_book)

        val intent: Intent = getIntent()
        if (intent != null){
            val bundle: Bundle = intent.extras!!
            if(bundle != null){
                title = bundle.get(getString(R.string.title)).toString()
                authors = bundle.get(getString(R.string.authors)).toString()
                isbn = bundle.get(getString(R.string.isbn)).toString()
                page = bundle.get(getString(R.string.page)).toString()
                date = bundle.get(getString(R.string.date)).toString()
                status = bundle.get(getString(R.string.status)).toString()
                categories = bundle.get(getString(R.string.categories)).toString()
                sDesc = bundle.get(getString(R.string.sDesc)).toString()
                lDesc = bundle.get(getString(R.string.lDesc)).toString()
                url = bundle.get(getString(R.string.url)).toString()
            }
        }
        loadThumb();
        setInformations()
    }

    fun loadThumb(){
        if (url != "No Image.") {
            Picasso.with(this).load(url).placeholder(R.mipmap.refresh2).error(R.mipmap.refresh2)
                .into(imageView, object : com.squareup.picasso.Callback {

                    override fun onSuccess() {

                    }

                    override fun onError() {

                    }
                })
        }
    }
    fun setInformations(){
        formatDate()
        textView3.text = getString(R.string.titleInfo) + title
        textView4.text = getString(R.string.autInfo) + authors
        textView6.text = getString(R.string.isbnInfo) + isbn
        textView7.text = getString(R.string.pagInfo) + page
        textView5.text = getString(R.string.pageInfo) + date
        textView8.text = getString(R.string.statusInfo) + status
        textView12.text = getString(R.string.catInfo) + categories
        textView11.text = getString(R.string.sDescInfo) + sDesc
        textView10.text = getString(R.string.lDescInfo) + lDesc
    }
    fun formatDate(){
        if (date.length > 9){
            val strDate = date.substring(0, 10);
            val year = strDate.substring(0,4)
            val month = strDate.substring(5, 7)
            val day = strDate.substring(8, 10)
            date = day + "/" + month + "/" + year
        }
    }

}
