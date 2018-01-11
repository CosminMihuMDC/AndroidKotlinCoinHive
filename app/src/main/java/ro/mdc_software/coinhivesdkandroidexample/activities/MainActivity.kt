package ro.mdc_software.coinhivesdkandroidexample.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ro.mdc_software.coinhivesdkandroidexample.R

/**
 * @author Cosmin Mihu
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonActivityExample.setOnClickListener {
            startActivity(Intent(this, CoinHiveActivityExample::class.java))
        }

        buttonFragmentExample.setOnClickListener {
            startActivity(Intent(this, CoinHiveFragmentExampleActivity::class.java))
        }
    }
}
