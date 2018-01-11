package ro.mdc_software.coinhive

/**
 * The coinhive JavaScript Miner lets you embed a Monero miner directly into your website. The miner itself does not come with a UI – it's your responsibility to tell your users what's going on and to provide stats on mined hashes.
 * While it's possible to run the miner without informing your users, we strongly advise against it. You know this. Long term goodwill of your users is much more important than any short term profits.
 * You can credit found hashes to a user name or let it run anonymously. The miner runs until you explicitely stop it again or the user navigates away. You can also credit hashes to a random token and the miner will automatically stop when it reaches the specified number of hashes.
 * See the HTTP API documentation on how to get the balance for a user name (the number of hashes accepted) and withdraw hashes, and how to verify a token.
 *
 * @author Cosmin Mihu
 */
object CoinHive {
    private var siteKey: String? = null
    var numberOfThreads = 4
        private set
    var isAutoThread = false
        private set
    var throttle = 0f
        private set
    var isForceASMJS = false
        private set
    var loggingEnabled: Boolean = false
        private set

    interface Callback {
        fun isShowMining(): Boolean = false

        fun onMiningStarted()

        fun onMiningStopped()

        fun onRunning(hashesPerSecond: Double, totalHashes: Long, acceptedHashes: Long)
    }

    /**
     * @param siteKey Your public Site-Key. See Settings » Sites.
     */
    fun init(siteKey: String): CoinHive {
        this.siteKey = siteKey
        return this
    }

    /**
     * @param numberOfThreads The number of threads the miner should start with. The default is navigator.hardwareConcurrency, i.e. the number of CPU cores available on the user's computer.
     */
    fun setNumberOfThreads(numberOfThreads: Int): CoinHive {
        this.numberOfThreads = numberOfThreads
        return this
    }

    /**
     * @param isAutoThread Whether to automatically adjust the number of threads for optimal performance. This feature is experimental. The default is false.
     */
    fun setIsAutoThread(isAutoThread: Boolean): CoinHive {
        this.isAutoThread = isAutoThread
        return this
    }

    /**
     * @param throttle The fraction of time that threads should be idle. See miner.setThrottle() for a detailed explanation. The default is 0.
     */
    fun setThrottle(throttle: Double): CoinHive {
        this.throttle = throttle.toFloat()
        return this
    }

    /**
     * @param isForceASMJS If true, the miner will always use the asm.js implementation of the hashing algorithm. If false, the miner will use the faster WebAssembly version if supported and otherwise fall back to asm.js. The default is false.
     */
    fun setForceASMJS(isForceASMJS: Boolean): CoinHive {
        this.isForceASMJS = isForceASMJS
        return this
    }

    /**
     * @param loggingEnabled Enable/disable console logging.
     */
    fun setLoggingEnabled(loggingEnabled: Boolean): CoinHive {
        this.loggingEnabled = loggingEnabled
        return this
    }

    internal fun generateURL(): String {
        if (siteKey == null) {
            throw IllegalArgumentException("site_key not set. You must call CoinHive.getInstance().init() from your application instance")
        }
        return "file:///android_asset/engine.html?coinhive_site_key=${siteKey}&num_of_threads=${numberOfThreads}&is_auto_thread=${isAutoThread}&throttle=${throttle}"
    }
}
