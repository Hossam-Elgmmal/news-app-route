package com.route.newsapp.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.route.newsapp.R
import com.route.newsapp.model.NewsData

@Composable
fun NewsCard(newsData: NewsData = NewsData()) {

    Card(
        modifier = Modifier.padding(24.dp, 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Image(
            painter = painterResource(id = newsData.imageId),
            contentDescription = stringResource(R.string.news_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.5F)
                .clip(RoundedCornerShape(8.dp))
        )
        Row {
            Text(
                text = stringResource(id = newsData.sourceId),
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
            text = stringResource(id = newsData.titleId),
            modifier = Modifier.padding(8.dp),
            style = TextStyle(fontSize = 18.sp)
        )
        Text(
            text = stringResource(id = newsData.timeId),
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.End),
            style = TextStyle(fontSize = 13.sp, color = Color.Gray)
        )

    }
}