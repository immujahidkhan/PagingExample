package com.paging.models


import com.google.gson.annotations.SerializedName

data class Collection(
    @SerializedName("href")
    val href: String
)