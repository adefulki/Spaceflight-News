package io.github.adefulki.spaceflightnews.utils

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class IdleWorker(val appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {

    private val TIMEOUT = (1 * 60 * 1000).toLong() //min * sec * 1000

    override fun doWork(): Result {
        val waiter = Waiter(TIMEOUT, appContext)
        waiter.start()

        return Result.success()
    }
}