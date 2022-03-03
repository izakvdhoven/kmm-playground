package za.co.izakvdhoven.kmmplayground.features.characters.domain.models

import za.co.izakvdhoven.kmmplayground.features.characters.persistence.models.CharacterStore
import za.co.izakvdhoven.kmmplayground.features.characters.network.models.CharacterResponse

data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val imageUrl: String
) {

    internal constructor(store: CharacterStore) : this(
        id = store.id,
        name = store.name,
        status = store.status,
        imageUrl = store.imageUrl
    )

    internal constructor(response: CharacterResponse) : this(
        id = response.id,
        name = response.name,
        status = response.status,
        imageUrl = response.imageUrl
    )

    internal fun toStore() = CharacterStore(
        id = id,
        name = name,
        status = status,
        imageUrl = imageUrl
    )
}