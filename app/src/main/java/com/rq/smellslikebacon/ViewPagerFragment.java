package com.rq.smellslikebacon;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Faydee on 2018/3/2.
 */

public class ViewPagerFragment extends Fragment {

    public static final String KEY_RECIPE_INDEX = "recipe_index";
    public static final String KEY_IS_INGREDIENTS = "is_ingredients";
    @BindView(R.id.viewPager) ViewPager viewPager;
    @BindView(R.id.tabLayout) TabLayout tabLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // 取得傳遞參數
        int index = getArguments().getInt(KEY_RECIPE_INDEX);
//        Toast.makeText(getActivity(), Recipes.names[index], Toast.LENGTH_SHORT).show();

        // 修改標題列
        getActivity().setTitle(Recipes.names[index]);

        View view = inflater.inflate(R.layout.fragment_viewpager, container, false);
        ButterKnife.bind(this, view);

        final IngredientsFragment ingredientsFragment = new IngredientsFragment();
        // 傳遞參數
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_RECIPE_INDEX, index);
        ingredientsFragment.setArguments(bundle);

        final DirectionsFragment directionsFragment = new DirectionsFragment();
        directionsFragment.setArguments(bundle);

        // getChildFragmentManager() 得搭配 import android.support.v4.app.Fragment;
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public android.support.v4.app.Fragment getItem(int position) {
                return position == 0 ? ingredientsFragment : directionsFragment;
            }

            @Override
            public int getCount() {
                return 2;
            }

            // 上方 Tab 的標題
            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return position == 0 ? "Ingredients" : "Directions";
            }
        });

        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    // 返回時修改標題列
    @Override
    public void onStop() {
        super.onStop();
        getActivity().setTitle(getResources().getString(R.string.app_name));
    }
}
