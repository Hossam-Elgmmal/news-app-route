package com.route.newsapp.screens.articledetail

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.route.newsapp.R
import com.route.newsapp.models.articles.ArticlesItem
import com.route.newsapp.ui.theme.Gray


@Composable
fun ArticleDetailsScreen(vm: ArticleDetailsViewModel = viewModel(), articleTitle: String) {

    LaunchedEffect(key1 = Unit) {
        vm.getArticle(articleTitle)
    }

    Scaffold(
        topBar = {
            ArticleDetailsAppBar()
        },
        containerColor = Color.Transparent
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(top = paddingValues.calculateTopPadding())
        ) {
            item {
                if (vm.newsArticles.size > 0) {
                    ArticlesResponseDetails(vm, vm.newsArticles[0])
                }
            }
        }
    }

}

@SuppressLint("QueryPermissionsNeeded")
@Preview(showSystemUi = true, showBackground = true)
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ArticlesResponseDetails(
    vm: ArticleDetailsViewModel = viewModel(),
    article: ArticlesItem = ArticlesItem()
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {

        GlideImage(
            model = article.urlToImage ?: "",
            contentDescription = stringResource(R.string.news_image),
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(2f)

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
            modifier = Modifier.padding(16.dp),
            style = TextStyle(Color.DarkGray, 18.sp)
        )
        Text(
            text = article.content ?: "",
            overflow = TextOverflow.Visible,
            maxLines = 8,
            style = TextStyle(Gray, 14.sp)
        )
        Text(
            text = article.publishedAt ?: "",
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.End),
            style = TextStyle(fontSize = 13.sp, color = Color.Gray)
        )
        TextButton(
            onClick = {
                vm.openInBrowser(context, article.url)
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(
                text = article.source?.name ?: "",
                modifier = Modifier
                    .padding(8.dp),
                style = TextStyle(Color.DarkGray, 13.sp)
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_launch),
                contentDescription = "",
                tint = Color.DarkGray,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}
