package com.krishna.todolist

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {

    private lateinit var etUserId: EditText
    private lateinit var etTitle: EditText
    private lateinit var etDesc: EditText
    private lateinit var btnCreate: Button
    private lateinit var btnRead: Button
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button

    private lateinit var btnListAll: Button
    private lateinit var tvResult: TextView


    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etUserId = findViewById(R.id.etUserId)
        etTitle = findViewById(R.id.etTitle)
        etDesc = findViewById(R.id.etDesc)
        btnCreate = findViewById(R.id.btnCreate)
        btnRead = findViewById(R.id.btnRead)
        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
        btnListAll = findViewById(R.id.btnListAll)
        tvResult = findViewById(R.id.tvResult)

        database = FirebaseDatabase.getInstance().getReference("Todos")

        btnCreate.setOnClickListener {
            val id = etUserId.text.toString().toIntOrNull()
            val title = etTitle.text.toString()
            val desc = etDesc.text.toString()

            if (id != null && title.isNotEmpty() && desc.isNotEmpty()) {
                val todo = Todo(id, title, desc)
                database.child(id.toString()).setValue(todo)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Todo Created", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    }
            }
        }

        btnRead.setOnClickListener {
            val id = etUserId.text.toString()
            if (id.isNotEmpty()) {
                database.child(id).get().addOnSuccessListener {
                    if (it.exists()) {
                        etTitle.setText(it.child("title").value.toString())
                        etDesc.setText(it.child("description").value.toString())
                    } else {
                        Toast.makeText(this, "Not Found", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        btnUpdate.setOnClickListener {
            val id = etUserId.text.toString()
            val title = etTitle.text.toString()
            val desc = etDesc.text.toString()

            if (id.isNotEmpty()) {
                val todo = Todo(id.toInt(), title, desc)
                database.child(id).setValue(todo)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show()
                    }
            }
        }

        btnDelete.setOnClickListener {
            val id = etUserId.text.toString()
            if (id.isNotEmpty()) {
                database.child(id).removeValue()
                    .addOnSuccessListener {
                        etTitle.setText("")
                        etDesc.setText("")
                        Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()
                    }
            }
        }

        btnListAll.setOnClickListener {

            database.addListenerForSingleValueEvent(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {

                    val builder = StringBuilder()

                    if (snapshot.exists()) {

                        for (todoSnap in snapshot.children) {

                            val todo = todoSnap.getValue(Todo::class.java)

                            builder.append("ID: ${todo?.id}\n")
                            builder.append("Title: ${todo?.title}\n")
                            builder.append("Desc: ${todo?.description}\n\n")
                        }

                        tvResult.text = builder.toString()

                    } else {
                        tvResult.text = "No Todos Found"
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    tvResult.text = error.message
                }
            })
        }

    }
}
