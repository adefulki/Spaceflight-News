package io.github.adefulki.spaceflightnews.data.local.datasource

import io.github.adefulki.spaceflightnews.data.datasource.LocalReportDatasource
import io.github.adefulki.spaceflightnews.data.datasource.RemoteReportDatasource
import io.github.adefulki.spaceflightnews.data.local.dao.ReportDao
import io.github.adefulki.spaceflightnews.data.local.entity.ReportEntity
import io.github.adefulki.spaceflightnews.domain.model.Report
import io.github.adefulki.spaceflightnews.utils.toReportEntities
import io.github.adefulki.spaceflightnews.utils.toReports
import javax.inject.Inject

class LocalReportDatasourceImpl @Inject constructor(
    private val reportDao: ReportDao
) : LocalReportDatasource {

    override suspend fun insertAll(reports: ArrayList<Report>) {
        reportDao.insertAll(reports.toReportEntities())
    }

    override suspend fun getAll(): ArrayList<Report> {
        return reportDao.findAll().toReports()
    }

    override suspend fun deleteAll() {
        reportDao.deleteAll()
    }

    override suspend fun findByTitle(title: String): ArrayList<Report> {
        return reportDao.findByTitle(title).toReports()
    }
}