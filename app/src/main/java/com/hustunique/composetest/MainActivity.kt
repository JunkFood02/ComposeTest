package com.hustunique.composetest

import android.graphics.drawable.shapes.Shape
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.expandHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.hustunique.composetest.ui.theme.ComposeTestTheme
import com.hustunique.composetest.ui.theme.PingFangFamily
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    GreetingCard(getDate(), "Default Title")
                }
            }
        }
    }

    private fun getDate(): String {
        val simpleDateFormat = SimpleDateFormat("MM月dd日 E HH:mm", Locale.CHINA)
        return simpleDateFormat.format(Date())
    }
}

@Composable
fun GreetingCard(date: String, title: String) {
    Card(
        modifier = Modifier
            .padding(Dp(12F))
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(Dp(12F))
    ) {
        Column() {
            Image(
                painter = painterResource(id = R.drawable.bg_silent),
                contentDescription = "Title Picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(width = Dp.Unspecified, height = Dp(150F))
            )
            Column(
                modifier = Modifier.padding(Dp(12F))
            ) {
                Text(text = "# $date",fontFamily = PingFangFamily, fontWeight = FontWeight.Normal)
                Text(text = title,fontFamily = PingFangFamily, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeTestTheme {
        Greeting("Android")
    }
}