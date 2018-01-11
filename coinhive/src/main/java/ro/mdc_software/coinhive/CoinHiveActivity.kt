package ro.mdc_software.coinhive

import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity

/**
 * @author Cosmin Mihu
 */
abstract class CoinHiveActivity : AppCompatActivity(), CoinHive.Callback {

    private lateinit var coinHiveMiner: Miner

    override fun setContentView(@LayoutRes layoutResID: Int) {
        super.setContentView(layoutResID)

        coinHiveMiner = Miner(this, this)
    }

    fun stopMining() {
        coinHiveMiner.stopMining()
    }

    fun startMining() {
        coinHiveMiner.startMining()
    }

    override fun onDestroy() {
        if (CoinHive.loggingEnabled) {
            println("Mining stopped")
        }
        stopMining()
        super.onDestroy()
    }

    override fun onMiningStarted() {}

    override fun onMiningStopped() {}

    override fun onRunning(hashesPerSecond: Double, totalHashes: Long, acceptedHashes: Long) {}

    override fun isShowMining() = false
}
