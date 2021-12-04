package za.co.izakvdhoven.kmmplayground.core.di

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import za.co.izakvdhoven.kmmplayground.features.characters.di.charactersModule
import za.co.izakvdhoven.kmmplayground.features.characters.domain.fetchers.CharactersFetcher
import za.co.izakvdhoven.kmmplayground.features.characters.domain.providers.CharactersProvider
import za.co.izakvdhoven.kmmplayground.features.login.di.loginModule

class DI(enableNetworkLogs: Boolean = false, appDeclaration: KoinAppDeclaration = {}) {

    init {
        startKoin{
            appDeclaration()
            modules(
                coreModule(enableNetworkLogs),
                loginModule,
                charactersModule
            )
        }
    }

    object Dependencies: KoinComponent {
        val charactersFetcher: CharactersFetcher by inject()
        val charactersProvider: CharactersProvider by inject()
    }
}