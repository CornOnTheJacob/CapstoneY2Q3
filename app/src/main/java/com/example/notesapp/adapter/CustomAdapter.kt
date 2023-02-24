package com.example.notesapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView.OnChildClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ListMenuItemView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.ItemsViewModel
import com.example.notesapp.R
import com.example.notesapp.databinding.CardViewDesignBinding
import com.example.notesapp.databinding.FragmentHomeBinding
import com.example.notesapp.model.Notes

class CustomAdapter(private val onItemClicked: (Notes) -> Unit) :
    ListAdapter<Notes, CustomAdapter.ViewHolder>(DiffCallback) {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        return ViewHolder(
            CardViewDesignBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClicked(current)
        }
        holder.bind(current)
    }


    // Holds the views for adding it to image and text
    class ViewHolder(private var binding: CardViewDesignBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(note: Notes) {
            binding.apply {
                title.text = note.title
                lastAccessed.text = note.lastAccessed
                if (note.passcode == "") {
                    lockIcon.setImageResource(R.drawable.ic_baseline_lock_open_24)
                }
                else {
                    lockIcon.setImageResource(android.R.drawable.ic_lock_lock)
                }

            }
        }
    }


    companion object DiffCallback: DiffUtil.ItemCallback<Notes>() {
        override fun areItemsTheSame(oldItem: Notes, newItem: Notes): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Notes, newItem: Notes): Boolean {
            return oldItem == newItem
        }

    }
}