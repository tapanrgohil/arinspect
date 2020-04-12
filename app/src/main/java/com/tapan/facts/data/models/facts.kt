package com.tapan.facts.data.models

import com.google.gson.annotations.SerializedName

data class FactsRS(
    @SerializedName("rows")
    val facts: List<Fact>,
    @SerializedName("title")
    val title: String
)

data class Fact(
    @SerializedName("description")
    val description: String?,
    @SerializedName("imageHref")
    val imageHref: String?,
    @SerializedName("title")
    val title: String?
)
