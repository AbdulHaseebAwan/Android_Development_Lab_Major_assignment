package com.example.haseeb_lab_major_assignment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.haseeb_lab_major_assignment.databinding.ActivityAddNoteBinding

class AddNoteActivity : Activity() {

    private lateinit var binding: ActivityAddNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the Spinner with categories
        val categories = arrayOf("Work", "Personal", "Others")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerCategory.adapter = adapter

        // Handle Save button click
        binding.btnSaveNote.setOnClickListener {
            val title = binding.etNoteTitle.text.toString()
            val description = binding.etNoteDescription.text.toString()
            val category = binding.spinnerCategory.selectedItem.toString()  // Get selected category

            if (title.isNotBlank() && description.isNotBlank()) {
                // Return note data to MainActivity, including category
                val resultIntent = Intent().apply {
                    putExtra("title", title)
                    putExtra("description", description)
                    putExtra("category", category)  // Send category with the note
                }
                setResult(Activity.RESULT_OK, resultIntent)
                finish()  // Close AddNoteActivity and return to MainActivity
            } else {
                // Show error messages if fields are empty
                if (title.isBlank()) {
                    binding.etNoteTitle.error = "Title is required"
                }
                if (description.isBlank()) {
                    binding.etNoteDescription.error = "Description is required"
                }
            }
        }
    }
}
