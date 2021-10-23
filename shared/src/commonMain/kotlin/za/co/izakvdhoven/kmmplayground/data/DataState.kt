package za.co.izakvdhoven.kmmplayground.data

open class DataState<out T: Any?>(
    val result: Result<T>? = null,
    val loading: Boolean = false
) {
    class Idle<out T: Any?>: DataState<T>()
    class Loading<out T: Any?>: DataState<T>(loading = true)
    class Complete<out T: Any?>(data: Result<T>): DataState<T>(result = data)

    fun isIdle() = this is Idle
    fun isLoading() = this is Loading
    fun isLoadingOrIdle() = isLoading() || isIdle()
    fun isComplete() = this is Complete
}