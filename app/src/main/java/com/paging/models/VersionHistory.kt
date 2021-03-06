package com.paging.models


import com.google.gson.annotations.SerializedName

data class VersionHistory(
    @SerializedName("count")
    val count: Int,
    @SerializedName("href")
    val href: String
)