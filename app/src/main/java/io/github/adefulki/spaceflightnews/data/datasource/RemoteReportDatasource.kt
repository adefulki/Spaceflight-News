package io.github.adefulki.spaceflightnews.data.datasource

import io.github.adefulki.spaceflightnews.domain.model.Report

interface RemoteReportDatasource {
    suspend fun getAll(): ArrayList<Report>
}