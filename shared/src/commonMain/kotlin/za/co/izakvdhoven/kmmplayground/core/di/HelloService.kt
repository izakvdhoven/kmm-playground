package za.co.izakvdhoven.kmmplayground.core.di

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface HelloService {
    fun hello(): String
}

class HelloServiceImpl : HelloService, KoinComponent {

    private val helloMessageData: HelloMessageData by inject()

    override fun hello() = "Hey, ${helloMessageData.message}"
}