package com.rajesh.bitfitpart1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class InputActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_input)

        val sleepBar = findViewById<SeekBar>(R.id.sleepSeekBar)
        val moodBar = findViewById<SeekBar>(R.id.moodSeekBar)
        val noteTextInput = findViewById<EditText>(R.id.editTextTextMultiLine)
        val saveButton = findViewById<Button>(R.id.saveButton)

        val sleepTextView = findViewById<TextView>(R.id.sleep)
        val moodTextView = findViewById<TextView>(R.id.mood)

        var sleepHours: Int = 0
        var moodRating: Int = 0

        saveButton.setOnClickListener{
            val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
            val todayDate = Calendar.getInstance().time
            val dateString = dateFormat.format(todayDate)

            lifecycleScope.launch(IO) {
                (application as NoteApplication).db.articleDao().insert(
                    NoteEntity(
                        date = dateString,
                        sleepDuration = sleepHours.toFloat(),
                        mood = moodRating,
                        notes = noteTextInput.text.toString() ?:""
                    )
                )
            }

            val intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)
//            finish()
        }


        sleepBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                sleepTextView.text = "Sleep Duration : ${progress}"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Called when tracking starts
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Called when tracking stops
                sleepHours = seekBar?.progress ?: 0
            }
        })



       moodBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                moodTextView.text = "Mood : ${progress}"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Called when tracking starts
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Called when tracking stops
                moodRating = seekBar?.progress ?: 0
            }
        })


    }
}