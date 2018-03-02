package com.rq.smellslikebacon;

/**
 * Created by Faydee on 2018/3/2.
 */

public class IngredientsFragment extends CheckBoxesFragment {
    @Override
    public String[] getContents(int index) {
        return Recipes.ingredients[index].split("`");
    }
}
