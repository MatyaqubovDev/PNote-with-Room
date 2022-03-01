package dev.matyaqubov.pnotewithroom.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.matyaqubov.pnotewithroom.model.Note

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveNote(note:Note):Long

    @Query("SELECT * FROM roomNote")
    fun getNotes():List<Note>
}