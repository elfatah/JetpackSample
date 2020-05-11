package com.example.jetpacksample.feature.news

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.getValue
import androidx.lifecycle.LiveData
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.AdapterList
import androidx.ui.foundation.Box
import androidx.ui.foundation.ContentGravity
import androidx.ui.foundation.Text
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.fillMaxSize
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.layout.wrapContentWidth
import androidx.ui.livedata.observeAsState
import androidx.ui.material.Card
import androidx.ui.material.CircularProgressIndicator
import androidx.ui.material.ListItem
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontFamily
import androidx.ui.text.font.FontWeight
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import com.example.jetpacksample.model.News
import com.example.jetpacksample.model.NewsItem
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val newsViewModel: NewsViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsViewModel.getNews("the-verge")
        setContent {
            LiveDataComponent(newsList = newsViewModel.newsLiveData)
        }
    }
}

@Composable
fun LiveDataComponent(newsList: LiveData<News>) {
    // Here we access the live data object and convert it to a form that Jetpack Compose
    // understands using the observeAsState method.

    // Reacting to state changes is the core behavior of Compose. We use the state composable
    // that is used for holding a state value in this composable for representing the current
    // value of the selectedIndex. Any composable that reads the value of counter will be recomposed
    // any time the value changes. This ensures that only the composables that depend on this
    // will be redraw while the rest remain unchanged. This ensures efficiency and is a
    // performance optimization. It is inspired from existing frameworks like React.
    val news by newsList.observeAsState()
    // Since Jetpack Compose uses the declarative way of programming, we can easily decide what
    // needs to shows vs hidden based on which branch of code is being executed. In this example,
    // if the personList returned by the live data is empty, we want to show a loading indicator,
    // otherwise we want show the appropriate list. So we run the appropriate composable based on
    // the branch of code executed and that takes care of rendering the right views.
    if (news == null) {
        LiveDataLoadingComponent()
    } else {
        LiveDataComponentList(news?.articles ?: emptyList())
    }
}

@Composable
fun LiveDataLoadingComponent() {
    // Box is a predefined convenience composable that allows you to apply common draw & layout
    // logic. In addition we also pass a few modifiers to it.

    // You can think of Modifiers as implementations of the decorators pattern that are
    // used to modify the composable that its applied to. In this example, we configure the
    // Box composable to occupy the entire available width and height using
    // Modifier.fillMaxSize() and give center gravity to the content inside this box.
    Box(modifier = Modifier.fillMaxSize(), gravity = ContentGravity.Center) {
        // A pre-defined composable that's capable of rendering a circular progress indicator. It
        // honors the Material Design specification.
        CircularProgressIndicator(modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally))
    }
}

@Composable
fun LiveDataComponentList(newsItemList: List<NewsItem>) {
    // AdapterList is a vertically scrolling list that only composes and lays out the currently
    // visible items. This is very similar to what RecylerView tries to do as it's more optimized
    // than the VerticalScroller.
    AdapterList(data = newsItemList) { newsItem ->
        // Card composable is a predefined composable that is meant to represent the
        // card surface as specified by the Material Design specification. We also
        // configure it to have rounded corners and apply a modifier.

        // You can think of Modifiers as implementations of the decorators pattern that are used to
        // modify the composable that its applied to. In this example, we assign a padding of
        // 16dp to the Card along with specifying it to occupy the entire available width.
        Card(
            shape = RoundedCornerShape(4.dp), color = Color.White,
            modifier = Modifier.fillMaxWidth() + Modifier.padding(8.dp)
        ) {
            // ListItem is a predefined composable that is a Material Design implementation of [list
            // items](https://material.io/components/lists). This component can be used to achieve the
            // list item templates existing in the spec
            ListItem(text = {
                // The Text composable is pre-defined by the Compose UI library; you can use this
                // composable to render text on the screen
                Text(
                    text = newsItem.title,
                    style = TextStyle(
                        fontFamily = FontFamily.Serif, fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }, secondaryText = {
                Text(
                    text = "Author: ${newsItem.author}",
                    style = TextStyle(
                        fontFamily = FontFamily.Serif, fontSize = 15.sp,
                        fontWeight = FontWeight.Light, color = Color.DarkGray
                    )
                )
            })
        }
    }
}

@Preview
@Composable
fun LiveDataComponentListPreview() {
    LiveDataComponentList(emptyList())
}