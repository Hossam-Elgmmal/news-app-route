package com.route.newsapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.route.newsapp.R
import com.route.newsapp.ui.theme.Green

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DrawerSheet(onCategoriesClick: () -> Unit = {}, onSettingsClick: () -> Unit = {}) {
    ModalDrawerSheet(
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.systemBars)
            .fillMaxWidth(0.65f),
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
                .windowInsetsPadding(WindowInsets.statusBars)
                .fillMaxWidth()
        )
        CustomDrawerItem(R.drawable.ic_categories, R.string.categories, onCategoriesClick)
        CustomDrawerItem(R.drawable.ic_settings, R.string.settings, onSettingsClick)
    }
}

@Composable
fun CustomDrawerItem(iconId: Int, textId: Int, onClick: () -> Unit) {
    NavigationDrawerItem(
        label = {
            Text(
                text = stringResource(id = textId), style = TextStyle(
                    Color.Black, 18.sp,
                    FontWeight.Bold
                )
            )
        },
        icon = {
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = stringResource(id = textId),
                tint = Color.Black
            )
        },
        shape = RoundedCornerShape(16.dp),
        selected = false,
        onClick = onClick
    )
}