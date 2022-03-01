package dev.matyaqubov.pnotewithroom.database

import android.app.Application
import dev.matyaqubov.pnotewithroom.manager.RoomManager
import dev.matyaqubov.pnotewithroom.model.Note

class NoteRepository {

    lateinit var noteDao: NoteDao

    constructor(application: Application){
        val db= RoomManager.getDatabase(application)
        noteDao=db.noteDao()!!
    }

    fun getNotes():List<Note>{
        return noteDao.getNotes()
    }
    fun saveNote(note: Note){
        noteDao.saveNote(note)
    }

    fun removeNote(postion:Int){
        noteDao.removeNote(postion)
    }

}