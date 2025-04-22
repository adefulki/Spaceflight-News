package io.github.adefulki.spaceflightnews.ui.screen.home

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.callback.Callback
import io.github.adefulki.spaceflightnews.R
import io.github.adefulki.spaceflightnews.data.pref.UserPref
import io.github.adefulki.spaceflightnews.ui.component.AppBar
import io.github.adefulki.spaceflightnews.ui.component.AppDrawerContent
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
    LazyColumn(
        modifier = Modifier.padding(paddingValues)
    ) {

    }
}