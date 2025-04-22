package io.github.adefulki.spaceflightnews.data.remote.datasource

import io.github.adefulki.spaceflightnews.data.datasource.RemoteReportDatasource
import io.github.adefulki.spaceflightnews.data.remote.api.NewsApi
import io.github.adefulki.spaceflightnews.data.remote.dto.ReportDto
import javax.inject.Inject

class RemoteReportDatasourceImpl @Inject constructor(
    private val api: NewsApi
) : RemoteReportDatasource {

    override suspend fun getReports(): ReportDto {
        return api.getReports()
    }
}