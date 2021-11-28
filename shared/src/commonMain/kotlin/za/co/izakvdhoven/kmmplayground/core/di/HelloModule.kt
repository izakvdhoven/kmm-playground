package za.co.izakvdhoven.kmmplayground.core.di

import org.koin.dsl.module

val helloModule = module {

    single { HelloMessageData() }

    single<HelloService> { HelloServiceImpl() }

}