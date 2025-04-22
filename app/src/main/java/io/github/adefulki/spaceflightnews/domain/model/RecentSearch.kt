package io.github.adefulki.spaceflightnews.domain.model

import com.google.gson.annotations.SerializedName

data class RecentSearch (
    @SerializedName("id") var id: Int? = null,
    @SerializedName("keyword") var keyword: String? = null
)