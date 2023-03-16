package com.example.ktorkotlincorotines.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ktorkotlincorotines.databinding.ItemLayoutBinding
import com.example.ktorkotlincorotines.model.User

class UserAdapter: ListAdapter<User, UserAdapter.VHolder>(DiffCallback()) {
    lateinit var onClick: (id: Int) -> Unit
    private class DiffCallback: DiffUtil.ItemCallback<User>(){
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        return VHolder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class VHolder(private val binding: ItemLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(user: User){
            with(binding){
                Glide.with(imageView).load(user.avatar).into(imageView)
                textUsername.text = user.username
            }
            itemView.setOnClickListener {
                onClick.invoke(user.id)
            }
        }
    }
}