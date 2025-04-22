package io.github.adefulki.spaceflightnews

import android.app.Application
import android.content.Context
import android.content.Intent
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


@HiltAndroidApp
class MyApplication : Application() {

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
        UserPref(this).remove()
        val intent = Intent(this, MyActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        this.startActivity(intent)
    }
}