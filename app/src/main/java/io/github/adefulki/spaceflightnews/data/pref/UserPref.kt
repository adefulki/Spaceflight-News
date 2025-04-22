package io.github.adefulki.spaceflightnews.data.pref

import android.content.Context
import android.util.Base64
import com.google.gson.Gson
import io.github.adefulki.spaceflightnews.base.BaseSharedPreferences
import io.github.adefulki.spaceflightnews.domain.model.User

class UserPref(context: Context) : BaseSharedPreferences(context) {

    var user: User?
        get() {
            val json = getString(KEY, "")
            return Gson().fromJson(json, User::class.java)?.apply {
                this.accessToken = Base64.decode(this.accessToken, Base64.DEFAULT).decodeToString()
                if (!this.refreshToken.isNullOrEmpty()) this.refreshToken = Base64.decode(this.refreshToken, Base64.DEFAULT).decodeToString()
                if (!this.recoveryCode.isNullOrEmpty()) this.recoveryCode = Base64.decode(this.recoveryCode, Base64.DEFAULT).decodeToString()
            }
        }
        set(value) {
            value?.apply {
                this.accessToken = Base64.encodeToString(this.accessToken.toByteArray(), Base64.DEFAULT)
                if (!this.refreshToken.isNullOrEmpty()) this.refreshToken = Base64.encodeToString(this.refreshToken?.toByteArray(), Base64.DEFAULT)
                if (!this.recoveryCode.isNullOrEmpty()) this.recoveryCode = Base64.encodeToString(this.recoveryCode?.toByteArray(), Base64.DEFAULT)
            }
            val json = Gson().toJson(value)
            set(KEY, json)
        }

    fun remove() {
        remove(KEY)
    }

    companion object {
        private const val KEY = "USER_KEY"
    }
}