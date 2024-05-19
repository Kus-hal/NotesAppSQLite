package com.example.notesappsql

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
        import com.example.notesappsql.databinding.ActivityUpdateNoteBinding


class UpdateNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateNoteBinding
    private lateinit var db: NotesDatabaseHelper
    private var noteId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NotesDatabaseHelper(this)

        noteId= intent.getIntExtra("note_id",-1)
        if(noteId ==-1){
            finish()
            return
        }

        val note= db.getNotesById(noteId)
        binding.updateTitelEditText.setText(note.title)
        binding.updateContentEditText.setText(note.content)

        binding.updateSaveButton.setOnClickListener{
            val newTitle = binding.updateTitelEditText.text.toString()
            val newContent = binding.updateContentEditText.text.toString()
            val updateNote= Note(noteId,newTitle,newContent)
            db.updateNotes(updateNote)
            finish()
            Toast.makeText(this, "Changes Saved!", Toast.LENGTH_SHORT).show()
        }


    }
}