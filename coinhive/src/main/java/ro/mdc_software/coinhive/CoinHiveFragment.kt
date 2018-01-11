package ro.mdc_software.coinhive

import android.support.v4.app.Fragment

/**
 * @author Cosmin Mihu
 */
abstract class CoinHiveFragment : Fragment(), CoinHive.Callback {

    private lateinit var coinHiveMiner: Miner

    override fun onStart() {
        super.onStart()

        coinHiveMiner = Miner(activity, this)
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
