package philip.com.dogapi.view;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import philip.com.dogapi.R;
import philip.com.dogapi.model.Repository;
import philip.com.dogapi.presenter.DetailPresenter;
import philip.com.dogapi.util.ActivityUtils;
import philip.com.dogapi.util.Constant;
import philip.com.dogapi.view.listener.BreedItemListener;

public class DetailActivity extends AppCompatActivity implements BreedItemListener{
    private DetailContentFragment mDetailContentFragment;

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

        mDetailContentFragment = (DetailContentFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);

        if (detailListFragment == null) {
            detailListFragment = DetailListFragment.newInstance(breed);

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    detailListFragment, R.id.listFrame);
        }
        if (mDetailContentFragment == null) {
            mDetailContentFragment = DetailContentFragment.newInstance(breed);

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    mDetailContentFragment, R.id.contentFrame);
        }

        // Create the presenter
        new DetailPresenter(detailListFragment, mDetailContentFragment, Repository.getInstance(), breed);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBreedClick(String breed) {
        mDetailContentFragment.loadData(breed);
    }
}
