package com.farhan.suitmediatest.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.farhan.suitmediatest.databinding.UserItemBinding
import com.farhan.suitmediatest.response.DataItem

class UserAdapter(
    private val onClick: (DataItem) -> Unit
) : ListAdapter<DataItem, UserAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }

    inner class MyViewHolder(
        private val binding: UserItemBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: DataItem) {
            binding.tvItemName.text = user.firstName
            Glide.with(binding.root.context)
                .load(user.avatar)
                .circleCrop()
                .into(binding.imgItemPhoto)
            binding.tvItemEmail.text = user.email

            itemView.setOnClickListener {
                onClick(user)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}