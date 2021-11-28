package za.co.izakvdhoven.kmmplayground.core.di

import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import org.koin.core.qualifier.named
import org.koin.dsl.module
import za.co.izakvdhoven.kmmplayground.core.networking.ConnectivityHelper
import za.co.izakvdhoven.kmmplayground.core.networking.MockConnectivityHelper

fun coreModule(enableNetworkLogs: Boolean) = module {

    single(named(NamedDependencies.BASE_URL)) {
        "https://rickandmortyapi.com/api"
    }

    single {
        HttpClient {
            install(JsonFeature) {
                serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                })
            }
            if (enableNetworkLogs) {
                install(Logging) {
                    level = LogLevel.ALL
                }
            }
        }
    }

    single<ConnectivityHelper> {
        MockConnectivityHelper()
    }
}