package ro.mdc_software.coinhive;

import android.support.v4.app.Fragment;

/**
 * @author Cosmin Mihu
 */
public abstract class CoinHiveFragment extends Fragment implements CoinHive.Callback {

    private CoinHive.Miner coinHiveMiner;

    @Override
    public void onStart() {
        super.onStart();

        coinHiveMiner = new CoinHive.Miner(getActivity(), this);
    }

    public void stopMining() {
        coinHiveMiner.stopMining();
    }

    public void startMining() {
        coinHiveMiner.startMining();
    }

    @Override
    public void onDestroy() {
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
