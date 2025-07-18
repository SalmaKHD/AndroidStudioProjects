package onlineshopapp.adapor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.salmakhd.android.androidwithjavaandxml.databinding.ViewHolderPopListBinding;

import java.util.ArrayList;

import onlineshopapp.domain.PopularDomain;

public class PopularAdaptor extends RecyclerView.Adapter<PopularAdaptor.ViewHolder> {
    ArrayList<PopularDomain> items;
    Context context;
    ViewHolderPopListBinding binding;

    public PopularAdaptor(ArrayList<PopularDomain> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       binding = ViewHolderPopListBinding.inflate(LayoutInflater.from(parent.getContext()));
       return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        binding.titleText.setText(items.get(position).getTitle());

        int drawableResource = holder.itemView.getResources().getIdentifier(items.get(position).getPicUrl(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(context)
                .load(drawableResource)
                .transform(new GranularRoundedCorners(30,30,0,0))
                .into(binding.imageView4);


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(ViewHolderPopListBinding binding) {
            super(binding.getRoot());
        }
    }
}
