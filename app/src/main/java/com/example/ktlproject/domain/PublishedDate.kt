package com.example.ktlproject.domain

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PublishedDate {

    @SerializedName("\$date")
    @Expose
    var date: String? = null

}