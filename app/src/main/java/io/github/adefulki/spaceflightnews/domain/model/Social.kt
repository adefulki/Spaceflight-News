package io.github.adefulki.spaceflightnews.domain.model

import com.google.gson.annotations.SerializedName

data class Social(
    @SerializedName("x") var x: String? = null,
    @SerializedName("youtube") var youtube: String? = null,
    @SerializedName("instagram") var instagram: String? = null,
    @SerializedName("linkedin") var linkedin: String? = null,
    @SerializedName("mastodon") var mastodon: String? = null,
    @SerializedName("bluesky") var bluesky: String? = null
)