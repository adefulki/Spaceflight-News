package io.github.adefulki.spaceflightnews.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import io.github.adefulki.spaceflightnews.MyApplication
import io.github.adefulki.spaceflightnews.data.pref.UserPref

@AndroidEntryPoint
class MyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val defaultRoute =
            if (UserPref(context = this).user?.accessToken.isNullOrEmpty()) Screen.Welcome.route
            else Screen.Home.route
        setContent {
            ComposeApp(defaultRoute)
        }
    }

    fun startIdleWorker() {
        getApp().startIdleWorker()
    }

    private fun getApp(): MyApplication {
        return this.application as MyApplication
    }
}