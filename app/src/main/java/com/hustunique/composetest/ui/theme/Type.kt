package com.hustunique.composetest.ui.theme

import androidx.compose.material.Text
import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.hustunique.composetest.R

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)

val PingFangFamily = FontFamily(
    Font(R.font.pingfang_regular, FontWeight.Normal),
    Font(R.font.pingfang_semibold, FontWeight.SemiBold),
    Font(R.font.pingfang_bold, FontWeight.Bold)

)
val TitleTextStyle =
    TextStyle(fontFamily = PingFangFamily, fontWeight = FontWeight.Bold, fontSize = 15.sp)

val DateTextStyle =
    TextStyle(fontFamily = PingFangFamily, fontWeight = FontWeight.Normal, fontSize = 12.sp)