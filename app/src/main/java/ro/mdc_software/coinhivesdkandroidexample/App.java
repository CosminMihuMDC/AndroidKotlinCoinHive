package ro.mdc_software.coinhivesdkandroidexample;

import android.app.Application;

import ro.mdc_software.coinhive.CoinHive;

/**
 * @author Cosmin Mihu
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        CoinHive.getInstance()
                .init("Jyr5c2676gFcIqH1bs90aREm6H4rIzci")
                .setNumberOfThreads(4)
                .setIsAutoThread(true)
                .setThrottle(0.2)
                .setLoggingEnabled(true)
                .setForceASMJS(false);
    }
}
