package io.github.adefulki.spaceflightnews.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.auth0.android.Auth0
import com.auth0.android.provider.WebAuthProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val auth0: Auth0,
    @Named("AUTH0-SCHEME") private val auth0Scheme: String
) : ViewModel() {

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun logoutWithBrowser() = viewModelScope.launch {
        val builder = WebAuthProvider.logout(auth0)
            .withScheme(auth0Scheme)
        _eventFlow.emit(UIEvent.ResultBuildLogout(builder))
    }

    sealed class UIEvent {
        data class ResultBuildLogout(val builder: WebAuthProvider.LogoutBuilder) : UIEvent()
    }
}