package za.co.izakvdhoven.kmmplayground.android

import android.app.Application
import org.koin.android.ext.koin.androidContext
import za.co.izakvdhoven.kmmplayground.android.di.androidModule
import za.co.izakvdhoven.kmmplayground.core.di.*

class KMMPlaygroundApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        DI(true) {
            androidContext(this@KMMPlaygroundApplication)
            modules(androidModule)
        }
    }
}