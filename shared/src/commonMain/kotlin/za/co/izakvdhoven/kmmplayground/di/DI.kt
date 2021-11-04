package za.co.izakvdhoven.kmmplayground.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

class DI(appDeclaration: KoinAppDeclaration = {}) {

    init {
        startKoin{
            appDeclaration()
            modules(helloModule)
        }
    }
}