package com.rajesh.bitfitpart1

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_detail)

        // get the text views and fill them
        val note = intent.getSerializableExtra("NOTE") as NoteDisplay

        val dateTextView = findViewById<TextView>(R.id.dateTextView)
        val durationTextView = findViewById<TextView>(R.id.durationTextView)
        val moodTextView = findViewById<TextView>(R.id.moodTextView)
        val notesTextView = findViewById<TextView>(R.id.notesTextView)

        dateTextView.text = note.date
        durationTextView.text = note.sleepDuration.toString() + " Hours"
        moodTextView.text = note.mood.toString() + "/10"
        notesTextView.text = note.notes

    }
}