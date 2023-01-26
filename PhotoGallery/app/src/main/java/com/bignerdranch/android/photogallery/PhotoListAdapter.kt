package com.bignerdranch.android.photogallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bignerdranch.android.photogallery.api.GalleryItem
import com.bignerdranch.android.photogallery.databinding.ListItemGalleryBinding

class PhotoListAdapter (
    private val galleryItems: List<GalleryItem>
): RecyclerView.Adapter<PhotoListAdapter.PhotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        // inflate the ViewHolder and pass a reference to the layout for manipulation to the
        // ViewHolder class
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemGalleryBinding.inflate(inflater, parent, false)
        return PhotoViewHolder(binding)
    }

    override fun getItemCount(): Int = galleryItems.size

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        // update the ViewHolder with data
        holder.bind(galleryItems[position])
    }

    class PhotoViewHolder(
        private val binding: ListItemGalleryBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(galleryItem: GalleryItem) {
            // use the load() extension function Coil provides for loading pictures to
            // an ImageView
            binding.itemImageView.load(galleryItem.url) {
                // add a placeholder to the image
                placeholder(R.drawable.image_placeholder)
            }
        }
    }

}