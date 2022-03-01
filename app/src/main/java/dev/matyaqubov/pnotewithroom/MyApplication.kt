package dev.matyaqubov.pnotewithroom

import android.app.Application
import android.os.Handler
import android.os.Looper
import dev.matyaqubov.pnotewithroom.activity.MainActivity
import dev.matyaqubov.pnotewithroom.database.NoteRepository
import dev.matyaqubov.pnotewithroom.model.Note
import java.util.concurrent.Executors

class MyApplication :Application() {
 companion object{
     var notes=ArrayList<Note>()
 }

    override fun onCreate() {
        super.onCreate()
        loadOldNotes()
    }

    private fun loadOldNotes() {
        val repository=NoteRepository(this)
        val exacutor= Executors.newSingleThreadExecutor()
        val handler= Handler(Looper.getMainLooper())
        exacutor.execute{
           var notess =repository.getNotes()
            handler.post {
                notes.addAll(notess as ArrayList<Note>)
            }
        }

    }
}