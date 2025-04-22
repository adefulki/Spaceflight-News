package io.github.adefulki.spaceflightnews.domain.model

import com.google.gson.annotations.SerializedName

data class Event(
    @SerializedName("event_id") var eventId: Int? = null,
    @SerializedName("provider") var provider: String? = null
)