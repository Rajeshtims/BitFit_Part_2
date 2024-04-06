package com.rajesh.bitfitpart1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class EntriesFragment : Fragment() {

    private val notes = mutableListOf<NoteDisplay>()
    private lateinit var notesRecyclerView: RecyclerView
    private lateinit var noteDisplayAdapter: NoteDisplayAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_entries, container, false)

        notesRecyclerView = view.findViewById(R.id.entries_recycler_view)
        val layoutManager = LinearLayoutManager(context)
        notesRecyclerView.layoutManager = layoutManager
        notesRecyclerView.setHasFixedSize(true)
        noteDisplayAdapter = NoteDisplayAdapter(view.context, notes)
        notesRecyclerView.adapter = noteDisplayAdapter

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Call the new method within onViewCreated
        fetchData()
    }

    private fun fetchData() {
        lifecycleScope.launch (Dispatchers.IO) {
            val application = requireActivity().applicationContext as NoteApplication
            application.db.articleDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    NoteDisplay(
                        entity.date,
                        entity.sleepDuration,
                        entity.mood,
                        entity.notes
                    )
                }.also { mappedList ->
//                    notesRecyclerView.adapter = NoteDisplayAdapter(this@MainActivity, mappedList)
                    notes.clear()
                    notes.addAll(mappedList)
                    noteDisplayAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = EntriesFragment()
    }
}