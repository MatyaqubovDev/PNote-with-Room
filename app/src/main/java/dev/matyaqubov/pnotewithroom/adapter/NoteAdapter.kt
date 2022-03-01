package dev.matyaqubov.pnotewithroom.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.matyaqubov.pnotewithroom.R
import dev.matyaqubov.pnotewithroom.model.Note

class NoteAdapter(var notes: ArrayList<Note>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var deleteDB: ((id: Int) -> Unit)? = null
    var deleteListItem: ((position: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val note = notes[position]
        if (holder is NoteViewHolder) {
            holder.apply {
                date.text = note.date
                text.text = note.text
                item.setOnClickListener {
                    deleteDB!!.invoke(note.id!!)
                    deleteListItem!!.invoke(position)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var date: TextView = view.findViewById(R.id.tv_date)
        var item = view.findViewById<LinearLayout>(R.id.item)
        var text: TextView = view.findViewById(R.id.tv_note)
    }
}