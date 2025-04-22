package io.github.adefulki.spaceflightnews.data.local.datasource

import io.github.adefulki.spaceflightnews.data.datasource.LocalBlogDatasource
import io.github.adefulki.spaceflightnews.data.local.dao.BlogDao
import io.github.adefulki.spaceflightnews.domain.model.Blog
import io.github.adefulki.spaceflightnews.utils.toBlogEntities
import io.github.adefulki.spaceflightnews.utils.toBlogs
import javax.inject.Inject

class LocalBlogDatasourceImpl @Inject constructor(
    private val blogDao: BlogDao
) : LocalBlogDatasource {

    override suspend fun insertAll(blogs: ArrayList<Blog>) {
        blogDao.insertAll(blogs.toBlogEntities())
    }

    override suspend fun getAll(): ArrayList<Blog> {
        return blogDao.findAll().toBlogs()
    }

    override suspend fun deleteAll() {
        blogDao.deleteAll()
    }

    override suspend fun findByTitle(title: String): ArrayList<Blog> {
        return blogDao.findByTitle(title).toBlogs()
    }
}