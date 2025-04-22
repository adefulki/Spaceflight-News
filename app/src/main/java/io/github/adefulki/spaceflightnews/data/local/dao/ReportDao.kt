package io.github.adefulki.spaceflightnews.data.local.dao

import io.github.adefulki.spaceflightnews.base.BaseDao
import io.github.adefulki.spaceflightnews.data.local.entity.ReportEntity
import io.realm.kotlin.Realm
import javax.inject.Inject
import kotlin.reflect.KClass

interface ReportDao : BaseDao<ReportEntity>

class ReportDaoImpl @Inject constructor(
    r: Realm,
) : ReportDao {
    override val realm: Realm = r
    override val clazz: KClass<ReportEntity> = ReportEntity::class
}