package za.co.izakvdhoven.kmmplayground.features.characters.domain.cache

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import za.co.izakvdhoven.kmmplayground.features.characters.persistence.models.CharacterStore
import za.co.izakvdhoven.kmmplayground.features.characters.domain.models.Character
import za.co.izakvdhoven.kmmplayground.features.characters.domain.providers.CharactersProvider

internal interface CharactersCache {
    val characters: Flow<List<Character>?>
    fun update(characters: List<Character>)
    fun clear()
}

internal class CharactersCacheImpl : CharactersCache, CharactersProvider {
    private val _characters = MutableStateFlow<List<CharacterStore>?>(null)

    override val characters: Flow<List<Character>?> = _characters.map { stores ->
        stores?.map { Character(it) }
    }

    override fun update(characters: List<Character>) {
        _characters.value = characters.map { it.toStore() }
    }

    override fun clear() {
        _characters.value = null
    }
}