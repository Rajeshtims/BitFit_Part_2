package com.rajesh.bitfitpart1

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteDisplayAdapter(private val context: Context, private val notes: List<NoteDisplay>) : RecyclerView.Adapter<NoteDisplayAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NoteDisplayAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.fragment_entry, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteDisplayAdapter.ViewHolder, position: Int) {
        val note = notes[position]
        holder.bind(note)
    }

    override fun getItemCount() = notes.size


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private val dateTextView = itemView.findViewById<TextView>(R.id.dateTextView)
        private val sleepDurationTextView = itemView.findViewById<TextView>(R.id.sleepDurationTextView)
        private val moodTextView = itemView.findViewById<TextView>(R.id.moodTextView)
        private val noteTextView = itemView.findViewById<TextView>(R.id.noteTextView)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            // TODO: Get selected article
            val note = notes[adapterPosition]

            // TODO: Navigate to Details screen and pass selected article
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("NOTE", note)
            context.startActivity(intent)
        }

        fun bind(note: NoteDisplay) {
            dateTextView.text = note.date.toString()
            sleepDurationTextView.text = "Slept " + note.sleepDuration.toString() + " Hours"
            moodTextView.text = "Feeling " + note.mood.toString() + "/10"
            noteTextView.text = note.notes
        }
    }

}