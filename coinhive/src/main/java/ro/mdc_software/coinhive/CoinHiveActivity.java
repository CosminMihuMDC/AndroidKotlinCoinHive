package ro.mdc_software.coinhive;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

/**
 * @author Cosmin Mihu
 */
public abstract class CoinHiveActivity extends AppCompatActivity implements CoinHive.Callback {

    private CoinHive.Miner coinHiveMiner;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);

        coinHiveMiner = new CoinHive.Miner(this, this);
    }

    public void stopMining() {
        coinHiveMiner.stopMining();
    }

    public void startMining() {
        coinHiveMiner.startMining();
    }

    @Override
    protected void onDestroy() {
        if (CoinHive.getInstance().isLoggingEnabled()) {
            System.out.println("Mining stopped");
        }
        stopMining();
        super.onDestroy();
    }

    @Override
    public boolean isShowMining() {
        return false;
    }

    @Override
    public void onMiningStarted() {
    }

    @Override
    public void onMiningStopped() {
    }

    @Override
    public void onRunning(double hashesPerSecond, long totalHashes, long acceptedHashes) {
    }
}
