package philip.com.dogapi.model.dto;

import java.util.ArrayList;

/**
 * Created by Philip on 2018. 1. 8..
 */

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
