package io.github.adefulki.spaceflightnews.base

sealed class BaseResult<T>(val data: T? = null, val message: String? = null) {
    class Loading<T>(data: T? = null) : BaseResult<T>(data)
    class Success<T>(data: T?) : BaseResult<T>(data)
    class Error<T>(message: String, data: T? = null) : BaseResult<T>(data, message)
}
