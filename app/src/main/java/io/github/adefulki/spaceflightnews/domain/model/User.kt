package io.github.adefulki.spaceflightnews.domain.model

import java.util.Date

data class User (
    var id: String? = null,
    var name: String? = null,
    var nickname: String? = null,
    var pictureURL: String? = null,
    var email: String? = null,
    var isEmailVerified: Boolean? = null,
    var familyName: String? = null,
    var createdAt: Date? = null,
    var givenName: String? = null,
    var idToken: String? = null,
    var accessToken: String? = null,
    var refreshToken: String? = null,
    var recoveryCode: String? = null,
    var scope: String? = null,
    var expiresAt: Date? = null,
    var type: String? = null,
    var isLoggedIn: Boolean = false
)