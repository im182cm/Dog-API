package philip.com.dogapi.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import philip.com.dogapi.contract.MainContract;
import philip.com.dogapi.R;
import philip.com.dogapi.view.adapter.MainAdapter;
import philip.com.dogapi.util.Constant;
import philip.com.dogapi.view.listener.BreedItemListener;

public class MainFragment extends Fragment implements MainContract.View {
    private static final String LOG_TAG = MainFragment.class.getSimpleName();
    private MainContract.Presenter mPresenter;
    private RecyclerView mRecyclerView;
    private MainAdapter mMainAdapter;
    private ProgressBar mProgressBar;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
        mProgressBar = (ProgressBar) root.findViewById(R.id.pb);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(container.getContext(), DividerItemDecoration.VERTICAL));
        mMainAdapter = new MainAdapter(new BreedItemListener() {
            @Override
            public void onBreedClick(String breed) {
                if (TextUtils.isEmpty(breed)) {
                    Toast.makeText(getContext(), "No sub breeds exists.", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getContext(), DetailActivity.class);
                    intent.putExtra(Constant.EXTRA_BREED, breed);
                    startActivity(intent);
                }
            }
        });
        mRecyclerView.setAdapter(mMainAdapter);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        Log.d(LOG_TAG, "setPresenter()");
        this.mPresenter = presenter;
    }

    @Override
    public void showData(ArrayList<String> breeds, ArrayList<String> subBreeds) {
        Log.d(LOG_TAG, "showData()");
        mMainAdapter.setmData(breeds, subBreeds);
    }

    @Override
    public void setProgress(boolean show) {
        Log.d(LOG_TAG, "setProgress()");
        if (mProgressBar == null)
            return;

        if (show) {
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            mProgressBar.setVisibility(View.GONE);
        }
    }
}
