package com.example.ultivictask.ui.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ultivictask.databinding.ItemFruitBinding
import com.example.ultivictask.model.local.entities.Fruit

class FruitAdapter(
    private val onFruitSelected: (Fruit) -> Unit,
    private val onFruitDeselected: (Fruit) -> Unit
) : ListAdapter<Fruit, FruitAdapter.FruitViewHolder>(FruitDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitViewHolder {
        val binding = ItemFruitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FruitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FruitViewHolder, position: Int) {
        val fruit = getItem(position)
        holder.bind(fruit)
    }

    inner class FruitViewHolder(private val binding: ItemFruitBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(fruit: Fruit) {
            binding.fruitNameTextView.text = fruit.name

            // Set initial state of the checkbox
            binding.fruitCheckBox.isChecked = false

            // Handle checkbox selection and deselection
            binding.fruitCheckBox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    onFruitSelected(fruit)
                } else {
                    onFruitDeselected(fruit)
                }
            }

            // Set item click listener (for navigating to detail page)
            binding.root.setOnClickListener {
                // Handle item click to show fruit details
                // This would involve starting a new activity and passing fruit information
            }
        }
    }

    // Use DiffUtil to calculate changes in the list
    class FruitDiffCallback : DiffUtil.ItemCallback<Fruit>() {
        override fun areItemsTheSame(oldItem: Fruit, newItem: Fruit): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Fruit, newItem: Fruit): Boolean {
            return oldItem == newItem
        }
    }
}
