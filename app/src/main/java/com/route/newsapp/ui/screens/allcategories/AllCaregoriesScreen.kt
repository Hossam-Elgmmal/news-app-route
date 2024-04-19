package com.route.newsapp.ui.screens.allcategories

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.route.newsapp.R
import com.route.newsapp.models.categories.CategoryItem
import com.route.newsapp.models.categories.Constants
import com.route.newsapp.ui.theme.Gray

@Preview(
    device = "id:pixel_8_pro", showSystemUi = true, showBackground = true
)
@Composable
fun AllCategoriesScreen(
    navController: NavHostController = rememberNavController(),
    onNavigationIconClick: () -> Unit = {}
) {

    Scaffold(
        topBar = {
            CategoriesAppBar {
                onNavigationIconClick()
            }
        },
        containerColor = Color.Transparent
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding())
                .padding(24.dp)
        ) {

            Text(
                text = stringResource(R.string.pick_your_category),
                style = TextStyle(Gray, 24.sp, FontWeight.Bold)
            )

            LazyVerticalGrid(
                columns = GridCells.Adaptive(145.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(16.dp)
            ) {
                items(Constants.categoriesList.size) { position ->
                    CategoryItem(Constants.categoriesList[position], position) {

                        navController.navigate("News/$position")

                    }
                }
            }
        }
    }
}

@Composable
fun CategoryItem(
    categoryItem: CategoryItem = CategoryItem(),
    index: Int,
    onCardClick: () -> Unit = {}
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = categoryItem.color
        ),
        shape = if (index % 2 == 0) {
            RoundedCornerShape(24.dp, 24.dp, 0.dp, 24.dp)
        } else {
            RoundedCornerShape(24.dp, 24.dp, 24.dp, 0.dp)
        },
        modifier = Modifier
            .clickable { onCardClick() }
    ) {
        Image(
            painter = painterResource(id = categoryItem.imageId),
            contentDescription = stringResource(id = categoryItem.titleId),
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
                .aspectRatio(1f),
            contentScale = ContentScale.Fit
        )
        Text(
            text = stringResource(id = categoryItem.titleId),
            style = TextStyle(Color.White, 20.sp, FontWeight.Normal),
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}