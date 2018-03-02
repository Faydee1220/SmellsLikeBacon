package com.rq.smellslikebacon;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Faydee on 2018/3/2.
 */

public class GridFragment extends Fragment {

    @BindView(R.id.recycleView)RecyclerView recyclerView;

    // 為 Activity 建立事件回呼，在片段內定義回呼介面，然後要求主要 Activity 實作。
    public interface OnRecipeSelectedInterface {
        void onGridRecipeSelected(int index);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d(LoggingFragment.TAG, "onCreateView");

        // 因為系統已經將 view 加到 container 中，第三個參數如果沒放 false 會造成重複加入
        View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);

        ButterKnife.bind(this, view);

        // 將 listener 放入 adapter
        GridFragment.OnRecipeSelectedInterface listener = (GridFragment.OnRecipeSelectedInterface) getActivity();
        GridAdapter gridAdapter = new GridAdapter(listener);
        recyclerView.setAdapter(gridAdapter);

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int numColums = (int) (dpWidth / 200);
        // 在 Fragment 內使用 getActivity()
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), numColums);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);


        return view;
    }
}