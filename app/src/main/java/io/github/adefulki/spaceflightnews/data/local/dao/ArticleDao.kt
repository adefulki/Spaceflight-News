package io.github.adefulki.spaceflightnews.data.local.dao

import io.github.adefulki.spaceflightnews.base.BaseDao
import io.github.adefulki.spaceflightnews.data.local.entity.ArticleEntity
import io.realm.kotlin.Realm
import javax.inject.Inject
import kotlin.reflect.KClass

interface ArticleDao : BaseDao<ArticleEntity>

class ArticleDaoImpl @Inject constructor(
    r: Realm,
) : ArticleDao {
    override val realm: Realm = r
    override val clazz: KClass<ArticleEntity> = ArticleEntity::class
}