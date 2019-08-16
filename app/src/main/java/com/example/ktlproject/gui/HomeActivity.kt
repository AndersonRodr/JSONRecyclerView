package com.example.ktlproject.gui
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ktlproject.service.AdapterBook
import com.example.ktlproject.R
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_home.*
import org.json.JSONArray
import org.json.JSONException
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.ArrayList
import com.example.ktlproject.domain.Book
import com.example.ktlproject.service.ServiceBook

class HomeActivity : AppCompatActivity() {
    private var data: String? = ""
    private var service: ServiceBook = ServiceBook()
    companion object {
        @JvmStatic var listBookFinal = ArrayList<Book>()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        read()
        createRecycler()
    }
    private fun createRecycler(){
        val recyclerLinear = LinearLayoutManager(this)
        recyclerLivros.setLayoutManager(recyclerLinear)
        val adapterBook =
            AdapterBook(listBookFinal)
        adapterBook.setOnClickListener(View.OnClickListener { view ->
            val it = Intent(applicationContext, InformationsBookActivity::class.java)
            val bundle = Bundle()
            val book: Book = listBookFinal[recyclerLivros.getChildAdapterPosition(view)]
            bundle.putString(getString(R.string.title), service.title(book, this))
            bundle.putString(getString(R.string.authors), service.getAuthors(book, this))
            bundle.putString(getString(R.string.isbn), service.isbn(book, this))
            bundle.putInt(getString(R.string.page), service.pageC(book))
            bundle.putString(getString(R.string.date), book.publishedDate!!.date)
            bundle.putString(getString(R.string.status), book.status)
            bundle.putString(getString(R.string.categories), service.getCategories(book, this))
            bundle.putString(getString(R.string.sDesc), book.shortDescription)
            bundle.putString(getString(R.string.lDesc), book.longDescription)
            bundle.putString(getString(R.string.url), book.thumbnailUrl)
            it.putExtras(bundle)
            startActivity(it)
        })
        recyclerLivros.setAdapter(adapterBook)
    }
    private fun read() {
        Thread(Runnable {
            try {
                val url = URL(getString(R.string.urlJson))
                val httpURLConnection = url.openConnection() as HttpURLConnection
                val inputStream = httpURLConnection.inputStream
                val bufferedReader = BufferedReader(InputStreamReader(inputStream))
                var line: String? = ""
                while (line != null) {
                    line = bufferedReader.readLine()
                    data = data + line
                }
                val jsonArray = JSONArray(data)
                for (i in 0 until jsonArray.length()) {
                    if (jsonArray.getString(i).contains(getString(R.string.dateErro))) {
                        val book = Gson().fromJson(jsonArray.getString(i), Book::class.java)
                        listBookFinal.add(book)
                    }
                }
            } catch (e: MalformedURLException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }).start()
    }
}


