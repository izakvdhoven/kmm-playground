package za.co.izakvdhoven.kmmplayground.features.characters.gateways

import io.ktor.client.*
import io.ktor.client.request.*
import za.co.izakvdhoven.kmmplayground.core.networking.BaseGateway
import za.co.izakvdhoven.kmmplayground.core.networking.ConnectivityHelper
import za.co.izakvdhoven.kmmplayground.core.networking.NetworkResponse
import za.co.izakvdhoven.kmmplayground.features.characters.gateways.models.CharacterResponse
import za.co.izakvdhoven.kmmplayground.features.characters.gateways.models.CharactersResponse

internal interface CharactersGateway {
    suspend fun fetchCharacters(): NetworkResponse<List<CharacterResponse>?>
}

internal class CharactersGatewayImpl(
    private val endpoint: String,
    private val client: HttpClient,
    connectivityHelper: ConnectivityHelper
) : BaseGateway(connectivityHelper), CharactersGateway {

    override suspend fun fetchCharacters(): NetworkResponse<List<CharacterResponse>?> {
        val response = getNetworkResponse<CharactersResponse> {
            client.get(endpoint) {
                parameter("page", (1..42).random())
            }
        }

        return NetworkResponse(response.result, response.data?.characters)
    }
}