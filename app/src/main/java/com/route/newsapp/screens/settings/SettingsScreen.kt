package com.route.newsapp.screens.settings

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.LocaleListCompat
import com.route.newsapp.R
import com.route.newsapp.ui.theme.Green

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SettingsScreen(onNavigationIconClick: () -> Unit = {}) {
    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current

    val langCode by remember {
        mutableStateOf(
            context.getString(
                R.string.lang_code
            )
        )
    }
    Scaffold(
        topBar = {
            SettingsAppBar {

            }
        },
        containerColor = Color.Transparent
    ) {


        Column(
            modifier = Modifier
                .padding(top = it.calculateTopPadding())
                .fillMaxSize()
                .padding(24.dp)
        ) {
            Text(
                text = stringResource(id = R.string.language), style = TextStyle(
                    Color.Black, 22.sp,
                    FontWeight.Bold
                ), modifier = Modifier.padding(16.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp)
            ) {
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = {
                        expanded = !expanded
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(0.dp)
                ) {
                    OutlinedTextField(
                        value = stringResource(id = R.string.chosen_lang),
                        onValueChange = {},
                        readOnly = true,
                        shape = RectangleShape,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth(),
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = White,
                            unfocusedTextColor = Green,
                            unfocusedIndicatorColor = Green,
                            unfocusedTrailingIconColor = Green,
                            focusedContainerColor = White,
                            focusedTextColor = Green,
                            focusedIndicatorColor = Green,
                            focusedTrailingIconColor = Green,

                            )
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier.background(White)
                    ) {

                        DropdownMenuItem(
                            text = { Text(text = stringResource(id = R.string.lang)) },
                            onClick = {
                                expanded = false
                                AppCompatDelegate.setApplicationLocales(
                                    LocaleListCompat.forLanguageTags(langCode)
                                )
                            }
                        )

                    }
                }
            }
        }
    }
}