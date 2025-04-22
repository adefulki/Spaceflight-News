package io.github.adefulki.spaceflightnews.data.remote.datasource

import io.github.adefulki.spaceflightnews.data.datasource.RemoteBlogDatasource
import io.github.adefulki.spaceflightnews.data.remote.api.NewsApi
import io.github.adefulki.spaceflightnews.domain.model.Blog
import javax.inject.Inject

class RemoteBlogDatasourceImpl @Inject constructor(
    private val api: NewsApi
) : RemoteBlogDatasource {

    override suspend fun getAll(): ArrayList<Blog> {
        return api.getBlogs().resultsToBlogs()
    }
}