package za.co.izakvdhoven.kmmplayground.android.features.characters.ui

import android.net.Uri
import androidx.core.net.toUri
import za.co.izakvdhoven.kmmplayground.features.characters.domain.models.Character

data class CharacterViewData(
    val name: String,
    val status: String,
    val imageUrl: Uri
) {
    constructor(character: Character) : this(
        name = character.name,
        status = character.status,
        imageUrl = character.imageUrl.toUri()
    )
}