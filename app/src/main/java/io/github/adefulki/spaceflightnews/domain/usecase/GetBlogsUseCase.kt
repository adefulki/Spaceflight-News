package io.github.adefulki.spaceflightnews.domain.usecase

import io.github.adefulki.spaceflightnews.base.BaseUseCase
import io.github.adefulki.spaceflightnews.domain.model.Blog
import io.github.adefulki.spaceflightnews.domain.repo.NewsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetBlogsUseCase @Inject constructor(
    private val repository: NewsRepo
) : BaseUseCase<Unit, ArrayList<Blog>> {
    override suspend fun execute(input: Unit): ArrayList<Blog> {
        return withContext(Dispatchers.IO) {
            repository.getBlogs()
        }
    }
}