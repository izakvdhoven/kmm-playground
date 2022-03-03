package za.co.izakvdhoven.kmmplayground.features.characters.domain.refreshers

import kotlinx.coroutines.flow.firstOrNull
import za.co.izakvdhoven.kmmplayground.core.refreshers.RefresherResult
import za.co.izakvdhoven.kmmplayground.features.characters.domain.cache.CharactersCache
import za.co.izakvdhoven.kmmplayground.features.characters.domain.models.Character
import za.co.izakvdhoven.kmmplayground.features.characters.domain.gateways.CharactersGateway

interface CharactersRefresher {
    suspend fun refreshCharacters(forcedRefresh: Boolean = false): RefresherResult
}

internal class CharactersRefresherImpl(
    val gateway: CharactersGateway,
    val cache: CharactersCache
) : CharactersRefresher {

    override suspend fun refreshCharacters(forcedRefresh: Boolean): RefresherResult {
        if (!shouldUpdate(forcedRefresh)) return RefresherResult.Ignored()
        val response = gateway.fetchCharacters()
        return RefresherResult.from(response).also { fetcherResult ->
            if (fetcherResult is RefresherResult.Success && response.data != null) {
                save(response.data)
            }
        }
    }

    private fun save(characters: List<Character>) {
        cache.update(characters)
    }

    private suspend fun shouldUpdate(forcedRefresh: Boolean) =
        forcedRefresh || cache.characters.firstOrNull().isNullOrEmpty()
}