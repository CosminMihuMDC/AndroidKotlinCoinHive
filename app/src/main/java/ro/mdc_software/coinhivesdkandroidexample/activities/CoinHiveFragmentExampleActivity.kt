package ro.mdc_software.coinhivesdkandroidexample.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem

import ro.mdc_software.coinhivesdkandroidexample.R

/**
 * @author Cosmin Mihu
 */
class CoinHiveFragmentExampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coinhive_fragment_example)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem) =
            if (item.itemId == android.R.id.home) {
                onBackPressed()
                true
            } else {
                super.onOptionsItemSelected(item)
            }
}
