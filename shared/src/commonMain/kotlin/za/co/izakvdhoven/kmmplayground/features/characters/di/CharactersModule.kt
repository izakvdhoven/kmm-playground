package za.co.izakvdhoven.kmmplayground.features.characters.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import za.co.izakvdhoven.kmmplayground.core.di.NamedDependencies
import za.co.izakvdhoven.kmmplayground.features.characters.domain.cache.CharactersCache
import za.co.izakvdhoven.kmmplayground.features.characters.domain.cache.CharactersCacheImpl
import za.co.izakvdhoven.kmmplayground.features.characters.domain.gateways.CharactersGateway
import za.co.izakvdhoven.kmmplayground.features.characters.domain.gateways.CharactersGatewayImpl
import za.co.izakvdhoven.kmmplayground.features.characters.domain.providers.CharactersProvider
import za.co.izakvdhoven.kmmplayground.features.characters.domain.refreshers.CharactersRefresher
import za.co.izakvdhoven.kmmplayground.features.characters.domain.refreshers.CharactersRefresherImpl

internal val charactersModule = module {

    single(named(NamedDependencies.CHARACTERS_ENDPOINT)) {
        val baseUrl: String = get(named(NamedDependencies.BASE_URL))
        "$baseUrl/character"
    }

    single<CharactersGateway> {
        CharactersGatewayImpl(
            endpoint = get(named(NamedDependencies.CHARACTERS_ENDPOINT)),
            client = get(),
            connectivityHelper = get()
        )
    }

    single<CharactersCache> {
        CharactersCacheImpl()
    }

    single<CharactersProvider> {
        get<CharactersCache>() as CharactersProvider
    }

    single<CharactersRefresher> {
        CharactersRefresherImpl(
            gateway = get(),
            cache = get()
        )
    }
}