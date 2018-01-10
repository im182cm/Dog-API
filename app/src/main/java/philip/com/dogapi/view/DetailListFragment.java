package philip.com.dogapi.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import philip.com.dogapi.util.Constant;
import philip.com.dogapi.view.adapter.DetailAdapter;
import philip.com.dogapi.contract.DetailContract;
import philip.com.dogapi.view.listener.BreedItemListener;

public class DetailListFragment extends DetailFragment implements DetailContract.View{
    private static final String ARG_BREED = "breed";
    private BreedItemListener mBreedItemListener;

    public DetailListFragment() {
        // Required empty public constructor
    }

    public static DetailListFragment newInstance(String breed) {
        DetailListFragment fragment = new DetailListFragment();
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
