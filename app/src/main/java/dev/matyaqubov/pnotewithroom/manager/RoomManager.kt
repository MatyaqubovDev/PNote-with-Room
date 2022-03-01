package dev.matyaqubov.pnotewithroom.manager

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.matyaqubov.pnotewithroom.database.NoteDao
import dev.matyaqubov.pnotewithroom.model.Note

@Database (entities = [Note::class], version = 1)
abstract class RoomManager : RoomDatabase() {
    abstract fun noteDao(): NoteDao?

    companion object{

        @Volatile
        private var INSTANCE:RoomManager?=null
        fun getDatabase(context: Context):RoomManager{
            synchronized(this){
                if (INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context.applicationContext,RoomManager::class.java,"note_db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}