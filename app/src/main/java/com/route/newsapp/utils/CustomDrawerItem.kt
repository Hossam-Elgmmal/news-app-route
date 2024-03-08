package com.route.newsapp.utils

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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