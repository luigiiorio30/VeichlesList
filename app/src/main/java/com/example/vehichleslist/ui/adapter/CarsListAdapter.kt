package com.example.vehichleslist.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.vehichleslist.databinding.ListItemCarBinding
import com.example.vehichleslist.model.Cars

class CarsListAdapter(
    private val clickListener: (Cars) -> Unit
) : ListAdapter<Cars, CarsListAdapter.CarsViewHolder>(DiffCallback) {

    class CarsViewHolder(
        private var binding: ListItemCarBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(forageable: Cars) {
            binding.cars = forageable
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<Cars>() {
        override fun areItemsTheSame(oldItem: Cars, newItem: Cars): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Cars, newItem: Cars): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CarsViewHolder(
            ListItemCarBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CarsViewHolder, position: Int) {
        val cars = getItem(position)
        holder.itemView.setOnClickListener{
            clickListener(cars)
        }
        holder.bind(cars)
    }
}
