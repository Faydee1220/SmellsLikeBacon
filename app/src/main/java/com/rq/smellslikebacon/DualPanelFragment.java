package com.rq.smellslikebacon;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

import static com.rq.smellslikebacon.ViewPagerFragment.KEY_RECIPE_INDEX;

/**
 * Created by Faydee on 2018/3/3.
 */

public class DualPanelFragment extends Fragment {

    private static final String INGREDIENTS_FRAGMENT = "ingredients_fragment";
    private static final String DIRECTIONS_FRAGMENT = "directions_fragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 取得傳遞參數
        int index = getArguments().getInt(KEY_RECIPE_INDEX);
//        Toast.makeText(getActivity(), Recipes.names[index], Toast.LENGTH_SHORT).show();

        // 修改標題列
        getActivity().setTitle(Recipes.names[index]);

        View view = inflater.inflate(R.layout.fragment_dualpanel, container, false);
        ButterKnife.bind(this, view);

        FragmentManager fragmentManager = getChildFragmentManager();

        // 左方的 Ingredients
        IngredientsFragment savedIngredientsFragment = (IngredientsFragment) fragmentManager
                .findFragmentByTag(INGREDIENTS_FRAGMENT);

        if (savedIngredientsFragment == null) {
            IngredientsFragment ingredientsFragment = new IngredientsFragment();
            // 傳遞參數
            Bundle bundle = new Bundle();
            bundle.putInt(KEY_RECIPE_INDEX, index);
            ingredientsFragment.setArguments(bundle);

            fragmentManager.beginTransaction()
                    .add(R.id.leftPlaceholder, ingredientsFragment, INGREDIENTS_FRAGMENT)
                    .commit();
        }

        // 右方的 Directions
        DirectionsFragment savedDirectionsFragment = (DirectionsFragment) fragmentManager
                .findFragmentByTag(DIRECTIONS_FRAGMENT);

        if (savedDirectionsFragment == null) {
            DirectionsFragment directionsFragment = new DirectionsFragment();
            // 傳遞參數
            Bundle bundle = new Bundle();
            bundle.putInt(KEY_RECIPE_INDEX, index);
            directionsFragment.setArguments(bundle);

            fragmentManager.beginTransaction()
                    .add(R.id.rightPlaceholder, directionsFragment, DIRECTIONS_FRAGMENT)
                    .commit();
        }

        return view;
    }

    // 返回時修改標題列
    @Override
    public void onStop() {
        super.onStop();
        getActivity().setTitle(getResources().getString(R.string.app_name));
    }
}
