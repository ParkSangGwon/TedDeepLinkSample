package ted.gun0912.deeplink

import android.app.Application

class TedApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: TedApplication
            private set
    }
}