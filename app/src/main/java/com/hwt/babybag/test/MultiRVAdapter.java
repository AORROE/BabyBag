package com.hwt.babybag.test;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

public class MultiRVAdapter extends RecyclerView.Adapter<ItemHolder>{
    private Context context;
    private List<Item> itemList;
    private LayoutInflater inflater;

    public MultiRVAdapter(Context context, List<Item> itemList) {
        this.context = context;
        this.itemList = itemList;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return ItemHolderFactory.getItemHolder(i,viewGroup);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder itemHolder, int i) {
        itemHolder.setItemData(itemList.get(i));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return itemList.get(position).getItemType();
    }

    public void setSpanCount(GridLayoutManager gridLayoutManager){
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int i) {
                int type = getItemViewType(i);
                switch (type){
                    default:
                    case 0:
                    case 1:
                        return 2;
                    case 2:
                        return 3;
//
//
                }
            }
        });
    }
}
