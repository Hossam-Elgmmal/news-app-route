package com.route.newsapp.utils

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.route.newsapp.R
import com.route.newsapp.ui.theme.Green

@Preview(showBackground = true, showSystemUi = true)
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun NewsAppBar(
    isSearchVisible: Boolean = false,
    title: String = "news_app",
    onSearchClick: () -> Unit = {},
    onNavigationIconClick: () -> Unit = {}
) {

    CenterAlignedTopAppBar(
        title = {
            AnimatedContent(
                targetState = title, label = "",
                transitionSpec = {
                    (slideInHorizontally(
                        initialOffsetX = { it },
                        animationSpec = tween(300)
                    ) + fadeIn(tween(300))
                            ).togetherWith(
                            slideOutHorizontally(
                                targetOffsetX = { it },
                                animationSpec = tween(300)
                            ) + fadeOut(tween(300))
                        )
                }
            ) {
                Text(text = it, color = Color.White)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(Green),
        navigationIcon = {
            IconButton(onClick = { onNavigationIconClick() }) {
                Icon(
                    painterResource(id = R.drawable.ic_menu),
                    "menu", tint = Color.White
                )
            }
        },
        actions = {
            AnimatedVisibility(
                visible = isSearchVisible,
                enter = slideInHorizontally(
                    tween(300)
                ) { it } + fadeIn(tween(300)),
                exit = slideOutHorizontally(
                    tween(300)
                ) { it } + fadeOut(tween(300))
            ) {

                IconButton(onClick = onSearchClick) {
                    Icon(
                        painterResource(id = R.drawable.ic_search),
                        "search", tint = Color.White
                    )
                }

            }
        },
        modifier = Modifier
            .clip(shape = RoundedCornerShape(bottomEnd = 50.dp, bottomStart = 50.dp))
    )
}
