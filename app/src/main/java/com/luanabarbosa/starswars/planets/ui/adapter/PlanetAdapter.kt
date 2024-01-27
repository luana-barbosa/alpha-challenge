package com.luanabarbosa.starswars.planets.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.luanabarbosa.starswars.databinding.ItemListBinding
import com.luanabarbosa.starswars.planets.data.model.PlanetListModel
import com.luanabarbosa.starswars.utils.extensions.ImageHelper
import com.luanabarbosa.starswars.utils.extensions.load
import com.luanabarbosa.starswars.utils.extensions.setValueOrDefault

class PlanetAdapter(
    private val listener: (item: PlanetListModel, position: Int) -> Unit
) : ListAdapter<PlanetListModel, PlanetAdapter.ViewHolder>(TaskDiffCallBack()) {

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
        val planets = getItem(position)
        with(planets) {
            holder.avatarImg.load(ImageHelper.getPlanetsImage(position + 1))
            holder.username.setValueOrDefault(name, "Unknown")
            holder.avatarImg.setOnClickListener {
                listener(planets, position)
            }
        }
    }

    class TaskDiffCallBack : DiffUtil.ItemCallback<PlanetListModel>() {
        override fun areItemsTheSame(oldItem: PlanetListModel, newItem: PlanetListModel): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: PlanetListModel,
            newItem: PlanetListModel
        ): Boolean {
            return oldItem == newItem
        }
    }
}
