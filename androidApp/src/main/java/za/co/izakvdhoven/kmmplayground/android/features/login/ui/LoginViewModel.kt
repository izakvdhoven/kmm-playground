package za.co.izakvdhoven.kmmplayground.android.features.login.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import za.co.izakvdhoven.kmmplayground.core.data.DataState
import za.co.izakvdhoven.kmmplayground.features.login.AuthenticationResult
import za.co.izakvdhoven.kmmplayground.features.login.Authenticator
import za.co.izakvdhoven.kmmplayground.features.login.ui.LoginViewData
import za.co.izakvdhoven.kmmplayground.features.login.ui.LoginViewStateData

class LoginViewModel(private val authenticator: Authenticator) : ViewModel() {

    private val _authState = MutableStateFlow<DataState<AuthenticationResult>>(DataState.Idle())

    val viewData: LoginViewData = LoginViewData(
        usernameLabel = "Username",
        passwordLabel = "Password",
        buttonLabel = "Log in"
    )

    val viewStateData: LiveData<LoginViewStateData> = liveData {
        _authState.collect { authState ->
            LoginViewStateData.from(authState)?.let { viewStateData ->
                emit(viewStateData)
            }
        }
    }

    fun login(username: String, password: String) {
        viewModelScope.launch {
            authenticate(username, password)
        }
    }

    private suspend fun authenticate(username: String, password: String) {
        authenticator.authenticate(username, password).collect { state ->
            _authState.value = state
        }

    }
}