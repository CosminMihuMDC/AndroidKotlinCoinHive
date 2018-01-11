package ro.mdc_software.coinhive

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import android.widget.LinearLayout

/**
 * TODO description
 *
 * @author Cosmin Mihu
 * @date 11-January-2018
 */

internal class Miner(activity: Activity,
                     private val callback: CoinHive.Callback) {

    private val webViewCoinHive: android.webkit.WebView = android.webkit.WebView(activity)

    init {
        webViewCoinHive.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        webViewCoinHive.settings.javaScriptEnabled = true
        webViewCoinHive.addJavascriptInterface(this, "Android")

        (activity.window.decorView.findViewById(android.R.id.content) as ViewGroup).addView(webViewCoinHive)
        webViewCoinHive.visibility = if (callback.isShowMining()) View.VISIBLE else View.GONE
        webViewCoinHive.loadUrl(CoinHive.generateURL())
    }

    @JavascriptInterface
    fun onMiningStartedJS() = callback.onMiningStarted()

    @JavascriptInterface
    fun onMiningStoppedJS() = callback.onMiningStopped()

    @JavascriptInterface
    fun onRunningJS(hashesPerSecond: Double, totalHashes: Long, acceptedHashes: Long) {
        if (CoinHive.loggingEnabled) {
            println("Hashes/second:" + hashesPerSecond)
            println("Total hashes:" + totalHashes)
            println("Accepted hashes:" + acceptedHashes)
        }

        callback.onRunning(hashesPerSecond, totalHashes, acceptedHashes)
    }

    fun stopMining() = webViewCoinHive.loadUrl("javascript:stopMining()")

    fun startMining() = webViewCoinHive.loadUrl("javascript:startMining()")
}