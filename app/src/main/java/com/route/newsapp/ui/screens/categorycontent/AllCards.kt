package com.route.newsapp.ui.screens.categorycontent

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.route.data.articles.ArticleItem
import com.route.newsapp.R

@Composable
fun AllCards(
    isInternetAvailable: Boolean,
    articles: List<ArticleItem>,
    onCardClick: (String) -> Unit
) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn {
            items(articles.size) { position ->
                NewsCard(articles[position]) {
                    onCardClick(articles[position].title)
                }
            }
        }
        if (!isInternetAvailable) {
            Text(
                text = "no connection",
                color = Color.White,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(Color.Black)
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewsCard(article: ArticleItem, onCardClick: () -> Unit = {}) {

    Card(
        modifier = Modifier
            .padding(24.dp, 16.dp)
            .clickable { onCardClick() },
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(4.dp)
    ) {
        GlideImage(
            model = article.urlToImage ?: "",
            contentDescription = stringResource(R.string.news_image),
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(2f)
                .clip(RoundedCornerShape(4.dp))
        )
        Row {//source name
            Text(
                text = article.author ?: "",
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
            text = article.title,
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