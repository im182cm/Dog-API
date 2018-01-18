package philip.com.dogapi.mvp.detail;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;
import philip.com.dogapi.listener.BreedItemListener;

import philip.com.dogapi.util.Constant;

public class DetailListFragment extends DetailFragment implements DetailContract.View{
    private static final String ARG_BREED = "breed";
    private BreedItemListener mBreedItemListener;

    @Inject
    public DetailListFragment() {
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

        mDetailAdapter = new DetailAdapter(new BreedItemListener() {
            @Override
            public void onBreedClick(String breed) {
                mBreedItemListener.onBreedClick(breed);
            }
        }, Constant.VIEW_TYPE_TEXT);
        mRecyclerView.setAdapter(mDetailAdapter);
        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mBreedItemListener = (BreedItemListener) context;
    }
}
