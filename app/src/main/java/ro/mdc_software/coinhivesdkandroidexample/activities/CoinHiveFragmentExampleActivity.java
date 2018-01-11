package ro.mdc_software.coinhivesdkandroidexample.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import ro.mdc_software.coinhivesdkandroidexample.R;

/**
 * @author Cosmin Mihu
 */
public class CoinHiveFragmentExampleActivity extends AppCompatActivity {

    protected void enableBackNavigation() {
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coinhive_fragment_example);
        enableBackNavigation();
    }
}
