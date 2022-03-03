package za.co.izakvdhoven.kmmplayground.android.features.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import za.co.izakvdhoven.kmmplayground.android.features.characters.ui.CharacterViewData
import za.co.izakvdhoven.kmmplayground.core.refreshers.RefresherResult
import za.co.izakvdhoven.kmmplayground.features.characters.domain.refreshers.CharactersRefresher
import za.co.izakvdhoven.kmmplayground.features.characters.domain.models.Character
import za.co.izakvdhoven.kmmplayground.features.characters.domain.providers.CharactersProvider

class CharactersViewModel(
    private val refresher: CharactersRefresher,
    provider: CharactersProvider
) : ViewModel() {
    private val fetcherResult = MutableStateFlow<RefresherResult?>(null)
    private val isLoading = MutableStateFlow(false)

    val viewData: LiveData<CharactersViewData> = combine(isLoading, fetcherResult, provider.characters) { isLoading, fetcherResult, characters ->
        CharactersViewData.from(isLoading, fetcherResult, characters)
    }.asLiveData()

    fun fetchCharacters() {
        viewModelScope.launch {
            isLoading.value = true
            fetcherResult.value = refresher.refreshCharacters(true)
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
        fun from(isLoading: Boolean, refresherResult: RefresherResult?, characters: List<Character>? = null): CharactersViewData {
            return when {
                isLoading -> Loading()
                refresherResult == null -> Idle()
                refresherResult is RefresherResult.Success && characters != null -> Success(characters.map { CharacterViewData(it) })
                refresherResult is RefresherResult.NoConnection -> NoConnection()
                else -> GeneralError()
            }
        }
    }
}