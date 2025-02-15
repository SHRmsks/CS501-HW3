
package com.example.hw31
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.hw31.ui.theme.HW31Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val dataset =
                listOf(
                    PhotoType(R.drawable.pic1, "pic1"),
                    PhotoType(R.drawable.pic2, "pic2"),
                    PhotoType(R.drawable.pic3, "pic3"),
                    PhotoType(R.drawable.pic4, "pic4"),
                    PhotoType(R.drawable.pic5, "pic5"),
                    PhotoType(R.drawable.pic6, "pic6"),
                    PhotoType(R.drawable.pic7, "pic7"),
                    PhotoType(R.drawable.pic8, "pic8"),
                    PhotoType(R.drawable.pic9, "pic9"),
                    PhotoType(R.drawable.pic10, "pic10"),
                    PhotoType(R.drawable.pic11, "pic11"),
                    PhotoType(R.drawable.pic12, "pic12"),
                    PhotoType(R.drawable.pic13, "pic13"),
                    PhotoType(R.drawable.pic14, "pic14"),
                    PhotoType(R.drawable.pic15, "pic15"),
                    PhotoType(R.drawable.pic16, "pic16"),
                    PhotoType(R.drawable.pic17, "pic17"),
                    PhotoType(R.drawable.pic18, "pic18"),
                    PhotoType(R.drawable.pic19, "pic19"),
                    PhotoType(R.drawable.pic20, "pic20"),
                )
            HW31Theme {
                imageLoader(imgurl = dataset)
            }
        }
    }
}

data class PhotoType(
    val url: Int,
    val title: String,
)

@Composable
fun imgExhibitor(
    url: Int,
    title: String,

) {
    var clicked by remember { mutableStateOf(false) }
    var ini by remember { mutableStateOf(true) }
    val scale = remember { Animatable(1f) }
    val idx = remember { mutableFloatStateOf(0f) }
    Box(
        modifier =
            Modifier
                .clickable {
                    clicked = !clicked
                    ini = false

                },
    ) {
        LaunchedEffect(ini, clicked) {
            if (clicked && !ini) {
                scale.animateTo(2f, tween(durationMillis = 100, easing = FastOutSlowInEasing))
                idx.value = 2f
            } else {
                scale.animateTo(1f, tween(durationMillis = 100, easing = FastOutSlowInEasing))
                idx.value = 0f
            }
        }
        Image(
            modifier = Modifier.zIndex(idx.value).scale(scale.value),
            painter = painterResource(id = url),
            contentDescription = title,
            contentScale = ContentScale.Fit,
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun imageLoader(imgurl: List<PhotoType>) {

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Row(
            modifier =
                Modifier.fillMaxSize(0.9f),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Adaptive(minSize = 150.dp),
                verticalItemSpacing = 5.dp,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                items(imgurl) { item ->
                    val itemURL = item.url
                    val itemTitle = item.title
                    imgExhibitor(title = itemTitle, url = itemURL, )
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun test() {
    val dataset =
        listOf(
            PhotoType(R.drawable.pic1, "pic1"),
            PhotoType(R.drawable.pic2, "pic2"),
            PhotoType(R.drawable.pic3, "pic3"),
            PhotoType(R.drawable.pic4, "pic4"),
            PhotoType(R.drawable.pic5, "pic5"),
            PhotoType(R.drawable.pic6, "pic6"),
            PhotoType(R.drawable.pic7, "pic7"),
            PhotoType(R.drawable.pic8, "pic8"),
            PhotoType(R.drawable.pic9, "pic9"),
            PhotoType(R.drawable.pic10, "pic10"),
            PhotoType(R.drawable.pic11, "pic11"),
            PhotoType(R.drawable.pic12, "pic12"),
            PhotoType(R.drawable.pic13, "pic13"),
            PhotoType(R.drawable.pic14, "pic14"),
            PhotoType(R.drawable.pic15, "pic15"),
            PhotoType(R.drawable.pic16, "pic16"),
            PhotoType(R.drawable.pic17, "pic17"),
            PhotoType(R.drawable.pic18, "pic18"),
            PhotoType(R.drawable.pic19, "pic19"),
            PhotoType(R.drawable.pic20, "pic20"),
        )
    HW31Theme {
        imageLoader(imgurl = dataset)
    }
}
