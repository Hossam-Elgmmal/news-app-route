package com.route.newsapp.screens.categorycontent

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.route.newsapp.R
import com.route.newsapp.ui.theme.Green


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SearchBox(onSearch: (String) -> Unit = {}, onCloseClick: () -> Unit = {}) {

    var textValue by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current
    val view = LocalView.current
    val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager

    val focusRequester = remember {
        FocusRequester()
    }
    LaunchedEffect(key1 = Unit) {
        focusRequester.requestFocus()
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp, 4.dp)
    ) {
        OutlinedTextField(
            value = textValue,
            onValueChange = {
                textValue = it
            },
            placeholder = { Text(text = stringResource(id = R.string.search)) },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = stringResource(id = R.string.search),
                    tint = Green
                )
            },
            trailingIcon = {
                IconButton(onClick = onCloseClick) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_close),
                        contentDescription = stringResource(R.string.close),
                        tint = Color.Red
                    )
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    inputManager?.hideSoftInputFromWindow(view.windowToken, 0)
                    onSearch(textValue)
                }
            ),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                unfocusedTextColor = Green,
                unfocusedIndicatorColor = Green,
                unfocusedTrailingIconColor = Green,
                focusedContainerColor = Color.White,
                focusedTextColor = Color.Black,
                focusedIndicatorColor = Green,
                focusedTrailingIconColor = Green,
                focusedLabelColor = Green,
                cursorColor = Color.DarkGray,
                focusedPlaceholderColor = Color.LightGray,
                unfocusedPlaceholderColor = Color.LightGray

            ),
            maxLines = 1,
            shape = CircleShape,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .fillMaxWidth(0.9f)
                .focusRequester(focusRequester)

        )
    }

}