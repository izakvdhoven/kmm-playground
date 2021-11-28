package za.co.izakvdhoven.kmmplayground.android.features.login.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import za.co.izakvdhoven.kmmplayground.android.databinding.ActivityLoginBinding
import za.co.izakvdhoven.kmmplayground.core.di.HelloService
import za.co.izakvdhoven.kmmplayground.features.login.ui.LoginViewData
import za.co.izakvdhoven.kmmplayground.features.login.ui.LoginViewStateData

class LoginActivity : AppCompatActivity(), KoinComponent {
    private val helloService by inject<HelloService>()

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding
    private val username by lazy { binding.username }
    private val password by lazy { binding.password }
    private val login by lazy { binding.login }
    private val loading by lazy { binding.loading }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, LoginViewModelFactory()).get(LoginViewModel::class.java)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)
        configureViews(viewModel.viewData)
        observeViewState()
        sayHello()
    }

    private fun sayHello() = println(helloService.hello())

    private fun configureViews(viewData: LoginViewData) {
        with(viewData) {
            username.hint = usernameLabel
            password.hint = passwordLabel
            login.text = buttonLabel
        }

        login.setOnClickListener {
            viewModel.login(username.text.toString(), password.text.toString())
        }
    }

    private fun configureViewState(viewState: LoginViewStateData) {
        viewState.isLoading.let { isLoading ->
            loading.visibility = if (isLoading) View.VISIBLE else View.GONE
            if (isLoading) return
        }

        if (viewState.didSucceed) {
            Log.d("AuthenticationResult", "Success")
        }

        viewState.errorMessage?.let { errorMessage ->
            Log.d("AuthenticationResult", errorMessage.body)
        }
    }

    private fun observeViewState() {
        viewModel.viewStateData.observe(this) { viewState ->
            configureViewState(viewState)
        }
    }
}