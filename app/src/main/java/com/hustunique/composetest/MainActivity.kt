package com.hustunique.composetest

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.hustunique.composetest.MainActivity.Companion.getDate
import com.hustunique.composetest.ui.theme.ComposeTestTheme
import com.hustunique.composetest.ui.theme.DateTextStyle
import com.hustunique.composetest.ui.theme.TitleTextStyle
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.launch
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
                    val musicDiaryList = remember { mutableStateListOf<MusicDairyItem>() }
                    val listState = rememberLazyListState();
                    val coroutineScope = rememberCoroutineScope()
                    musicDiaryList.add(MusicDairyItem("菜狗 1", getDate(), R.drawable.bg_silent))
                    musicDiaryList.add(MusicDairyItem("菜狗 2", getDate(), R.drawable.bg_groove))
                    musicDiaryList.add(MusicDairyItem("菜狗 3", getDate(), R.drawable.bg_hopeful))
                    musicDiaryList.add(MusicDairyItem("菜狗 4", getDate(), R.drawable.bg_passion))
                    musicDiaryList.add(MusicDairyItem("菜狗 5", getDate(), R.drawable.bg_romance))
                    LazyColumn(state = listState) {
                        itemsIndexed(musicDiaryList) { _, item ->
                            GreetingCard(
                                date = item.date,
                                title = item.title,
                                resID = item.resId, {
                                    makeToast(item.title)
                                }, {
                                    makeToast(item.title + "(long click)")
                                }
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(36.dp),
                        contentAlignment = Alignment.BottomEnd
                    ) {

                        FAB() {
                            musicDiaryList.add(
                                MusicDairyItem(
                                    "新菜狗",
                                    getDate(),
                                    R.drawable.bg_passion
                                )
                            )
                            coroutineScope.launch {
                                listState.animateScrollToItem(index = musicDiaryList.size - 1)
                            }
                        }
                    }
                }
            }
        }
    }


    private fun makeToast(title: String) {
        Toast.makeText(this, "$title was clicked.", Toast.LENGTH_SHORT).show();
    }


    companion object {
        fun getDate(): String {
            val simpleDateFormat = SimpleDateFormat("MM月dd日 E HH:mm", Locale.CHINA)
            return simpleDateFormat.format(Date())
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun GreetingCard(
    date: String = getDate(),
    title: String = "菜狗",
    resID: Int = R.drawable.bg_romance,
    onClick: () -> Unit = {},
    onLongClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        elevation = 12.dp,
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            Modifier.combinedClickable(
                enabled = true,
                null,
                onLongClick = onLongClick,
                onClick = onClick
            )
        ) {
            Image(
                painter = rememberImagePainter(resID), contentDescription = "Title Picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(width = Dp.Unspecified, height = 150.dp)
            )
            Column(
                modifier = Modifier.padding(12.dp, 9.dp)
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
fun FAB(onClick: () -> Unit) {
    FloatingActionButton(
        modifier = Modifier
            .size(64.dp), onClick = onClick
    ) {
        Icon(
            painter = painterResource(id = R.drawable.outline_flutter_dash_24),
            contentDescription = "add new item"
        )
    }
}
