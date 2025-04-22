package io.github.adefulki.spaceflightnews.ui.component

import androidx.compose.foundation.Image
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import io.github.adefulki.spaceflightnews.R

@Composable
fun Logo(
    modifier: Modifier = Modifier,
    lightTheme: Boolean = LocalContentColor.current.luminance() < 0.5f,
) {
    val assetId = if (lightTheme) {
        R.mipmap.ic_launcher
    } else {
        R.mipmap.ic_launcher
    }
    Image(
        painter = painterResource(id = assetId),
        modifier = modifier,
        contentDescription = stringResource(id = R.string.img_description)
    )
}