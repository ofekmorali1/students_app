package com.example.students_app

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : ComponentActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var fab: FloatingActionButton
    private lateinit var topBar: MaterialToolbar
    private lateinit var adapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        topBar = findViewById<MaterialToolbar>(R.id.topAppBar)
        topBar.title = "Student List"

        recyclerView = findViewById<RecyclerView>(R.id.recyclerViewStudents)
        fab = findViewById<FloatingActionButton>(R.id.fabAddStudent)

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = StudentAdapter(StudentRepository.getAll()) { student ->
            val intent = Intent(this, StudentDetailsActivity::class.java)
            intent.putExtra("student_id", student.id)
            startActivity(intent)
        }

        recyclerView.adapter = adapter
        fab.setOnClickListener {
            startActivity(Intent(this, AddStudentActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }
}
