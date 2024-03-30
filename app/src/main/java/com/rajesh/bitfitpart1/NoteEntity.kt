package com.rajesh.bitfitpart1

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "notes_table")
class NoteEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "date")
    val date: String?,
    @ColumnInfo(name = "sleep_duration")
    val sleepDuration: Float?,
    @ColumnInfo(name = "mood")
    val mood: Int?,
    @ColumnInfo(name = "notes")
    val notes: String?,
)
