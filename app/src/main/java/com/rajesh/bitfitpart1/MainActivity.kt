package com.rajesh.bitfitpart1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers.IO



class MainActivity : AppCompatActivity() {

    private lateinit var notesRecyclerView: RecyclerView
    private val notes = mutableListOf<NoteDisplay>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "BitFit Part 1"

        notesRecyclerView = findViewById(R.id.recycler_view)
        val noteDisplayAdapter = NoteDisplayAdapter(this, notes)
        notesRecyclerView.adapter = noteDisplayAdapter

        notesRecyclerView.layoutManager = LinearLayoutManager(this).also {
            val dividerItemDecoration = DividerItemDecoration(this, it.orientation)
            notesRecyclerView.addItemDecoration(dividerItemDecoration)
        }


        lifecycleScope.launch (IO) {
            (application as NoteApplication).db.articleDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    NoteDisplay(
                        entity.date,
                        entity.sleepDuration,
                        entity.mood,
                        entity.notes
                    )
                }.also { mappedList ->
//                    notesRecyclerView.adapter = NoteDisplayAdapter(this@MainActivity, mappedList)
                    notes.addAll(mappedList)
                    noteDisplayAdapter.notifyDataSetChanged()
                }
            }
        }

        // ---------------  add onclick listener to the "ADD NEW ITEM" button
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener{
            val intent = Intent(this, InputActivity::class.java)
            this.startActivity(intent)
        }

    }
}