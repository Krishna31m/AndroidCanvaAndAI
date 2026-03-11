package com.krishna.firebasesetup

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


data class User(
    val id: Int? = null,
    val name: String? = null,
    val email: String? = null
)

class MainActivity : AppCompatActivity() {

    private lateinit var etUserId: EditText
    private lateinit var etUserName: EditText
    private lateinit var etUserEmail: EditText
    private lateinit var btnCreate: Button
    private lateinit var btnRead: Button
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        etUserId = findViewById(R.id.etUserId)
        etUserName = findViewById(R.id.etUserName)
        etUserEmail = findViewById(R.id.etUserEmail)
        btnCreate = findViewById(R.id.btnCreate)
        btnRead = findViewById(R.id.btnRead)
        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)

        // Firebase reference
        database = FirebaseDatabase.getInstance().getReference("Users")

        // CREATE
        btnCreate.setOnClickListener {
            val id = etUserId.text.toString().toIntOrNull()
            val name = etUserName.text.toString()
            val email = etUserEmail.text.toString()

            if (id != null && name.isNotEmpty() && email.isNotEmpty()) {
                val user = User(id, name, email)
                database.child(id.toString()).setValue(user)
                    .addOnSuccessListener {
                        Toast.makeText(this, "User Created", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Error: ${it.message}", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        // READ
        btnRead.setOnClickListener {
            val id = etUserId.text.toString()
            if (id.isNotEmpty()) {
                database.child(id).get()
                    .addOnSuccessListener {
                        if (it.exists()) {
                            val name = it.child("name").value
                            val email = it.child("email").value
                            etUserName.setText(name.toString())
                            etUserEmail.setText(email.toString())
                            Toast.makeText(this, "User Found", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, "User Not Found", Toast.LENGTH_SHORT).show()
                        }
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Error: ${it.message}", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Enter User ID", Toast.LENGTH_SHORT).show()
            }
        }

        // UPDATE
        btnUpdate.setOnClickListener {
            val id = etUserId.text.toString()
            val name = etUserName.text.toString()
            val email = etUserEmail.text.toString()

            if (id.isNotEmpty() && name.isNotEmpty() && email.isNotEmpty()) {
                val user = User(id.toInt(), name, email)
                database.child(id).setValue(user)
                    .addOnSuccessListener {
                        Toast.makeText(this, "User Updated", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Error: ${it.message}", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        // DELETE
        btnDelete.setOnClickListener {
            val id = etUserId.text.toString()
            if (id.isNotEmpty()) {
                database.child(id).removeValue()
                    .addOnSuccessListener {
                        Toast.makeText(this, "User Deleted", Toast.LENGTH_SHORT).show()
                        etUserName.setText("")
                        etUserEmail.setText("")
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Error: ${it.message}", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Enter User ID", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

