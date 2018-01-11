package ro.mdc_software.coinhivesdkandroidexample

import android.app.Application

import ro.mdc_software.coinhive.CoinHive

/**
 * @author Cosmin Mihu
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        CoinHive.init("Jyr5c2676gFcIqH1bs90aREm6H4rIzci")
                .setNumberOfThreads(4)
                .setIsAutoThread(true)
                .setThrottle(0.6) // will stay idle 60%
                .setLoggingEnabled(true)
                .setForceASMJS(false)
    }
}
