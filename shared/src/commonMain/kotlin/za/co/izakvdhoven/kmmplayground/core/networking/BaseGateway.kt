package za.co.izakvdhoven.kmmplayground.core.networking

import io.ktor.client.call.*
import io.ktor.client.features.*
import io.ktor.client.statement.*

abstract class BaseGateway(val connectivityHelper: ConnectivityHelper) {
    suspend inline fun <reified T : Any?> getNetworkResponse(allowEmptyResponse: Boolean = false, call: () -> HttpResponse): NetworkResponse<T> {
        if (connectivityHelper.isConnected.not()) return NetworkResponse(NetworkResult.NoConnection(), null)

        return try {
            val httpResponse: HttpResponse = call()
            val data = httpResponse.receive<T>()

            if (data == null && allowEmptyResponse.not()) {
                NetworkResponse(NetworkResult.MissingData(), null)
            } else {
                NetworkResponse(httpResponse.status, data)
            }
        } catch (exception: ResponseException) {
            NetworkResponse(exception.response.status, null)
        } catch (exception: Exception) {
            NetworkResponse(NetworkResult.Other(), null)
        }
    }
}