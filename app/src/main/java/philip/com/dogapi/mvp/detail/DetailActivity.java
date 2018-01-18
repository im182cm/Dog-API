package philip.com.dogapi.mvp.detail;

import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import philip.com.dogapi.R;
import philip.com.dogapi.util.ActivityUtils;
import philip.com.dogapi.util.Constant;
import philip.com.dogapi.listener.BreedItemListener;

public class DetailActivity extends DaggerAppCompatActivity implements BreedItemListener {
    @Inject
    DetailImageFragment mDetailContentFragment;
    @Inject
    DetailListFragment mDetailListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Set up the toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);

        // Get value and set as a Title
        String breed = getIntent().getStringExtra(Constant.EXTRA_BREED);
        ab.setTitle(breed.toUpperCase());

        DetailListFragment detailListFragment = (DetailListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.listFrame);

        DetailImageFragment detailContentFragment = (DetailImageFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);

        if (detailListFragment == null) {
            detailListFragment = mDetailListFragment;

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    detailListFragment, R.id.listFrame);
        }
        if (detailContentFragment == null) {
            detailContentFragment = mDetailContentFragment;

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    detailContentFragment, R.id.contentFrame);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBreedClick(String subBreed) {
        mDetailContentFragment.loadData(subBreed);
    }
}
