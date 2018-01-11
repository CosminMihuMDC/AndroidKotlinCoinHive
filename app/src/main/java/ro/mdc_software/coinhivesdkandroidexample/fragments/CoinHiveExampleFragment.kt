package ro.mdc_software.coinhivesdkandroidexample.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ro.mdc_software.coinhive.CoinHiveFragment
import ro.mdc_software.coinhivesdkandroidexample.R

/**
 * @author Cosmin Mihu
 */
class CoinHiveExampleFragment : CoinHiveFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater?.inflate(R.layout.fragment_coinhive_example, container, false)
    }

    override fun isShowMining() = true
}
