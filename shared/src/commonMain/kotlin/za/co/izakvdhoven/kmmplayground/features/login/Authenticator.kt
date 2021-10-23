package za.co.izakvdhoven.kmmplayground.features.login

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import za.co.izakvdhoven.kmmplayground.data.Result
import za.co.izakvdhoven.kmmplayground.data.DataState

//TODO - Cache authentication state
class AuthenticationResult: Result<Nothing>(data = null) {
    object Success: Result.Success<Nothing>(null)
    object InvalidCredentials: Result.Error<Nothing>()
    object NetworkError: Result.Error<Nothing>()
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
        emit(DataState(result = result))
    }
}