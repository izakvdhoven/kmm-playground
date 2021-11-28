package za.co.izakvdhoven.kmmplayground.features.characters.gateways.models

import kotlinx.serialization.*

@Serializable
data class CharactersResponse(
    @SerialName("results")
    val characters: List<CharacterResponse>
)