package za.co.izakvdhoven.kmmplayground.features.characters.persistence.models

internal data class CharacterStore(
    val id: Int,
    val name: String,
    val status: String,
    val imageUrl: String
)