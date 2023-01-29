package com.bignerdranch.android.photogallery

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bignerdranch.android.photogallery.api.GalleryItem
import com.bignerdranch.android.photogallery.databinding.ListItemGalleryBinding

class PhotoListAdapter (
    private val galleryItems: List<GalleryItem>,
    private val onItemClicked: (Uri) -> Unit
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
        holder.bind(galleryItems[position], onItemClicked)
    }

    class PhotoViewHolder(
        private val binding: ListItemGalleryBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        // the lambda expression will be used for setting a listener
        fun bind(galleryItem: GalleryItem, onItemClicked: (Uri) -> Unit) {

            // use the load() extension function Coil provides for loading pictures to
            // an ImageView
            binding.itemImageView.load(galleryItem.url) {
                // add a placeholder to the image
                placeholder(R.drawable.image_placeholder)
            }
            // set a listener for the ViewHolder
            binding.root.setOnClickListener { onItemClicked(galleryItem.photoPageUri) }
        }
    }

}