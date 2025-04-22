package io.github.adefulki.spaceflightnews.domain.usecase

import io.github.adefulki.spaceflightnews.base.BaseUseCase
import io.github.adefulki.spaceflightnews.domain.model.Article
import io.github.adefulki.spaceflightnews.domain.repo.NewsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetArticlesUseCase @Inject constructor(
    private val repository: NewsRepo
) : BaseUseCase<Unit, ArrayList<Article>> {
    override suspend fun execute(input: Unit): ArrayList<Article> {
        return withContext(Dispatchers.IO) {
            repository.getArticles()
        }
    }
}