package io.github.adefulki.spaceflightnews.ui.screen.welcome

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationAPIClient
import com.auth0.android.provider.WebAuthProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val auth0: Auth0,
    @Named("AUTH0-SCHEME") private val auth0Scheme: String,
    @Named("AUTH0-DOMAIN") private val auth0Domain: String
) : ViewModel() {

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun loginWithBrowser() = viewModelScope.launch {
        val builder = WebAuthProvider.login(auth0)
            .withScheme(auth0Scheme)
            .withScope("openid profile email read:current_user update:current_user_metadata")
            .withAudience("https://$auth0Domain/api/v2/")
        _eventFlow.emit(UIEvent.ResultBuildLoginBrowser(builder))
    }

    fun getUserProfile() = viewModelScope.launch {
        val builder = AuthenticationAPIClient(auth0)
        _eventFlow.emit(UIEvent.ResultBuildGetUserProfile(builder))
    }

    sealed class UIEvent {
        data class ResultBuildLoginBrowser(val builder: WebAuthProvider.Builder) : UIEvent()
        data class ResultBuildGetUserProfile(val builder: AuthenticationAPIClient) : UIEvent()
    }
}
