package za.co.izakvdhoven.kmmplayground.features.login.ui

import za.co.izakvdhoven.kmmplayground.core.data.DataState
import za.co.izakvdhoven.kmmplayground.features.login.AuthenticationResult
import za.co.izakvdhoven.kmmplayground.core.ui.Message

open class LoginViewStateData(
    val isLoading: Boolean = false,
    val didSucceed: Boolean = false,
    val errorMessage: Message? = null
) {
    class Loading : LoginViewStateData(isLoading = true)
    class Success : LoginViewStateData(didSucceed = true)
    class Error(message: Message) : LoginViewStateData(errorMessage = message)

    companion object {
        private val invalidCredentialsMessage = Message("Message Title", "Invalid Credentials")
        private val networkErrorMessage = Message("Message Title", "Network Error")
        private val generalErrorMessage = Message("Message Title", "General Error")

        fun from(authState: DataState<AuthenticationResult>): LoginViewStateData? = when {
            authState.isIdle() -> null
            authState.isLoading() -> Loading()
            authState.result is AuthenticationResult.Success -> Success()
            authState.result is AuthenticationResult.InvalidCredentials -> Error(invalidCredentialsMessage)
            authState.result is AuthenticationResult.NetworkError -> Error(networkErrorMessage)
            else -> Error(generalErrorMessage)
        }
    }
}