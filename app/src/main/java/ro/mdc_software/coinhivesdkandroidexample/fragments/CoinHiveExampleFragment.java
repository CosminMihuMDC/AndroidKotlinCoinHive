package ro.mdc_software.coinhivesdkandroidexample.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ro.mdc_software.coinhivesdkandroidexample.R;

import ro.mdc_software.coinhive.CoinHiveFragment;

/**
 * @author Cosmin Mihu
 */
public class CoinHiveExampleFragment extends CoinHiveFragment {


    public CoinHiveExampleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coinhive_example, container, false);
    }

    @Override
    public boolean isShowMining() {
        return true;
    }
}
