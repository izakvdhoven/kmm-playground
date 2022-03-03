package za.co.izakvdhoven.kmmplayground.android.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import za.co.izakvdhoven.kmmplayground.android.features.characters.CharactersViewModel
import za.co.izakvdhoven.kmmplayground.android.features.login.ui.LoginViewModel

val androidModule = module {
    viewModel {
        LoginViewModel(
            authenticator = get()
        )
    }

    viewModel {
        CharactersViewModel(
            refresher = get(),
            provider = get()
        )
    }
}