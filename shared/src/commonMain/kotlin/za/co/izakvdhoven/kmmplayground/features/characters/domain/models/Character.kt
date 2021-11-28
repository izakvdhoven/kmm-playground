package za.co.izakvdhoven.kmmplayground.features.characters.domain.models

import za.co.izakvdhoven.kmmplayground.features.characters.cache.models.CharacterStore
import za.co.izakvdhoven.kmmplayground.features.characters.gateways.models.CharacterResponse

data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val imageUrl: String
) {

    fun toStore() = CharacterStore(
        id = id,
        name = name,
        status = status,
        imageUrl = imageUrl
    )

    companion object {
        fun from(store: CharacterStore) = Character(
            id = store.id,
            name = store.name,
            status = store.status,
            imageUrl = store.imageUrl
        )

        fun from(response: CharacterResponse) = Character(
            id = response.id,
            name = response.name,
            status = response.status,
            imageUrl = response.imageUrl
        )
    }
}