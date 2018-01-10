package philip.com.dogapi.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import philip.com.dogapi.R;
import philip.com.dogapi.util.Constant;
import philip.com.dogapi.view.listener.BreedItemListener;

/**
 * Created by Philip on 2018. 1. 8..
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private ArrayList<String> mBreedsAL = new ArrayList<>();
    private ArrayList<String> mSubBreedsAL = new ArrayList<>();
    private BreedItemListener mBreedItemListener;

    public MainAdapter(BreedItemListener mBreedItemListener) {
        this.mBreedItemListener = mBreedItemListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rcy_breeds, parent, false);
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.breedTv.setText(mBreedsAL.get(position));
        holder.subBreedTv.setText(mSubBreedsAL.get(position));
    }

    public void setmData(ArrayList<String> breeds, ArrayList<String> subBreeds) {
        if (breeds == null || subBreeds == null)
            return;

        mBreedsAL.clear();
        mSubBreedsAL.clear();

        mBreedsAL.addAll(breeds);
        mSubBreedsAL.addAll(subBreeds);

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mBreedsAL.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView breedTv, subBreedTv;

        public ViewHolder(View itemView) {
            super(itemView);
            breedTv = (TextView) itemView.findViewById(R.id.breedTv);
            subBreedTv = (TextView) itemView.findViewById(R.id.subBreedTv);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mBreedsAL.size() < 1)
                        return;

                    if (Constant.NO_SUB.equals(mSubBreedsAL.get(getAdapterPosition()))) {
                        mBreedItemListener.onBreedClick(null);
                    } else {
                        mBreedItemListener.onBreedClick(mBreedsAL.get(getAdapterPosition()));
                    }
                }
            });
        }
    }
}
