package philip.com.dogapi.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import philip.com.dogapi.contract.DetailContract;
import philip.com.dogapi.util.Constant;
import philip.com.dogapi.view.adapter.DetailAdapter;

public class DetailContentFragment extends DetailFragment implements DetailContract.View {
    private static final String ARG_BREED = "breed";

    public DetailContentFragment() {
        // Required empty public constructor
    }

    public static DetailContentFragment newInstance(String breed) {
        DetailContentFragment fragment = new DetailContentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_BREED, breed);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = super.onCreateView(inflater, container, savedInstanceState);

        mDetailAdapter = new DetailAdapter(Glide.with(getContext()), Constant.VIEW_TYPE_IMAGE);
        mRecyclerView.setAdapter(mDetailAdapter);
        return root;
    }

    public void loadData(String breed) {
        if (!mBreed.equals(breed)) {
            mBreed = breed;
            mPresenter.setBreed(breed);
        }
    }
}
