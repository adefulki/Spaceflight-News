package io.github.adefulki.spaceflightnews.data.datasource

import io.github.adefulki.spaceflightnews.domain.model.RecentSearch

interface LocalRecentSearchDatasource {
    suspend fun insert(keyword: String)
    suspend fun getAll(): ArrayList<RecentSearch>
}