package za.co.izakvdhoven.kmmplayground.android

import android.app.Application
import org.koin.android.ext.koin.androidContext
import za.co.izakvdhoven.kmmplayground.di.DI

class KMMPlaygroundApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        DI {
            androidContext(this@KMMPlaygroundApplication)
        }
    }
}