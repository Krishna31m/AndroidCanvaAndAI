package com.krishna.studentmanager
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {

    private lateinit var etId: EditText
    private lateinit var etName: EditText
    private lateinit var etSection: EditText
    private lateinit var etPhone: EditText

    private lateinit var btnAdd: Button
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button
    private lateinit var btnShow: Button

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StudentAdapter
    private val list = ArrayList<Student>()

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etId = findViewById(R.id.etId)
        etName = findViewById(R.id.etName)
        etSection = findViewById(R.id.etSection)
        etPhone = findViewById(R.id.etPhone)

        btnAdd = findViewById(R.id.btnAdd)
        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
        btnShow = findViewById(R.id.btnShow)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = StudentAdapter(list)
        recyclerView.adapter = adapter

        dbRef = FirebaseDatabase.getInstance().getReference("Students")

        btnAdd.setOnClickListener {
            val id = etId.text.toString()
            val student = Student(
                id,
                etName.text.toString(),
                etSection.text.toString(),
                etPhone.text.toString()
            )
            dbRef.child(id).setValue(student)
            clear()
        }

        btnUpdate.setOnClickListener {
            val id = etId.text.toString()
            val map = HashMap<String, Any>()
            map["name"] = etName.text.toString()
            map["section"] = etSection.text.toString()
            map["phone"] = etPhone.text.toString()
            dbRef.child(id).updateChildren(map)
            clear()
        }

        btnDelete.setOnClickListener {
            val id = etId.text.toString()
            dbRef.child(id).removeValue()
            clear()
        }

        btnShow.setOnClickListener {
            fetchData()
        }
    }

    private fun fetchData() {
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                list.clear()
                for (s in snapshot.children) {
                    val st = s.getValue(Student::class.java)
                    if (st != null) list.add(st)
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

    private fun clear() {
        etId.text.clear()
        etName.text.clear()
        etSection.text.clear()
        etPhone.text.clear()
    }
}
