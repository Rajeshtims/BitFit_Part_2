package com.rajesh.bitfitpart1

import android.app.Application

class NoteApplication : Application() {
    val db by lazy { AppDatabase.getInstance(this) }
}