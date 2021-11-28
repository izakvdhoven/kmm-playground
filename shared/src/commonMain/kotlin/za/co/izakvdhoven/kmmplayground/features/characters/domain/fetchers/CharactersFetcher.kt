package za.co.izakvdhoven.kmmplayground.features.characters.domain.fetchers

import kotlinx.coroutines.flow.firstOrNull
import za.co.izakvdhoven.kmmplayground.core.fetchers.FetcherResult
import za.co.izakvdhoven.kmmplayground.features.characters.cache.CharactersCache
import za.co.izakvdhoven.kmmplayground.features.characters.domain.models.Character
import za.co.izakvdhoven.kmmplayground.features.characters.gateways.CharactersGateway
import za.co.izakvdhoven.kmmplayground.features.characters.gateways.models.CharacterResponse

interface CharactersFetcher {
    suspend fun fetchCharacters(forcedRefresh: Boolean = false): FetcherResult
}

class CharactersFetcherImpl(
    private val gateway: CharactersGateway,
    private val cache: CharactersCache
) : CharactersFetcher {
    override suspend fun fetchCharacters(forcedRefresh: Boolean): FetcherResult {
        if (!shouldUpdate(forcedRefresh)) return FetcherResult.Ignored()
        val response = gateway.fetchCharacters()
        return FetcherResult.from(response).also { fetcherResult ->
            if (fetcherResult is FetcherResult.Success && response.data != null) {
                save(response.data)
            }
        }
    }

    private fun save(characters: List<CharacterResponse>) {
        val stores = characters.map { Character.from(it).toStore() }
        cache.update(stores)
    }

    private suspend fun shouldUpdate(forcedRefresh: Boolean) =
        forcedRefresh || cache.characters.firstOrNull().isNullOrEmpty()
}