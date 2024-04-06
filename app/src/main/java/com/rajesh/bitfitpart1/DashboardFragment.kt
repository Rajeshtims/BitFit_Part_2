package com.rajesh.bitfitpart1

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DashboardFragment : Fragment() {
    private lateinit var averageSleepTextView: TextView
    private lateinit var averageMoodTextView: TextView

//    private var averageSleep: Float = 0f


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
        averageSleepTextView = view.findViewById(R.id.averageSleepTextView)
        averageMoodTextView = view.findViewById(R.id.averageMoodTextView)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Call the new method within onViewCreated
        populateData()
    }

    private fun populateData(){
        var averageSleep = 0f
        var averageMood = 0f

        lifecycleScope.launch (Dispatchers.IO){
            val application = requireActivity().applicationContext as NoteApplication
            application.db.articleDao().getAll().collect { databaseList ->
                var sleepDurations = databaseList.map { noteEntity -> noteEntity.sleepDuration }
                var moods = databaseList.map { noteEntity -> noteEntity.mood }

                sleepDurations = sleepDurations.filterNotNull()
                moods = moods.filterNotNull()


                Log.d("Rajesh", sleepDurations.toString())
                Log.d("Rajesh", moods.toString())


                if (sleepDurations.isNotEmpty()){
                    averageSleep = sleepDurations.sum() / sleepDurations.size.toFloat()
                }
                if (moods.isNotEmpty()){
                    averageMood = moods.sum() / moods.size.toFloat()
                }

                Log.d("Rajesh", averageSleep.toString())
                Log.d("Rajesh", averageMood.toString())

                averageSleepTextView.text  = averageSleep.toString()
                averageMoodTextView.text = averageMood.toString()
            }
        }


        Log.d("Rajesh", averageSleepTextView.text.toString())
        Log.d("Rajesh", averageMoodTextView.text.toString())
    }

    companion object {
        @JvmStatic
        fun newInstance() = DashboardFragment()
    }
}