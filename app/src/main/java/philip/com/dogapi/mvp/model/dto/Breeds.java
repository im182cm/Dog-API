package philip.com.dogapi.mvp.model.dto;

import java.util.ArrayList;

public class Breeds {
    private final ArrayList<String> mBreedAL;
    private final ArrayList<String> mSubBreedAL;

    public Breeds(ArrayList<String> mBreedAL, ArrayList<String> mSubBreedAL) {
        this.mBreedAL = mBreedAL;
        this.mSubBreedAL = mSubBreedAL;
    }

    public ArrayList<String> getmBreedAL() {
        return mBreedAL;
    }

    public ArrayList<String> getmSubBreedAL() {
        return mSubBreedAL;
    }
}
