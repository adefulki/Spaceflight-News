package io.github.adefulki.spaceflightnews.domain.model

import com.google.gson.annotations.SerializedName

data class Launch(
    @SerializedName("launch_id") var launchId: String? = null,
    @SerializedName("provider") var provider: String? = null
)