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
    var idToken: String,
    var accessToken: String,
    var refreshToken: String? = null,
    var recoveryCode: String? = null,
    var scope: String? = null,
    var expiresAt: Date,
    var type: String
)