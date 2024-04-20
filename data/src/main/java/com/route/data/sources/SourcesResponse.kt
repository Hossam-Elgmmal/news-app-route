package com.route.data.sources

import com.google.gson.annotations.SerializedName

data class SourcesResponse(

    @SerializedName("status")
    var status: String? = null,

    @SerializedName("sources")
    var sources: List<SourceItem> = listOf()
)

