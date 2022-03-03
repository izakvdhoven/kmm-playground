package za.co.izakvdhoven.kmmplayground.core.refreshers

import za.co.izakvdhoven.kmmplayground.core.data.DataResult
import za.co.izakvdhoven.kmmplayground.core.networking.NetworkResponse
import za.co.izakvdhoven.kmmplayground.core.networking.NetworkResult

abstract class RefresherResult: DataResult<Nothing>(data = null) {
    class Success: RefresherResult()
    class Ignored: RefresherResult()
    class InvalidCredentials: RefresherResult()
    class NoConnection: RefresherResult()
    class MissingData: RefresherResult()
    class GeneralError: RefresherResult()

    companion object {
        internal fun <T : Any?> from(response: NetworkResponse<T>): RefresherResult = with(response) {
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