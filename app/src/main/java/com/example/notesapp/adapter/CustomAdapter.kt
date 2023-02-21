package com.example.notesapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView.OnChildClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ListMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.ItemsViewModel
import com.example.notesapp.R

class CustomAdapter(private val mList: List<ItemsViewModel>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    class NotesListAdpater(
        private var binding: ListMenuItemView
    )

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        // sets the image to the imageview from our itemHolder class
        if (ItemsViewModel.passcode == "") {
            holder.lockIcon.setImageResource(R.drawable.ic_baseline_lock_open_24)
        }

        // sets the text to the textview from our itemHolder class
        holder.title.text = ItemsViewModel.title

        holder.lastAccessed.text = ItemsViewModel.lastAccessed

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        val title: TextView = itemView.findViewById(R.id.title)
        val lastAccessed: TextView = itemView.findViewById(R.id.lastAccessed)
        val lockIcon: ImageView = itemView.findViewById(R.id.lockIcon)
    }
}