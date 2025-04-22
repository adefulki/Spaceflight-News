package io.github.adefulki.spaceflightnews.data.datasource

import io.github.adefulki.spaceflightnews.data.remote.dto.ReportDto

interface RemoteReportDatasource {
    suspend fun getReports(): ReportDto
}