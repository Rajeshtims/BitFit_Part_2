package com.rajesh.bitfitpart1

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import kotlin.collections.List

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes_table")
    fun getAll() : Flow<List<NoteEntity>>

    @Query("SELECT * FROM notes_table WHERE id = :pk")
    fun get(pk: Long): NoteEntity

    @Insert
    fun insert(noteEntity: NoteEntity)

    @Query("DELETE FROM notes_table")
    fun delete()
}