package com.example.oblig3.overview

import android.provider.ContactsContract.Contacts.Photo
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.oblig3.network.PhotoImage
import com.example.oblig3.databinding.GridViewItemBinding

/*
  onItemClicked er en lamda-funksjon som tar i mot et ArtPhoto-objekt og
  returnerer Unit (ingenting). Denne kan f.eks. se slik ut:
  {artPhoto ->
      sharedViewModel.onArtPhotoSelected(artPhoto)

findNavController().navigate(R.id.action_allPhotosFragment_to_photoFragment
)
  }
 */
class PhotoGridAdapter(private val onItemClicked: (PhotoImage) -> Unit):
    ListAdapter<PhotoImage, PhotoGridAdapter.ArtPhotoViewHolder>(DiffCallback) {

    class ArtPhotoViewHolder(private var binding:
                             GridViewItemBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(artPhoto: PhotoImage) {
            binding.photo = artPhoto
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            PhotoGridAdapter.ArtPhotoViewHolder {
        return ArtPhotoViewHolder(GridViewItemBinding.inflate(
            LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder:
                                  PhotoGridAdapter.ArtPhotoViewHolder, position: Int) {
        val artPhoto = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClicked(artPhoto)
        }
        holder.bind(artPhoto)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<PhotoImage>() {
        override fun areItemsTheSame(oldItem: PhotoImage, newItem: PhotoImage):
                Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PhotoImage, newItem:
        PhotoImage): Boolean {
            return oldItem.thumbnailUrl == newItem.thumbnailUrl
        }
    }
}