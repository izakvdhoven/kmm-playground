package za.co.izakvdhoven.kmmplayground.features.characters.domain.providers

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import za.co.izakvdhoven.kmmplayground.features.characters.domain.cache.CharactersCache
import za.co.izakvdhoven.kmmplayground.features.characters.domain.models.Character

interface CharactersProvider {
    val characters: Flow<List<Character>?>
}