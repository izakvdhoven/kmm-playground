package za.co.izakvdhoven.kmmplayground.features.characters.cache

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import za.co.izakvdhoven.kmmplayground.features.characters.cache.models.CharacterStore

interface CharactersCache {
    val characters: Flow<List<CharacterStore>?>
    fun update(characters: List<CharacterStore>)
    fun clear()
}

class CharactersCacheImpl : CharactersCache {
    private val _characters = MutableStateFlow<List<CharacterStore>?>(null)

    override val characters: Flow<List<CharacterStore>?> = _characters

    override fun update(characters: List<CharacterStore>) {
        _characters.value = characters
    }

    override fun clear() {
        _characters.value = null
    }
}