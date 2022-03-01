package dev.matyaqubov.pnotewithroom.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dev.matyaqubov.pnotewithroom.MyApplication
import dev.matyaqubov.pnotewithroom.R
import dev.matyaqubov.pnotewithroom.adapter.NoteAdapter
import dev.matyaqubov.pnotewithroom.database.NoteRepository
import dev.matyaqubov.pnotewithroom.helper.AddDialog
import dev.matyaqubov.pnotewithroom.model.Note
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    companion object {
        var notes = MyApplication.notes
        var adapter = NoteAdapter(notes)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        recyclerView=findViewById(R.id.recyclerView)
        recyclerView.layoutManager=GridLayoutManager(this,1)
        recyclerView.adapter= adapter
        val add = findViewById<FloatingActionButton>(R.id.b_add_note)
        add.setOnClickListener {
            val dialog = AddDialog(this)
            dialog.show()
        }

        adapter.deleteDB={
            var repository=NoteRepository(application)
            val exacutor= Executors.newSingleThreadExecutor()
            val handler= Handler(Looper.getMainLooper())
            exacutor.execute{
                repository.removeNote(it)
                handler.post {
                  // adapter.notifyDataSetChanged()
                }
            }
        }
        adapter.deleteListItem={
            notes.removeAt(it)
            adapter.notifyDataSetChanged()
        }

    }


}