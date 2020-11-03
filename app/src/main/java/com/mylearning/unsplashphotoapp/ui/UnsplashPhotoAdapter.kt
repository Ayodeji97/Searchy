package com.mylearning.unsplashphotoapp.ui

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.mylearning.unsplashphotoapp.R
import com.mylearning.unsplashphotoapp.data.UnsplashPhoto
import com.mylearning.unsplashphotoapp.databinding.ItemUnsplashPhotoBinding

class UnsplashPhotoAdapter (private val listener : OnItemClickListener) : PagingDataAdapter<UnsplashPhoto, UnsplashPhotoAdapter.PhotoViewHolder>(
    PHOTO_COMPARATOR
) {

   inner class PhotoViewHolder(private val binding: ItemUnsplashPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                // get the position of the item in the recyclerview
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null) {
                        listener.onItemClick(item)
                    }
                }

            }
        }
        fun bind(photo: UnsplashPhoto) {
            binding.apply {
                Glide.with(itemView)
                    .load(photo.urls.regular)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .into(imageView)

                  textViewUserName.text = photo.user.username


            }
        }

    }

    interface OnItemClickListener {
        fun onItemClick(photo: UnsplashPhoto)
    }

    companion object {
        private val PHOTO_COMPARATOR = object : DiffUtil.ItemCallback<UnsplashPhoto>() {
            override fun areItemsTheSame(oldItem: UnsplashPhoto, newItem: UnsplashPhoto): Boolean =

                oldItem.id == newItem.id


            override fun areContentsTheSame(
                oldItem: UnsplashPhoto,
                newItem: UnsplashPhoto
            ): Boolean =
                oldItem == newItem

        }
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {

        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bind(currentItem)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding =
            ItemUnsplashPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PhotoViewHolder(binding)
    }
}