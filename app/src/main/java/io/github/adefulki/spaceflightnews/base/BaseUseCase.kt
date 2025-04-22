package io.github.adefulki.spaceflightnews.base

interface BaseUseCase<In, Out> {
    suspend fun execute(input: In): Out
}