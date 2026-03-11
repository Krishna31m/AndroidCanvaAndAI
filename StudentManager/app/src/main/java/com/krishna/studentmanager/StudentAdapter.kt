package com.krishna.studentmanager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase

class StudentAdapter(private val list: ArrayList<Student>) :
    RecyclerView.Adapter<StudentAdapter.VH>() {

    private val dbRef = FirebaseDatabase.getInstance().getReference("Students")

    class VH(v: View) : RecyclerView.ViewHolder(v) {
        val txtInfo: TextView = v.findViewById(R.id.txtInfo)
        val btnDelete: ImageView = v.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_student, parent, false)
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val s = list[position]
        holder.txtInfo.text =
            "ID: ${s.id}\nName: ${s.name}\nSection: ${s.section}\nPhone: ${s.phone}"

        holder.btnDelete.setOnClickListener {
            dbRef.child(s.id).removeValue()
        }
    }

    override fun getItemCount(): Int = list.size
}
