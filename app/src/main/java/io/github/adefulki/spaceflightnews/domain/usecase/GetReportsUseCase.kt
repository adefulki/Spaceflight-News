package io.github.adefulki.spaceflightnews.domain.usecase

import io.github.adefulki.spaceflightnews.base.BaseUseCase
import io.github.adefulki.spaceflightnews.domain.model.Report
import io.github.adefulki.spaceflightnews.domain.repo.NewsRepo
import javax.inject.Inject

class GetReportsUseCase @Inject constructor(
    private val repository: NewsRepo
) : BaseUseCase<Unit, ArrayList<Report>> {
    override suspend fun execute(input: Unit): ArrayList<Report> {
        return repository.getReports()
    }
}