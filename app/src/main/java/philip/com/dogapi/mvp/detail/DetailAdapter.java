package philip.com.dogapi.mvp.detail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import philip.com.dogapi.R;
import philip.com.dogapi.util.Constant;
import philip.com.dogapi.listener.BreedItemListener;

import static com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions.withCrossFade;

/**
 * Created by Philip on 2018. 1. 8..
 */

public class DetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<String> mItems = new ArrayList<>();
    private BreedItemListener mItemListener;
    private RequestManager mRequestManager;
    private int mVewType;

    public DetailAdapter(BreedItemListener mBreedItemListener, int viewType) {
        this.mItemListener = mBreedItemListener;
        this.mVewType = viewType;
    }

    public DetailAdapter(RequestManager requestManager, int viewType) {
        this.mRequestManager = requestManager;
        this.mVewType = viewType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case Constant.VIEW_TYPE_IMAGE:
                return new ImageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rcy_image, parent, false));
            default:
                return new TextViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rcy_subbreed, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case Constant.VIEW_TYPE_IMAGE:
                ImageViewHolder ivh = (ImageViewHolder) holder;
                mRequestManager.asBitmap().load(mItems.get(position)).apply(RequestOptions.placeholderOf(R.drawable.ic_launcher_foreground)).transition(withCrossFade()).into(ivh.breedIv);
                break;
            default:
                TextViewHolder tvh = (TextViewHolder) holder;
                tvh.subBreedTv.setText(mItems.get(position));

        }
    }

    public void setmData(ArrayList<String> subBreeds) {
        if (subBreeds == null)
            return;

        mItems.clear();

        mItems.addAll(subBreeds);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return mVewType;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class TextViewHolder extends RecyclerView.ViewHolder {
        private TextView subBreedTv;

        public TextViewHolder(View itemView) {
            super(itemView);
            subBreedTv = (TextView) itemView.findViewById(R.id.subBreedTv);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItems.size() < 1)
                        return;

                    mItemListener.onBreedClick(mItems.get(getAdapterPosition()));
                }
            });
        }
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        private ImageView breedIv;

        public ImageViewHolder(View itemView) {
            super(itemView);
            breedIv = (ImageView) itemView.findViewById(R.id.dogIv);
        }
    }
}
