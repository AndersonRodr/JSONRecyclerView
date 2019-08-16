package com.example.ktlproject.domain

import java.io.Serializable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Book : Serializable {
    var title: String? = ""
    var isbn: String? = ""
    var pageCount: Int = 0
    @SerializedName("publishedDate")
    @Expose
    var publishedDate: PublishedDate? = null
    var thumbnailUrl: String? = null
    var shortDescription: String? = null
    var longDescription: String? = null
    var status: String? = null
    @SerializedName("authors")
    @Expose
    var authors: List<String>? = null
    @SerializedName("categories")
    @Expose
    var categories: List<String>? = null

}