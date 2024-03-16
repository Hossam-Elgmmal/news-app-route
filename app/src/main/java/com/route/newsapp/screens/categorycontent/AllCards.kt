package com.route.newsapp.screens.categorycontent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.route.newsapp.R
import com.route.newsapp.models.articles.ArticlesItem

@Composable
fun AllCards(articles: List<ArticlesItem>, onCardClick: (String) -> Unit) {

    LazyColumn {
        items(articles.size) { position ->
            NewsCard(articles[position]) {
                articles[position].title?.let { onCardClick(it) }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewsCard(article: ArticlesItem, onCardClick: () -> Unit = {}) {

    Card(
        modifier = Modifier
            .padding(24.dp, 16.dp)
            .clickable { onCardClick() },
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        GlideImage(
            model = article.urlToImage ?: "",
            contentDescription = stringResource(R.string.news_image),
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(2f)
                .clip(RoundedCornerShape(8.dp))
        )
        Row {//source name
            Text(
                text = article.source?.name ?: "",
                modifier = Modifier.padding(8.dp),
                style = TextStyle(fontSize = 10.sp, color = Color.Gray)
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_circle),
                contentDescription = "",
                tint = Color.Gray,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
        Text(
            text = article.title ?: "",
            modifier = Modifier.padding(8.dp),
            style = TextStyle(fontSize = 18.sp)
        )
        Text(
            text = article.publishedAt ?: "",
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.End),
            style = TextStyle(fontSize = 13.sp, color = Color.Gray)
        )

    }
}