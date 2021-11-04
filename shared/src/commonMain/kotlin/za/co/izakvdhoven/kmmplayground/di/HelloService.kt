package za.co.izakvdhoven.kmmplayground.di

interface HelloService {
    fun hello(): String
}

class HelloServiceImpl(private val helloMessageData: HelloMessageData) : HelloService {

    override fun hello() = "Hey, ${helloMessageData.message}"
}