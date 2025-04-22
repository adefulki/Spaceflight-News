package io.github.adefulki.spaceflightnews.data.datasource

import io.github.adefulki.spaceflightnews.domain.model.Report

interface LocalReportDatasource {
    suspend fun insertAll(reports: ArrayList<Report>)
    suspend fun getAll(): ArrayList<Report>
    suspend fun deleteAll()
    suspend fun findByTitle(title: String): ArrayList<Report>
}