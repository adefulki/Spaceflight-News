package io.github.adefulki.spaceflightnews.utils

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import io.github.adefulki.spaceflightnews.data.pref.UserPref
import io.github.adefulki.spaceflightnews.ui.MyActivity

class Waiter(
    private var period: Long,
    private var context: Context
) : Thread() {

    private val SLEEP_TIME:Long = 5 * 1000
    private var lastUsed: Long = 0
    private var stop = false


    override fun run() {
        var idle: Long = 0
        touch()
        do {
            idle = System.currentTimeMillis() - lastUsed
            Log.d(TAG, "Application is idle for $idle ms")
            try {
                sleep(SLEEP_TIME) //check every 5 seconds
            } catch (e: InterruptedException) {
                Log.d(TAG, "Waiter interrupted!")
            }
            if (idle > period) {
                idle = 0
                val handler = Handler(Looper.getMainLooper())
                handler.post {
                    moveToLogin()
                }
                stopp()
            }
        } while (!stop)
        Log.d(TAG, "Finishing Waiter thread")
    }

    private fun moveToLogin() {
        UserPref(context).remove()
        val intent = Intent(context, MyActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        context.startActivity(intent)
    }

    @Synchronized
    fun touch() {
        lastUsed = System.currentTimeMillis()
    }

    @Synchronized
    fun forceInterrupt() {
        interrupt()
    }

    //soft stopping of thread
    @Synchronized
    fun stopp() {
        stop = true
    }

    @Synchronized
    fun setPeriod(period: Long) {
        this.period = period
    }

    companion object {
        private val TAG = Waiter::class.java.name
    }
}