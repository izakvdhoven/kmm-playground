package za.co.izakvdhoven.kmmplayground.core.data

abstract class DataResult<out T: Any?>(val data: T?) {
    open class Success<out T: Any?>(data: T? = null): DataResult<T>(data)
    open class Error<out T: Any?>(val message: String? = null): DataResult<T>(null)
}