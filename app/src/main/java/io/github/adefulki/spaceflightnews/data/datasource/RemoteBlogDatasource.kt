package io.github.adefulki.spaceflightnews.data.datasource

import io.github.adefulki.spaceflightnews.domain.model.Blog

interface RemoteBlogDatasource {
    suspend fun getAll(): ArrayList<Blog>
}