package com.example.searchapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.searchapp.R
import com.example.searchapp.data.Photo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.photo.view.*

class PhotosAdapter(val photos: MutableList<Photo> = mutableListOf())
    : RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        return PhotosViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.photo,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = photos.size

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.bind(photos[position])
    }

    //Everytime you create recyclerview, you need to create viewholder class.

    inner class PhotosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //bind method is responsible for taking each Photo object and binding it to the views in the layout
        fun bind(photo: Photo) {
            Picasso.get().
            load(photo.url)
                .resize(IMAGE_SIDE_PX, IMAGE_SIDE_PX)
                .centerCrop()
                .into(itemView.imageView)        }
    }
}
const val IMAGE_SIDE_PX = 400
