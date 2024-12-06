package com.example.haseeb_lab_major_assignment

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.haseeb_lab_major_assignment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val notesList = mutableListOf<Triple<String, String, String>>() // Store title, description, and category
    private lateinit var adapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up RecyclerView with NotesAdapter
        adapter = NotesAdapter(notesList) { position ->
            notesList.removeAt(position)
            adapter.notifyItemRemoved(position)
            Toast.makeText(this, "Note Deleted", Toast.LENGTH_SHORT).show()
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        // Floating Action Button to navigate to AddNoteActivity
        binding.fabAddNote.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            startActivityForResult(intent, 101)  // Open AddNoteActivity to add new note
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101 && resultCode == RESULT_OK) {
            val title = data?.getStringExtra("title") ?: ""
            val description = data?.getStringExtra("description") ?: ""
            val category = data?.getStringExtra("category") ?: ""  // Get category from AddNoteActivity
            notesList.add(Triple(title, description, category)) // Store title, description, and category
            adapter.notifyItemInserted(notesList.size - 1)
        }
    }
}
