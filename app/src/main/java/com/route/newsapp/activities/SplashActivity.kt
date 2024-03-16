package com.route.newsapp.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.route.newsapp.R
import com.route.newsapp.ui.theme.NewsAppTheme


@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppTheme {
                SplashScreen()
            }
        }
        Handler(mainLooper).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }
}

@Composable
fun SplashScreen() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.image_pattern),
                contentScale = ContentScale.FillBounds
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.image_splash),
            contentDescription = stringResource(R.string.news_app),
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(0.5f)
        )
        Image(
            painter = painterResource(id = R.drawable.image_signature),
            contentDescription = stringResource(R.string.signature),
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(0.5f)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SplashScreenPreview() {
    NewsAppTheme {
        SplashScreen()
    }
}