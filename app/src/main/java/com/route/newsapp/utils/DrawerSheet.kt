package com.route.newsapp.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
            .fillMaxWidth(0.45f)
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
        CustomDrawerItem(R.drawable.ic_categories, R.string.categories, onCategoriesClick)
        CustomDrawerItem(R.drawable.ic_settings, R.string.settings, onSettingsClick)
    }
}