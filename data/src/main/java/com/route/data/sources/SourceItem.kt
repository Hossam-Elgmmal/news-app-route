package com.route.data.sources

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.route.domain.models.SourceItemDto

@Entity
data class SourceItem(

    @PrimaryKey
    @SerializedName("id")
    var id: String = "",

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("description")
    var description: String? = null,

    @SerializedName("url")
    var url: String? = null,

    @SerializedName("category")
    var category: String? = null,

    ) {
    fun toDto(): SourceItemDto {
        return SourceItemDto(id, name, description, url, category)
    }
}
