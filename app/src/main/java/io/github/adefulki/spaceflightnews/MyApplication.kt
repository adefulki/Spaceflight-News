package io.github.adefulki.spaceflightnews

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkManager
import androidx.work.WorkRequest
import dagger.hilt.android.HiltAndroidApp
import io.github.adefulki.spaceflightnews.data.pref.UserPref
import io.github.adefulki.spaceflightnews.ui.MyActivity
import io.github.adefulki.spaceflightnews.utils.IdleWorker
import io.github.adefulki.spaceflightnews.utils.Waiter
import java.util.Date
import java.util.UUID
import javax.inject.Inject


@HiltAndroidApp
class MyApplication : Application() {

    @Inject
    lateinit var notificationBuilder: NotificationCompat.Builder
    @Inject
    lateinit var notificationManager: NotificationManagerCompat

    var id: UUID? = null
    var worker: WorkManager? = null

    override fun onCreate() {
        super.onCreate()
        val exp = UserPref(this).user?.expiresAt
        if (id == null && exp != null) {
            if (Date() < exp) {
                startIdleWorker()
            } else {
                moveToLogin()
            }
        }
    }

    fun startIdleWorker() {
        val uploadWorkRequest: WorkRequest = OneTimeWorkRequestBuilder<IdleWorker>()
            .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
            .build()
        id = uploadWorkRequest.id
        worker = WorkManager.getInstance(this)
        worker?.enqueue(uploadWorkRequest)
    }

    private fun moveToLogin() {
        showLogoutNotification()
        UserPref(this).remove()
        val intent = Intent(this, MyActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        this.startActivity(intent)
    }

    private fun showLogoutNotification() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        notificationManager.notify(1, notificationBuilder.build())
    }
}