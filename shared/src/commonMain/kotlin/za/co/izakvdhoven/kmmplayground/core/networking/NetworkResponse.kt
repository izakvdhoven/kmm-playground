package za.co.izakvdhoven.kmmplayground.core.networking

import io.ktor.http.*

internal data class NetworkResponse<T: Any?>(val result: NetworkResult, val data: T?) {
    constructor(status: HttpStatusCode, data: T?): this(NetworkResult.from(status.value), data)
    val isSuccessful = result is NetworkResult.Success
}

//Extend this if and when needed
internal abstract class NetworkResult {
    class Success: NetworkResult()
    class Unauthorised: NetworkResult()
    class NoConnection: NetworkResult()
    class MissingData: NetworkResult()
    class Other: NetworkResult()

    companion object {
        fun from(httpCode: Int): NetworkResult {
            return when (httpCode) {
                in 200..299 -> Success()
                401 -> Unauthorised()
                else -> Other()
            }
        }
    }
}

