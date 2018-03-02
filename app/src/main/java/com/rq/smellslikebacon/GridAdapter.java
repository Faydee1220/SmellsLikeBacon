package com.rq.smellslikebacon;

/**
 * Created by Faydee on 2018/3/2.
 */

class GridAdapter extends RecyclerAdapter {

    private final GridFragment.OnRecipeSelectedInterface listener;

    public GridAdapter(GridFragment.OnRecipeSelectedInterface listener) {
        this.listener = listener;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.grid_item;
    }

    @Override
    protected void onRecipeSelected(int index) {
        listener.onGridRecipeSelected(index);
    }

}
