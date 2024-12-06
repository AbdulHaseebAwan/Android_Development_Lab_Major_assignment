package com.example.haseeb_lab_major_assignment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.haseeb_lab_major_assignment.databinding.ItemNoteBinding

class NotesAdapter(
    private val notesList: MutableList<Triple<String, String, String>>,  // Updated to store title, description, and category
    private val onDeleteClick: (Int) -> Unit
) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notesList[position]
        holder.bind(note, position)
    }

    override fun getItemCount(): Int = notesList.size

    inner class NoteViewHolder(private val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Triple<String, String, String>, position: Int) {
            binding.tvNoteTitle.text = note.first
            binding.tvNoteDescription.text = note.second
            binding.tvNoteCategory.text = note.third  // Display the category
            binding.btnDelete.setOnClickListener { onDeleteClick(position) }
        }
    }
}
