package ro.mdc_software.coinhive;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

/**
 * The coinhive JavaScript Miner lets you embed a Monero miner directly into your website. The miner itself does not come with a UI – it's your responsibility to tell your users what's going on and to provide stats on mined hashes.
 * While it's possible to run the miner without informing your users, we strongly advise against it. You know this. Long term goodwill of your users is much more important than any short term profits.
 * You can credit found hashes to a user name or let it run anonymously. The miner runs until you explicitely stop it again or the user navigates away. You can also credit hashes to a random token and the miner will automatically stop when it reaches the specified number of hashes.
 * See the HTTP API documentation on how to get the balance for a user name (the number of hashes accepted) and withdraw hashes, and how to verify a token.
 *
 * @author Cosmin Mihu
 */
public class CoinHive {

    public interface Callback {
        boolean isShowMining();

        void onMiningStarted();

        void onMiningStopped();

        void onRunning(double hashesPerSecond, long totalHashes, long acceptedHashes);
    }

    private static final CoinHive instance = new CoinHive();
    private String siteKey;
    private int numberOfThreads = 4;
    private boolean isAutoThread = false;
    private float throttle = 0;
    private boolean isForceASMJS = false;
    private boolean loggingEnabled;

    public static CoinHive getInstance() {
        return instance;
    }

    /**
     * @param siteKey Your public Site-Key. See Settings » Sites.
     */
    public CoinHive init(String siteKey) {
        this.siteKey = siteKey;
        return this;
    }

    /**
     * @param numberOfThreads The number of threads the miner should start with. The default is navigator.hardwareConcurrency, i.e. the number of CPU cores available on the user's computer.
     */
    public CoinHive setNumberOfThreads(int numberOfThreads) {
        this.numberOfThreads = numberOfThreads;
        return this;
    }

    /**
     * @param isAutoThread Whether to automatically adjust the number of threads for optimal performance. This feature is experimental. The default is false.
     */
    public CoinHive setIsAutoThread(boolean isAutoThread) {
        this.isAutoThread = isAutoThread;
        return this;
    }

    /**
     * @param throttle The fraction of time that threads should be idle. See miner.setThrottle() for a detailed explanation. The default is 0.
     */
    public CoinHive setThrottle(double throttle) {
        this.throttle = (float) throttle;
        return this;
    }

    /**
     * @param isForceASMJS If true, the miner will always use the asm.js implementation of the hashing algorithm. If false, the miner will use the faster WebAssembly version if supported and otherwise fall back to asm.js. The default is false.
     */
    public CoinHive setForceASMJS(boolean isForceASMJS) {
        this.isForceASMJS = isForceASMJS;
        return this;
    }

    static String generateURL() {

        if (instance.getSiteKey() == null) {
            throw new IllegalArgumentException("site_key not set. You must call CoinHive.getInstance().init() from your application instance");
        }
        return String.format("file:///android_asset/engine.html?coinhive_site_key=%s&num_of_threads=%d&is_auto_thread=%s&throttle=%s",
                instance.getSiteKey(), instance.getNumberOfThreads(), instance.isAutoThread(), instance.getThrottle());
    }

    private String getSiteKey() {
        return siteKey;
    }

    private int getNumberOfThreads() {
        return numberOfThreads;
    }

    private boolean isAutoThread() {
        return isAutoThread;
    }

    private float getThrottle() {
        return throttle;
    }

    private boolean isForceASMJS() {
        return isForceASMJS;
    }

    public boolean isLoggingEnabled() {
        return loggingEnabled;
    }

    public CoinHive setLoggingEnabled(boolean loggingEnabled) {
        this.loggingEnabled = loggingEnabled;
        return this;
    }

    static class Miner {

        private final Callback callback;
        private android.webkit.WebView webviewCoinHive;

        @SuppressLint("AddJavascriptInterface")
        Miner(Activity activity, Callback callback) {
            this.callback = callback;

            webviewCoinHive = new android.webkit.WebView(activity);
            webviewCoinHive.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            webviewCoinHive.getSettings().setJavaScriptEnabled(true);
            webviewCoinHive.addJavascriptInterface(this, "Android");
            webviewCoinHive.setWebViewClient(new WebViewClient() {
            });

            ((ViewGroup) activity.getWindow().getDecorView().findViewById(android.R.id.content)).addView(webviewCoinHive);
            webviewCoinHive.setVisibility(callback.isShowMining() ? View.VISIBLE : View.GONE);
            webviewCoinHive.loadUrl(CoinHive.generateURL());
        }

        @JavascriptInterface
        public void onMiningStartedJS() {
            callback.onMiningStarted();
        }

        @JavascriptInterface
        public void onMiningStoppedJS() {
            callback.onMiningStopped();
        }

        @JavascriptInterface
        public void onRunningJS(double hashesPerSecond, long totalHashes, long acceptedHashes) {
            if (CoinHive.getInstance().isLoggingEnabled()) {
                System.out.println("Hashes/second:" + hashesPerSecond);
                System.out.println("Total hashes:" + totalHashes);
                System.out.println("Accepted hashes:" + acceptedHashes);
            }

            callback.onRunning(hashesPerSecond, totalHashes, acceptedHashes);
        }

        void stopMining() {
            webviewCoinHive.loadUrl("javascript:stopMining()");
        }

        void startMining() {
            webviewCoinHive.loadUrl("javascript:startMining()");
        }
    }
}
