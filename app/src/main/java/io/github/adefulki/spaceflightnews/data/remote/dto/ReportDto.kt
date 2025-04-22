package io.github.adefulki.spaceflightnews.data.remote.dto

import com.google.gson.annotations.SerializedName
import io.github.adefulki.spaceflightnews.domain.model.Results

data class ReportDto(
    @SerializedName("count") var count: Int? = null,
    @SerializedName("next") var next: String? = null,
    @SerializedName("previous") var previous: String? = null,
    @SerializedName("results") var results: ArrayList<Results> = arrayListOf()
)