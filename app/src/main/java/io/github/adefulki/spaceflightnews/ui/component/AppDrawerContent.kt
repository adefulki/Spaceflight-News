package io.github.adefulki.spaceflightnews.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.github.adefulki.spaceflightnews.R
import io.github.adefulki.spaceflightnews.data.pref.UserPref
import io.github.adefulki.spaceflightnews.domain.model.User

@Composable
fun AppDrawerContent(
    drawerState: DrawerState,
    onLogout: () -> Unit
) {
    val context = LocalContext.current

    ModalDrawerSheet {
        Surface(color = MaterialTheme.colorScheme.background) {
            Column(
                modifier = Modifier
                    .width(250.dp)
                    .padding(horizontal = 16.dp)
                    .padding(top = 50.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val userInfo = UserPref(context).user ?: User()
                ItemPhoto(
                    userInfo.pictureURL.orEmpty()
                ) { }
                Spacer(modifier = Modifier.height(25.dp))
                Text(
                    text = userInfo.email.orEmpty(),
                    style = MaterialTheme.typography.bodyMedium,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = userInfo.name.orEmpty(),
                    style = MaterialTheme.typography.bodyMedium,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = userInfo.nickname.orEmpty(),
                    style = MaterialTheme.typography.bodyMedium,
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedButton(
                    onClick = onLogout,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, bottom = 24.dp),
                ) {
                    Text(text = stringResource(id = R.string.logout))
                }
            }
        }
    }
}