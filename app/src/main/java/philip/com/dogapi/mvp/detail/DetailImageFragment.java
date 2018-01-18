package philip.com.dogapi.mvp.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

import philip.com.dogapi.util.Constant;

public class DetailImageFragment extends DetailFragment implements DetailContract.View {
    @Inject
    public DetailImageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter.takeView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = super.onCreateView(inflater, container, savedInstanceState);

        mDetailAdapter = new DetailAdapter(Glide.with(getContext()), Constant.VIEW_TYPE_IMAGE);
        mRecyclerView.setAdapter(mDetailAdapter);
        return root;
    }

    public void loadData(String subBreed) {
        mPresenter.setSubBreed(subBreed);
    }
}
