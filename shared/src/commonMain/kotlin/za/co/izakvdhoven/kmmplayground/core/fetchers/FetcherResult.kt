package za.co.izakvdhoven.kmmplayground.core.fetchers

import za.co.izakvdhoven.kmmplayground.core.data.DataResult
import za.co.izakvdhoven.kmmplayground.core.networking.NetworkResponse
import za.co.izakvdhoven.kmmplayground.core.networking.NetworkResult

abstract class FetcherResult: DataResult<Nothing>(data = null) {
    class Success: FetcherResult()
    class Ignored: FetcherResult()
    class InvalidCredentials: FetcherResult()
    class NoConnection: FetcherResult()
    class MissingData: FetcherResult()
    class GeneralError: FetcherResult()

    companion object {
        fun <T : Any?> from(response: NetworkResponse<T>): FetcherResult = with(response) {
            return when {
                isSuccessful -> Success()
                result is NetworkResult.Unauthorised -> InvalidCredentials()
                result is NetworkResult.NoConnection -> NoConnection()
                result is NetworkResult.Other -> GeneralError()
                data == null -> MissingData()
                else -> GeneralError()
            }
        }
    }

}