package za.co.izakvdhoven.kmmplayground.features.login

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import za.co.izakvdhoven.kmmplayground.core.data.DataResult
import za.co.izakvdhoven.kmmplayground.core.data.DataState

//TODO - Cache authentication state
class AuthenticationResult: DataResult<Nothing>(data = null) {
    object Success: DataResult.Success<Nothing>(null)
    object InvalidCredentials: DataResult.Error<Nothing>()
    object NetworkError: DataResult.Error<Nothing>()
}

interface Authenticator {
    suspend fun authenticate(username: String, password: String): Flow<DataState<AuthenticationResult>>
}

class MockAuthenticatorImpl : Authenticator {
    override suspend fun authenticate(username: String, password: String): Flow<DataState<AuthenticationResult>> = flow {
        emit(DataState.Loading())

        delay(1000)
        val isValid = username.equals("test", true) && password.equals("test", true)
        val result = if (isValid) AuthenticationResult.Success else AuthenticationResult.InvalidCredentials

        emit(DataState.Complete(data = result))
    }
}