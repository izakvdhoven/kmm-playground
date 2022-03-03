package za.co.izakvdhoven.kmmplayground.features.characters.domain.gateways

import io.ktor.client.*
import io.ktor.client.request.*
import za.co.izakvdhoven.kmmplayground.core.networking.BaseGateway
import za.co.izakvdhoven.kmmplayground.core.networking.ConnectivityHelper
import za.co.izakvdhoven.kmmplayground.core.networking.NetworkResponse
import za.co.izakvdhoven.kmmplayground.features.characters.domain.models.Character
import za.co.izakvdhoven.kmmplayground.features.characters.network.models.CharactersResponse

internal interface CharactersGateway {
    suspend fun fetchCharacters(): NetworkResponse<List<Character>?>
}

internal class CharactersGatewayImpl(
    private val endpoint: String,
    private val client: HttpClient,
    connectivityHelper: ConnectivityHelper
) : BaseGateway(connectivityHelper), CharactersGateway {

    override suspend fun fetchCharacters(): NetworkResponse<List<Character>?> {
        val response = getNetworkResponse<CharactersResponse> {
            client.get(endpoint) {
                parameter("page", (1..42).random())
            }
        }

        val characters = response.data?.characters?.map { Character(it) }
        return NetworkResponse(response.result, characters)
    }
}