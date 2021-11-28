package za.co.izakvdhoven.kmmplayground.features.characters.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import za.co.izakvdhoven.kmmplayground.core.di.NamedDependencies
import za.co.izakvdhoven.kmmplayground.features.characters.cache.CharactersCache
import za.co.izakvdhoven.kmmplayground.features.characters.cache.CharactersCacheImpl
import za.co.izakvdhoven.kmmplayground.features.characters.domain.fetchers.CharactersFetcher
import za.co.izakvdhoven.kmmplayground.features.characters.domain.fetchers.CharactersFetcherImpl
import za.co.izakvdhoven.kmmplayground.features.characters.domain.providers.CharactersProvider
import za.co.izakvdhoven.kmmplayground.features.characters.domain.providers.CharactersProviderImpl
import za.co.izakvdhoven.kmmplayground.features.characters.gateways.CharactersGateway
import za.co.izakvdhoven.kmmplayground.features.characters.gateways.CharactersGatewayImpl

val charactersModule = module {

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
        CharactersProviderImpl(
            cache = get()
        )
    }

    single<CharactersFetcher> {
        CharactersFetcherImpl(
            gateway = get(),
            cache = get()
        )
    }
}