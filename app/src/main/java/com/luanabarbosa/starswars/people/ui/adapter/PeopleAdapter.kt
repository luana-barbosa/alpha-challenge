package com.luanabarbosa.starswars.people.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.luanabarbosa.starswars.databinding.ItemListBinding
import com.luanabarbosa.starswars.people.data.model.PeopleListModel
import com.luanabarbosa.starswars.utils.extensions.ImageHelper
import com.luanabarbosa.starswars.utils.extensions.load
import com.luanabarbosa.starswars.utils.extensions.setValueOrDefault

class PeopleAdapter(
    private val listener: (item: PeopleListModel, position: Int) -> Unit
) : ListAdapter<PeopleListModel, PeopleAdapter.ViewHolder>(TaskDiffCallBack()) {

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
        val people = getItem(position)
        with(people) {
            holder.avatarImg.load(ImageHelper.getPeopleImage(position + 1))
            holder.username.setValueOrDefault(name, "Unknown")
            holder.avatarImg.setOnClickListener {
                listener.invoke(people, position)
            }
        }
    }

    class TaskDiffCallBack : DiffUtil.ItemCallback<PeopleListModel>() {
        override fun areItemsTheSame(oldItem: PeopleListModel, newItem: PeopleListModel): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: PeopleListModel,
            newItem: PeopleListModel
        ): Boolean {
            return oldItem == newItem
        }
    }
}
