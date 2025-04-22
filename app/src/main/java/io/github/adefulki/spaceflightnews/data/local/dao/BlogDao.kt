package io.github.adefulki.spaceflightnews.data.local.dao

import io.github.adefulki.spaceflightnews.base.BaseDao
import io.github.adefulki.spaceflightnews.data.local.entity.BlogEntity
import io.realm.kotlin.Realm
import javax.inject.Inject
import kotlin.reflect.KClass

interface BlogDao : BaseDao<BlogEntity>

class BlogDaoImpl @Inject constructor(
    r: Realm,
) : BlogDao {
    override val realm: Realm = r
    override val clazz: KClass<BlogEntity> = BlogEntity::class
}