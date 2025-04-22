package io.github.adefulki.spaceflightnews.ui.screen.home

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.callback.Callback
import io.github.adefulki.spaceflightnews.R
import io.github.adefulki.spaceflightnews.base.BaseResult
import io.github.adefulki.spaceflightnews.data.pref.UserPref
import io.github.adefulki.spaceflightnews.domain.model.Article
import io.github.adefulki.spaceflightnews.domain.model.Blog
import io.github.adefulki.spaceflightnews.domain.model.Report
import io.github.adefulki.spaceflightnews.ui.component.AppBar
import io.github.adefulki.spaceflightnews.ui.component.AppDrawerContent
import io.github.adefulki.spaceflightnews.ui.component.Loading
import io.github.adefulki.spaceflightnews.utils.orZero
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeScreen(
    onLogout: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val eventFlow = viewModel.eventFlow
    val logoutSuccessStr = stringResource(id = R.string.logout_success)
    val logoutFailedStr = stringResource(id = R.string.logout_failed)
    val drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    LaunchedEffect(key1 = true) {
        eventFlow.collectLatest { event ->
            when (event) {
                is HomeViewModel.UIEvent.ResultBuildLogout -> {
                    event.builder.start(context, object : Callback<Void?, AuthenticationException> {
                        override fun onFailure(exception: AuthenticationException) {
                            Toast.makeText(context, logoutFailedStr, Toast.LENGTH_SHORT).show()
                        }

                        override fun onSuccess(result: Void?) {
                            UserPref(context).remove()
                            Toast.makeText(context, logoutSuccessStr, Toast.LENGTH_SHORT).show()
                            viewModel.showLogoutNotification()
                            onLogout()
                        }
                    })
                }
            }
        }
    }

    Surface {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                AppDrawerContent(
                    drawerState = drawerState,
                    onLogout = {
                        viewModel.logoutWithBrowser()
                    }
                )
            }
        ) {
            Scaffold(
                topBar = {
                    AppBar(
                        drawerState = drawerState,
                        title = R.string.title_home
                    )
                }
            ) {
                viewModel.getArticles()
                viewModel.getBlogs()
                viewModel.getReports()
                UserHomeScreen(paddingValues = it, viewModel = viewModel)
            }
        }
    }
}

@Composable
fun UserHomeScreen(
    paddingValues: PaddingValues,
    viewModel: HomeViewModel
) {
    val context = LocalContext.current
    val articles: BaseResult<ArrayList<Article>> by viewModel.articles.collectAsState()
    val blogs: BaseResult<ArrayList<Blog>> by viewModel.blogs.collectAsState()
    val reports: BaseResult<ArrayList<Report>> by viewModel.reports.collectAsState()

    Column(modifier = Modifier.padding(paddingValues)) {
        Text(
            text = getGreeting(),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Text(
            text = UserPref(context).user?.name.orEmpty(),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        when (articles) {
            is BaseResult.Loading -> {
                Loading(true)
            }

            is BaseResult.Error -> {
                val error = (articles as BaseResult.Error).message
                Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
            }

            is BaseResult.Success -> {
                val articleData = (articles as BaseResult.Success).data
                if (articleData?.isEmpty() == true) {
                    Toast.makeText(context, "No Data", Toast.LENGTH_SHORT).show()
                } else {

                    val pagerState = rememberPagerState(
                        initialPageOffsetFraction = 0f,
                        initialPage = 0,
                        pageCount = { articleData?.size.orZero() }
                    )


                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Articles", modifier = Modifier.padding(horizontal = 16.dp))
                        Text(text = "See More", modifier = Modifier.padding(horizontal = 16.dp))
                    }
                    HorizontalPager(
                        state = pagerState,
                        modifier = Modifier.padding(horizontal = 8.dp),
                        contentPadding = PaddingValues(end = 250.dp),
                    ) { page ->
                        // Our page content - Main card with image
                        Card(
                            modifier = Modifier
                                .padding(8.dp),
                            shape = RoundedCornerShape(12.dp),
                        ) {
                            Box {
                                AsyncImage(
                                    modifier = Modifier.size(150.dp),
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(articleData?.get(page)?.imageUrl.orEmpty())
                                        .crossfade(true)
                                        .build(),
                                    contentScale = ContentScale.Crop,
                                    contentDescription = "Game Image"
                                )
                            }
                        }
                    }

                }
            }
        }
        when (blogs) {
            is BaseResult.Loading -> {
                Loading(true)
            }

            is BaseResult.Error -> {
                val error = (blogs as BaseResult.Error).message
                Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
            }

            is BaseResult.Success -> {
                val blogData = (blogs as BaseResult.Success).data
                if (blogData?.isEmpty() == true) {
                    Toast.makeText(context, "No Data", Toast.LENGTH_SHORT).show()
                } else {

                    val pagerState = rememberPagerState(
                        initialPageOffsetFraction = 0f,
                        initialPage = 0,
                        pageCount = { blogData?.size.orZero() }
                    )


                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Blogs", modifier = Modifier.padding(horizontal = 16.dp))
                        Text(text = "See More", modifier = Modifier.padding(horizontal = 16.dp))
                    }
                    HorizontalPager(
                        state = pagerState,
                        modifier = Modifier.padding(horizontal = 8.dp),
                        contentPadding = PaddingValues(end = 250.dp),
                    ) { page ->
                        // Our page content - Main card with image
                        Card(
                            modifier = Modifier
                                .padding(8.dp),
                            shape = RoundedCornerShape(12.dp),
                        ) {
                            Box {
                                AsyncImage(
                                    modifier = Modifier.size(150.dp),
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(blogData?.get(page)?.imageUrl.orEmpty())
                                        .crossfade(true)
                                        .build(),
                                    contentScale = ContentScale.Crop,
                                    contentDescription = "Game Image"
                                )
                            }
                        }
                    }

                }
            }
        }
        when (reports) {
            is BaseResult.Loading -> {
                Loading(true)
            }

            is BaseResult.Error -> {
                val error = (reports as BaseResult.Error).message
                Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
            }

            is BaseResult.Success -> {
                val reportData = (reports as BaseResult.Success).data
                if (reportData?.isEmpty() == true) {
                    Toast.makeText(context, "No Data", Toast.LENGTH_SHORT).show()
                } else {

                    val pagerState = rememberPagerState(
                        initialPageOffsetFraction = 0f,
                        initialPage = 0,
                        pageCount = { reportData?.size.orZero() }
                    )


                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Reports", modifier = Modifier.padding(horizontal = 16.dp))
                        Text(text = "See More", modifier = Modifier.padding(horizontal = 16.dp))
                    }
                    HorizontalPager(
                        state = pagerState,
                        modifier = Modifier.padding(horizontal = 8.dp),
                        contentPadding = PaddingValues(end = 250.dp),
                    ) { page ->
                        // Our page content - Main card with image
                        Card(
                            modifier = Modifier
                                .padding(8.dp),
                            shape = RoundedCornerShape(12.dp),
                        ) {
                            Box {
                                AsyncImage(
                                    modifier = Modifier.size(150.dp),
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(reportData?.get(page)?.imageUrl.orEmpty())
                                        .crossfade(true)
                                        .build(),
                                    contentScale = ContentScale.Crop,
                                    contentDescription = "Game Image"
                                )
                            }
                        }
                    }

                }
            }
        }
    }
}

fun getGreeting(): String {
    val hour = java.util.Calendar.getInstance().get(java.util.Calendar.HOUR_OF_DAY)
    return when (hour) {
        in 0..11 -> "Good Morning"
        in 12..17 -> "Good Afternoon"
        else -> "Good Evening"
    }
}