package za.co.izakvdhoven.kmmplayground.core.data

abstract class DataState<out T: Any?>(
    val result: DataResult<T>? = null,
    val loading: Boolean = false
) {
    class Idle<out T: Any?>: DataState<T>()
    class Loading<out T: Any?>: DataState<T>(loading = true)
    class Complete<out T: Any?>(data: DataResult<T>): DataState<T>(result = data)

    fun isIdle() = this is Idle
    fun isLoading() = this is Loading
    fun isLoadingOrIdle() = isLoading() || isIdle()
    fun isComplete() = this is Complete
}