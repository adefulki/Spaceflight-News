package io.github.adefulki.spaceflightnews.data.datasource

import io.github.adefulki.spaceflightnews.data.remote.dto.BlogDto

interface RemoteBlogDatasource {
    suspend fun getBlogs(): BlogDto
}