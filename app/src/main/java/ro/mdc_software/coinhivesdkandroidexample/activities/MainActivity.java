package ro.mdc_software.coinhivesdkandroidexample.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ro.mdc_software.coinhivesdkandroidexample.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Cosmin Mihu
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bActivityExample)
    public void onActivityExampleClicked() {
        startActivity(new Intent(this, CoinHiveActivityExample.class));
    }

    @OnClick(R.id.bFragmentExample)
    public void onFragmentExampleClicked() {
        startActivity(new Intent(this, CoinHiveFragmentExampleActivity.class));
    }
}
