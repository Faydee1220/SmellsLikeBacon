package com.rq.smellslikebacon;

/**
 * Created by Faydee on 2018/3/2.
 */

public class DirectionsFragment extends CheckBoxesFragment {
    @Override
    public String[] getContents(int index) {
        return Recipes.directions[index].split("`");
    }
}
