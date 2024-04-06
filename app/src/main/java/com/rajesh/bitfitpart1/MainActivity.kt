package com.rajesh.bitfitpart1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

//    private lateinit var notesRecyclerView: RecyclerView
//    private val notes = mutableListOf<NoteDisplay>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "BitFit Part 2"

        replaceFragment(EntriesFragment())

        val entriesFragment = EntriesFragment()
        val dashboardFragment = DashboardFragment()


        // handle clicks on the bottom bar
        val navBar: BottomNavigationView = findViewById(R.id.bottom_navigation)
        // set default selected item
        navBar.selectedItemId = R.id.nav_entries

        navBar.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.nav_entries -> fragment = entriesFragment
                R.id.nav_dashboard -> fragment = dashboardFragment
            }
            replaceFragment(fragment)
            true
        }


        // ---------------  add onclick listener to the "ADD NEW ITEM" button
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener{
            val intent = Intent(this, InputActivity::class.java)
            this.startActivity(intent)
        }
    }


//    override fun onResume() {
//        super.onResume()
//
//    }

    private fun replaceFragment(entriesFragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.notes_frame_layout, entriesFragment)
        fragmentTransaction.commit()
    }



}