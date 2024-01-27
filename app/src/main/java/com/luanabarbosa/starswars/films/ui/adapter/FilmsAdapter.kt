package com.luanabarbosa.starswars.films.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.luanabarbosa.starswars.databinding.ItemListBinding
import com.luanabarbosa.starswars.films.data.model.FilmsListModel
import com.luanabarbosa.starswars.utils.extensions.ImageHelper
import com.luanabarbosa.starswars.utils.extensions.load
import com.luanabarbosa.starswars.utils.extensions.setValueOrDefault

class FilmsAdapter(
    private val listener: (item: FilmsListModel, position: Int) -> Unit
) : ListAdapter<FilmsListModel, FilmsAdapter.ViewHolder>(TaskDiffCallBack()) {

    class ViewHolder(itemView: ItemListBinding) : RecyclerView.ViewHolder(itemView.root) {

        val username: TextView = itemView.userName
        val avatarImg: ImageView = itemView.userAvatar
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val films = getItem(position)
        with(films) {
            holder.avatarImg.load(ImageHelper.getFilmsImage(position + 1))
            holder.username.setValueOrDefault(title, "Unidentified name")
            holder.avatarImg.setOnClickListener {
                listener.invoke(films, position)
            }
        }
    }

    class TaskDiffCallBack : DiffUtil.ItemCallback<FilmsListModel>() {
        override fun areItemsTheSame(oldItem: FilmsListModel, newItem: FilmsListModel): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: FilmsListModel, newItem: FilmsListModel): Boolean {
            return oldItem == newItem
        }
    }

}
