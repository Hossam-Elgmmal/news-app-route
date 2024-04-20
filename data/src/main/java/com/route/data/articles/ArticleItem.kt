package com.route.data.articles

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.route.domain.models.ArticlesItemDto

@Entity
data class ArticleItem(

    @PrimaryKey
    @field:SerializedName("title")
    val title: String = "",

    var sourcesId: String = "",

    @field:SerializedName("publishedAt")
    val publishedAt: String? = null,

    @field:SerializedName("author")
    val author: String? = null,

    @field:SerializedName("urlToImage")
    val urlToImage: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("url")
    val url: String? = null,

    @field:SerializedName("content")
    val content: String? = null
) {
    fun toDto(): ArticlesItemDto {
        return ArticlesItemDto(
            title,
            sourcesId,
            publishedAt,
            author,
            urlToImage,
            description,
            url,
            content
        )
    }
}