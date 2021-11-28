package za.co.izakvdhoven.kmmplayground.android.features.characters

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import za.co.izakvdhoven.kmmplayground.core.fetchers.FetcherResult
import za.co.izakvdhoven.kmmplayground.features.characters.domain.fetchers.CharactersFetcher
import za.co.izakvdhoven.kmmplayground.features.characters.domain.models.Character
import za.co.izakvdhoven.kmmplayground.features.characters.domain.providers.CharactersProvider

class CharactersViewModel(
    private val fetcher: CharactersFetcher,
    provider: CharactersProvider
) : ViewModel() {
    private val fetcherResult = MutableStateFlow<FetcherResult?>(null)
    private val isLoading = MutableStateFlow(false)

    val viewData: LiveData<CharactersViewData> = combine(isLoading, fetcherResult, provider.characters) { isLoading, fetcherResult, characters ->
        CharactersViewData.from(isLoading, fetcherResult, characters)
    }.asLiveData()

    fun fetchCharacters() {
        viewModelScope.launch {
            isLoading.value = true
            fetcherResult.value = fetcher.fetchCharacters(true)
            isLoading.value = false
        }
    }
}

abstract class CharactersViewData(
    val isBusy: Boolean = false,
    val didSucceed: Boolean = false
) {
    class Idle: CharactersViewData()
    class Loading: CharactersViewData(isBusy = true)
    class Success(val characters: List<CharacterViewData>): CharactersViewData(didSucceed = true)
    abstract class Error(val message: String): CharactersViewData(didSucceed = false)
    class NoConnection: Error(message = "No connection")
    class GeneralError: Error(message = "Something went wrong")

    companion object {
        fun from(isLoading: Boolean, fetcherResult: FetcherResult?, characters: List<Character>? = null): CharactersViewData {
            return when {
                isLoading -> Loading()
                fetcherResult == null -> Idle()
                fetcherResult is FetcherResult.Success && characters != null -> Success(characters.map { CharacterViewData.from(it) })
                fetcherResult is FetcherResult.NoConnection -> NoConnection()
                else -> GeneralError()
            }
        }
    }
}

data class CharacterViewData(
    val name: String,
    val status: String,
    val imageUrl: Uri
) {
    companion object {
        fun from(character: Character) = CharacterViewData(
            name = character.name,
            status = character.status,
            imageUrl = Uri.parse(character.imageUrl)
        )
    }
}