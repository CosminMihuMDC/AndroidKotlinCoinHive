package ro.mdc_software.coinhivesdkandroidexample.activities

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import ro.mdc_software.coinhive.CoinHiveActivity
import ro.mdc_software.coinhivesdkandroidexample.R

/**
 * @author Cosmin Mihu
 */
class CoinHiveActivityExample : CoinHiveActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coinhive_example)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem) =
            if (item.itemId == android.R.id.home) {
                onBackPressed()
                true
            } else {
                super.onOptionsItemSelected(item)
            }

    override fun isShowMining() = true
}
