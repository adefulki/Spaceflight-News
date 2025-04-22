package io.github.adefulki.spaceflightnews.data.local.datasource

import io.github.adefulki.spaceflightnews.data.datasource.LocalBlogDatasource
import io.github.adefulki.spaceflightnews.data.datasource.LocalRecentSearchDatasource
import io.github.adefulki.spaceflightnews.data.local.dao.BlogDao
import io.github.adefulki.spaceflightnews.data.local.dao.RecentSearchDao
import io.github.adefulki.spaceflightnews.data.local.entity.RecentSearchEntity
import io.github.adefulki.spaceflightnews.domain.model.Blog
import io.github.adefulki.spaceflightnews.domain.model.RecentSearch
import io.github.adefulki.spaceflightnews.utils.toBlogEntities
import io.github.adefulki.spaceflightnews.utils.toBlogs
import io.github.adefulki.spaceflightnews.utils.toRecentSearch
import javax.inject.Inject

class LocalRecentSearchDatasourceImpl @Inject constructor(
    private val recentSearchDao: RecentSearchDao
) : LocalRecentSearchDatasource {

    override suspend fun insert(keyword: String) {
        recentSearchDao.insert(RecentSearchEntity(keyword = keyword))
    }

    override suspend fun getAll(): ArrayList<RecentSearch> {
        return recentSearchDao.findAll().toRecentSearch()
    }
}