package com.salmakhd.android.onlineshop;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.salmakhd.android.onlineshop.databinding.CafeItemLayoutBinding;
import com.salmakhd.android.onlineshop.model.CafeItem;

import java.util.ArrayList;

public class CafeItemsAdapter extends RecyclerView.Adapter<CafeItemsAdapter.ViewHolder> {
    private final Context context;
    private ArrayList<CafeItem> items = new ArrayList<>();

    public CafeItemsAdapter(Context context, ArrayList<CafeItem> items) {
        this.context = context;
        this.items = items;
    }

    public void updateListItems(ArrayList<CafeItem> items) {
        this.items = items;
        for(CafeItem item: items) {
            Log.i("OnlineShop", "Item is" + item.toString());
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(CafeItemLayoutBinding.inflate(LayoutInflater.from(context),parent, false ));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBindView(items.get(position));
    }

    @Override
    public int getItemCount() {
        Log.i("shop", "number of items: " + items.size());
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final CafeItemLayoutBinding binding;

        public void onBindView(@NonNull CafeItem item) {
            binding.itemName.setText(item.getName());
            binding.itemDescription.setText(item.getDescription());
            Glide.with(itemView.getContext())
                    .load(Uri.parse(item.getImgUrl()))
                    .transform(new GranularRoundedCorners(30,30,0,0))
                    .into(binding.itemPic);
        }

        public ViewHolder(@NonNull CafeItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}