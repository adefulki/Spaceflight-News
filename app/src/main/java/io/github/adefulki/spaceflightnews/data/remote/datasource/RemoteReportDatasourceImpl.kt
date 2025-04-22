package io.github.adefulki.spaceflightnews.data.remote.datasource

import io.github.adefulki.spaceflightnews.data.datasource.RemoteReportDatasource
import io.github.adefulki.spaceflightnews.data.remote.api.NewsApi
import io.github.adefulki.spaceflightnews.data.remote.dto.ReportDto
import io.github.adefulki.spaceflightnews.domain.model.Report
import javax.inject.Inject

class RemoteReportDatasourceImpl @Inject constructor(
    private val api: NewsApi
) : RemoteReportDatasource {

    override suspend fun getAll(): ArrayList<Report> {
        return api.getReports().resultsToReports()
    }
}