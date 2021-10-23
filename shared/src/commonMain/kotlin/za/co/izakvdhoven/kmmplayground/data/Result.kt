package za.co.izakvdhoven.kmmplayground.data

open class Result<out T: Any?>(val data: T?) {
    open class Success<out T: Any?>(data: T? = null): Result<T>(data)
    open class Error<out T: Any?>(val message: String? = null): Result<T>(null)
}