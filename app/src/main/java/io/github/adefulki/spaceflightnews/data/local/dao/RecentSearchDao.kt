package io.github.adefulki.spaceflightnews.data.local.dao

import io.github.adefulki.spaceflightnews.base.BaseDao
import io.github.adefulki.spaceflightnews.data.local.entity.RecentSearchEntity
import io.realm.kotlin.Realm
import javax.inject.Inject
import kotlin.reflect.KClass

interface RecentSearchDao : BaseDao<RecentSearchEntity>

class RecentSearchDaoImpl @Inject constructor(
    r: Realm,
) : RecentSearchDao {
    override val realm: Realm = r
    override val clazz: KClass<RecentSearchEntity> = RecentSearchEntity::class
}