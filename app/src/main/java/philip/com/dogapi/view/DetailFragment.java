package philip.com.dogapi.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;

import philip.com.dogapi.R;
import philip.com.dogapi.contract.DetailContract;
import philip.com.dogapi.view.adapter.DetailAdapter;

public class DetailFragment extends Fragment implements DetailContract.View{
    private static final String LOG_TAG = DetailFragment.class.getSimpleName();
    private static final String ARG_BREED = "breed";
    protected String mBreed;
    protected DetailContract.Presenter mPresenter;
    private ProgressBar mProgressBar;
    protected RecyclerView mRecyclerView;
    protected DetailAdapter mDetailAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mBreed = getArguments().getString(ARG_BREED);
        }
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
    public void setPresenter(DetailContract.Presenter presenter) {
        this.mPresenter = presenter;
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
