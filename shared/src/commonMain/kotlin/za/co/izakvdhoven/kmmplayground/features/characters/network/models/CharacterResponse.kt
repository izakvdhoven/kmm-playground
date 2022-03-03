package za.co.izakvdhoven.kmmplayground.features.characters.network.models

import kotlinx.serialization.*

@Serializable
internal data class CharacterResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("status")
    val status: String,
    @SerialName("image")
    val imageUrl: String
)