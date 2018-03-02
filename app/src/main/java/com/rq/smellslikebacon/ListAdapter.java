package com.rq.smellslikebacon;

/**
 * Created by Faydee on 2018/3/2.
 */

public class ListAdapter extends RecyclerAdapter {

    private final ListFragment.OnRecipeSelectedInterface listener;

    public ListAdapter(ListFragment.OnRecipeSelectedInterface listener) {
        this.listener = listener;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.list_item;
    }

    @Override
    protected void onRecipeSelected(int index) {
        listener.onListRecipeSelected(index);
    }

}
