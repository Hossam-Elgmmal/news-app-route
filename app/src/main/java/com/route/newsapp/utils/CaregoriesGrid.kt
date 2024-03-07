package com.route.newsapp.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.route.newsapp.R
import com.route.newsapp.model.Category
import com.route.newsapp.model.Constants
import com.route.newsapp.ui.theme.Gray

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AllCategories(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .padding(24.dp)
            .fillMaxSize()
    ) {

        Text(
            text = stringResource(R.string.pick_your_category),
            style = TextStyle(Gray, 22.sp, FontWeight.Bold)
        )

        LazyVerticalGrid(
            columns = GridCells.FixedSize(150.dp),
            horizontalArrangement = Arrangement.Center,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            items(Constants.ALL_CATEGORIES.size) {
                CategoryItem(Constants.ALL_CATEGORIES[it], it)
            }
        }
    }
}

@Composable
fun CategoryItem(category: Category = Category(), index: Int) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = category.color
        ),
        modifier = Modifier.padding(8.dp),
        shape = if (index % 2 == 0) {
            RoundedCornerShape(24.dp, 24.dp, 24.dp, 0.dp)
        } else {
            RoundedCornerShape(24.dp, 24.dp, 0.dp, 24.dp)
        }
    ) {
        Image(
            painter = painterResource(id = category.imageId),
            contentDescription = stringResource(id = category.titleId),

            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
                .aspectRatio(1f)
                .clip(MaterialTheme.shapes.extraSmall),
            contentScale = ContentScale.Fit
        )
        Text(
            text = stringResource(id = category.titleId),
            style = TextStyle(Color.White, 22.sp, FontWeight.Normal),
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}