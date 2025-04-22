package io.github.adefulki.spaceflightnews.ui.screen.home

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.auth0.android.Auth0
import com.auth0.android.provider.WebAuthProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.adefulki.spaceflightnews.base.BaseResult
import io.github.adefulki.spaceflightnews.domain.model.Article
import io.github.adefulki.spaceflightnews.domain.model.Blog
import io.github.adefulki.spaceflightnews.domain.model.Report
import io.github.adefulki.spaceflightnews.domain.usecase.GetArticlesUseCase
import io.github.adefulki.spaceflightnews.domain.usecase.GetBlogsUseCase
import io.github.adefulki.spaceflightnews.domain.usecase.GetReportsUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class HomeViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val getArticlesUseCase: GetArticlesUseCase,
    private val getBlogsUseCase: GetBlogsUseCase,
    private val getReportsUseCase: GetReportsUseCase,
    private val auth0: Auth0,
    @Named("AUTH0-SCHEME") private val auth0Scheme: String,
    private val notificationBuilder: NotificationCompat.Builder,
    private val notificationManager: NotificationManagerCompat
) : ViewModel() {

    private val _articles: MutableStateFlow<BaseResult<ArrayList<Article>>> =
        MutableStateFlow(BaseResult.Loading(arrayListOf()))
    val articles: StateFlow<BaseResult<ArrayList<Article>>> get() = _articles

    private val _blogs: MutableStateFlow<BaseResult<ArrayList<Blog>>> =
        MutableStateFlow(BaseResult.Loading(arrayListOf()))
    val blogs: StateFlow<BaseResult<ArrayList<Blog>>> get() = _blogs

    private val _reports: MutableStateFlow<BaseResult<ArrayList<Report>>> =
        MutableStateFlow(BaseResult.Loading(arrayListOf()))
    val reports: StateFlow<BaseResult<ArrayList<Report>>> get() = _reports

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun getArticles() = viewModelScope.launch {
        _articles.value = BaseResult.Loading(arrayListOf())
        try {
            val result = getArticlesUseCase.execute(Unit)
            if (result.isNotEmpty()) {
                _articles.value = BaseResult.Success(result)
            } else {
                _articles.value = BaseResult.Error("No articles found")
            }
        } catch (e: Exception) {
            Log.e(TAG, "getArticles: ${e.message}", e)
            _articles.value = BaseResult.Error(e.localizedMessage ?: "Unknown error")
        }
    }

    fun getBlogs() = viewModelScope.launch {
        _blogs.value = BaseResult.Loading(arrayListOf())
        try {
            val result = getBlogsUseCase.execute(Unit)
            if (result.isNotEmpty()) {
                _blogs.value = BaseResult.Success(result)
            } else {
                _blogs.value = BaseResult.Error("No blogs found")
            }
        } catch (e: Exception) {
            _blogs.value = BaseResult.Error(e.localizedMessage ?: "Unknown error")
        }
    }

    fun getReports() = viewModelScope.launch {
        _reports.value = BaseResult.Loading(arrayListOf())
        try {
            val result = getReportsUseCase.execute(Unit)
            if (result.isNotEmpty()) {
                _reports.value = BaseResult.Success(result)
            } else {
                _reports.value = BaseResult.Error("No reports found")
            }
        } catch (e: Exception) {
            _reports.value = BaseResult.Error(e.localizedMessage ?: "Unknown error")
        }
    }

    fun logoutWithBrowser() = viewModelScope.launch {
        val builder = WebAuthProvider.logout(auth0)
            .withScheme(auth0Scheme)
        _eventFlow.emit(UIEvent.ResultBuildLogout(builder))
    }

    fun showLogoutNotification() {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        notificationManager.notify(1, notificationBuilder.build())
    }

    sealed class UIEvent {
        data class ResultBuildLogout(val builder: WebAuthProvider.LogoutBuilder) : UIEvent()
    }

    companion object {
        private const val TAG = "HomeViewModel"
    }
}