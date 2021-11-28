package za.co.izakvdhoven.kmmplayground.features.login.di

import org.koin.dsl.module
import za.co.izakvdhoven.kmmplayground.features.login.Authenticator
import za.co.izakvdhoven.kmmplayground.features.login.MockAuthenticatorImpl

val loginModule = module {

    single<Authenticator> { MockAuthenticatorImpl() }

}