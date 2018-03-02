package com.rq.smellslikebacon;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Faydee on 2018/3/1.
 */

public class ListFragment extends Fragment {

    @BindView(R.id.listRecycleView)RecyclerView listRecycleView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // 因為系統已經將 view 加到 container 中，第三個參數如果沒放 false 會造成重複加入
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        ButterKnife.bind(this, view);
        ListAdapter listAdapter = new ListAdapter();
        listRecycleView.setAdapter(listAdapter);

        // 在 Fragment 內使用 getActivity()
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        listRecycleView.setLayoutManager(layoutManager);

        listRecycleView.setHasFixedSize(true);
        return view;
    }
}
