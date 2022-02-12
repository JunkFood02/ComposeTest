package com.hustunique.composetest

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.hustunique.composetest.ui.theme.ComposeTestTheme
import com.hustunique.composetest.ui.theme.DateTextStyle
import com.hustunique.composetest.ui.theme.TitleTextStyle
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
                    LazyColumn {
                        items(5) { index ->
                            val title = "菜狗 $index"
                            GreetingCard(getDate(), title) { makeToast(title) }
                        }
                    }
                }
            }
        }
    }

    private fun getDate(): String {
        val simpleDateFormat = SimpleDateFormat("MM月dd日 E HH:mm", Locale.CHINA)
        return simpleDateFormat.format(Date())
    }

    private fun makeToast(title: String) {
        Toast.makeText(this, "$title was clicked.", Toast.LENGTH_SHORT).show();
    }
}

@Composable
fun GreetingCard(date: String, title: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(Dp(12F))
            .fillMaxWidth()
            .wrapContentHeight(),
        elevation = Dp(12F),
        shape = RoundedCornerShape(Dp(12F))
    ) {
        Column(modifier = Modifier.clickable(onClick = onClick)) {
            Image(
                painter = painterResource(id = R.drawable.bg_silent),
                contentDescription = "Title Picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(width = Dp.Unspecified, height = Dp(150F))
            )
            Column(
                modifier = Modifier.padding(Dp(12F), Dp(9F))
            ) {
                Text(
                    text = "# $date",
                    style = DateTextStyle
                )
                Text(
                    text = title,
                    style = TitleTextStyle
                )
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