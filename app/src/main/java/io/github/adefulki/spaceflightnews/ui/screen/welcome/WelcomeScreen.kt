package io.github.adefulki.spaceflightnews.ui.screen.welcome

import android.content.Context
import android.content.ContextWrapper
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.callback.Callback
import com.auth0.android.result.Credentials
import com.auth0.android.result.UserProfile
import io.github.adefulki.spaceflightnews.R
import io.github.adefulki.spaceflightnews.data.pref.UserPref
import io.github.adefulki.spaceflightnews.domain.model.User
import io.github.adefulki.spaceflightnews.ui.MyActivity
import io.github.adefulki.spaceflightnews.utils.getActivity
import kotlinx.coroutines.flow.collectLatest

@Composable
fun WelcomeScreen(
    onLogInSuccess: () -> Unit,
    viewModel: WelcomeViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val eventFlow = viewModel.eventFlow
    val loginSuccessStr = stringResource(id = R.string.login_success)
    val loginFailedStr = stringResource(id = R.string.login_failed)
    val getProfileSuccessStr = stringResource(id = R.string.get_profile_success)
    val getProfileFailedStr = stringResource(id = R.string.get_profile_failed)

    LaunchedEffect(key1 = true) {
        eventFlow.collectLatest { event ->
            when (event) {
                is WelcomeViewModel.UIEvent.ResultBuildLoginBrowser -> {
                    event.builder.start(context, object :
                        Callback<Credentials, AuthenticationException> {
                        override fun onFailure(exception: AuthenticationException) {
                            Toast.makeText(context, loginFailedStr, Toast.LENGTH_SHORT).show()
                        }

                        override fun onSuccess(credentials: Credentials) {
                            UserPref(context).user = User(
                                idToken = credentials.idToken,
                                accessToken = credentials.accessToken,
                                refreshToken = credentials.refreshToken,
                                expiresAt = credentials.expiresAt,
                                scope = credentials.scope,
                                type = credentials.type
                            )
                            Toast.makeText(context, loginSuccessStr, Toast.LENGTH_SHORT).show()
                            viewModel.getUserProfile()
                        }
                    })
                }

                is WelcomeViewModel.UIEvent.ResultBuildGetUserProfile -> {
                    event.builder.userInfo(UserPref(context).user?.accessToken ?: "").start(
                        object : Callback<UserProfile, AuthenticationException> {
                            override fun onFailure(exception: AuthenticationException) {
                                Toast.makeText(context, getProfileFailedStr, Toast.LENGTH_SHORT)
                                    .show()
                            }

                            override fun onSuccess(result: UserProfile) {
                                val user = UserPref(context).user
                                UserPref(context).user = user?.apply {
                                    this.id = result.getId()
                                    this.name = result.name
                                    this.email = result.email
                                    this.nickname = result.nickname
                                    this.givenName = result.givenName
                                    this.familyName = result.familyName
                                    this.pictureURL = result.pictureURL
                                    this.createdAt = result.createdAt
                                    this.isEmailVerified = result.isEmailVerified
                                }
                                Toast.makeText(context, getProfileSuccessStr, Toast.LENGTH_SHORT)
                                    .show()
                                (context.getActivity() as MyActivity).startIdleWorker()
                                onLogInSuccess()
                            }
                        })
                }
            }
        }
    }

    Scaffold(modifier = Modifier) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedButton(
                onClick = { viewModel.loginWithBrowser() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 24.dp),
            ) {
                Text(text = stringResource(id = R.string.login))
            }
        }
    }
}