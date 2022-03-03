package za.co.izakvdhoven.kmmplayground.features.characters.network.models

import kotlinx.serialization.*

@Serializable
internal data class CharactersResponse(
    @SerialName("results")
    val characters: List<CharacterResponse>
)