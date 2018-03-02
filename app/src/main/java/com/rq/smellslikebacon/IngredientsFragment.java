package com.rq.smellslikebacon;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Faydee on 2018/3/2.
 */

public class IngredientsFragment extends Fragment {
    private static final String KEY_CHECKED_BOXES = "key_checked_boxes";

    @BindView(R.id.ingredientsLayout) LinearLayout ingredientsLayout;

    private CheckBox[] checkBoxes;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int index = getArguments().getInt(ViewPagerFragment.KEY_RECIPE_INDEX);

        View view = inflater.inflate(R.layout.fragment_ingredients, container, false);
        ButterKnife.bind(this, view);

        String[] ingredients = Recipes.ingredients[index].split("`");
        checkBoxes = new CheckBox[ingredients.length];

        // 修正轉向時已勾選的項目會被清空的問題
        boolean[] checkedBoxes = new boolean[checkBoxes.length];
        if (savedInstanceState != null && savedInstanceState.getBooleanArray(KEY_CHECKED_BOXES) != null) {
            checkedBoxes = savedInstanceState.getBooleanArray(KEY_CHECKED_BOXES);
        }

        setupCheckBoxes(ingredients, checkedBoxes);

        return view;
    }

    private void setupCheckBoxes(String[] ingredients, boolean[] checkedBoxes) {
        int i = 0;
        for (String ingredient : ingredients) {
            checkBoxes[i] = new CheckBox(getActivity());
            checkBoxes[i].setPadding(8, 16, 8 ,16);
            checkBoxes[i].setTextSize(20f);
            checkBoxes[i].setText(ingredient);
            ingredientsLayout.addView(checkBoxes[i]);
            // 修正轉向時已勾選的項目會被清空的問題
            if (checkedBoxes[i]) {
                checkBoxes[i].toggle();
            }
            i += 1;
        }
    }

    // 修正轉向時已勾選的項目會被清空的問題
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        boolean[] stateOfCheckBoxes = new boolean[checkBoxes.length];
        int i = 0;
        for (CheckBox checkBox : checkBoxes) {
            stateOfCheckBoxes[i] = checkBox.isChecked();
            i += 1;
        }
        outState.putBooleanArray(KEY_CHECKED_BOXES, stateOfCheckBoxes);
        super.onSaveInstanceState(outState);
    }
}
