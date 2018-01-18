package philip.com.dogapi.mvp.detail;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import philip.com.dogapi.R;

public class DetailFragment extends DaggerFragment implements DetailContract.View{
    @Inject
    DetailContract.Presenter mPresenter;

    @Inject
    String mBreed;

    private static final String LOG_TAG = DetailFragment.class.getSimpleName();
    private static final String ARG_BREED = "breed";
    private ProgressBar mProgressBar;
    protected RecyclerView mRecyclerView;
    protected DetailAdapter mDetailAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_detail, container, false);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
        mProgressBar = (ProgressBar) root.findViewById(R.id.pb);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(container.getContext(), DividerItemDecoration.VERTICAL));
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void showData(ArrayList<String> subBreeds) {
        mDetailAdapter.setmData(subBreeds);
    }

    @Override
    public void setProgress(boolean show) {
        Log.d(LOG_TAG, "setProgress():"+show);
        if (mProgressBar == null)
            return;

        if (show) {
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            mProgressBar.setVisibility(View.GONE);
        }
    }
}
