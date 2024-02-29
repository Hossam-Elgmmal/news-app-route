package com.route.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.route.newsapp.model.NewsData
import com.route.newsapp.ui.theme.Green
import com.route.newsapp.ui.theme.NewsAppTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppTheme {

                MainContent()

            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainContent() {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(drawerState = drawerState,
        drawerContent = {
            DrawerSheet()
        }
    ) {
        Scaffold(
            topBar = {
                NewsAppBar {
                    scope.launch {
                        drawerState.open()
                    }
                }
            })
        {
            NewsContent(Modifier.padding(top = it.calculateTopPadding()))
        }
    }


}

//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NewsContent(modifier: Modifier = Modifier) {

    val selectedIndex = remember {
        mutableIntStateOf(0)
    }
    val newsList = listOf(
        "ABC News",
        "ABC News",
        "ABC News",
        "ABC News",
    )
    Column(
        modifier = modifier
            .fillMaxSize()
            .paint(painterResource(id = R.drawable.image_pattern))
    ) {
        ScrollableTabRow(
            selectedTabIndex = selectedIndex.intValue,
            divider = { },
            indicator = {},
            edgePadding = 8.dp
        ) {
            newsList.forEachIndexed { index, item ->

                Tab(
                    selected = selectedIndex.intValue == index,
                    onClick = {
                        selectedIndex.intValue = index
                    },
                    selectedContentColor = Color.White,
                    unselectedContentColor = Green,

                    ) {
                    Text(
                        text = item,
                        modifier = if (selectedIndex.intValue == index)
                            Modifier
                                .padding(8.dp, 16.dp)
                                .background(Green, CircleShape)
                                .padding(16.dp, 8.dp)
                        else
                            Modifier
                                .padding(8.dp, 16.dp)
                                .border(2.dp, Green, CircleShape)
                                .background(Color.White, CircleShape)
                                .padding(16.dp, 8.dp)
                    )
                }
            }
        }
        AllCards()
    }
}

@Composable
fun AllCards() {
    val newsList = mutableListOf(
        NewsData(),
        NewsData(),
        NewsData()
    )

    LazyColumn {

        items(newsList.size) {
            NewsCard(newsList[it])
        }
    }
}

//@Preview(showBackground = true, showSystemUi = true)
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

//@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun DrawerSheet() {
    ModalDrawerSheet(
        modifier = Modifier
            .fillMaxWidth(0.65f)
    ) {
        Text(
            text = stringResource(R.string.drawer_news_app),
            style = TextStyle(
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .background(Green)
                .padding(0.dp, 24.dp)
                .fillMaxWidth()
        )
        CustomTextButton(R.drawable.ic_categories, R.string.categories) {}
        CustomTextButton(R.drawable.ic_settings, R.string.settings) {}
    }
}

@Composable
fun CustomTextButton(iconId: Int, textId: Int, onClick: () -> Unit) {
    TextButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 0.dp)
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = "settings", tint = Color.Black
        )
        Spacer(modifier = Modifier.padding(4.dp))
        Text(
            text = stringResource(textId),
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        )
        Spacer(modifier = Modifier.fillMaxWidth())
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun NewsAppBar(onNavigationIconClick: () -> Unit) {
    CenterAlignedTopAppBar(
        title = { Text(text = "News App", color = Color.White) },
        navigationIcon = {
            IconButton(onClick = { onNavigationIconClick() }) {
                Icon(
                    painterResource(id = R.drawable.ic_menu),
                    "menu", tint = Color.White
                )
            }
        }, colors = TopAppBarDefaults.topAppBarColors(Green),
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    painterResource(id = R.drawable.ic_search),
                    "search", tint = Color.White
                )
            }
        },
        modifier = Modifier
            .clip(shape = RoundedCornerShape(bottomEnd = 50.dp, bottomStart = 50.dp))

    )
}