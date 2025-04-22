package io.github.adefulki.spaceflightnews.domain.model

import com.google.gson.annotations.SerializedName

data class Author(
    @SerializedName("name") var name: String? = null,
    @SerializedName("socials") var socials: Social? = Social()
)
