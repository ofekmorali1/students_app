package com.example.students_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.students_app.ui.theme.Students_appTheme
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewStudents)
        val fab = findViewById<FloatingActionButton>(R.id.fabAddStudent)

        recyclerView.layoutManager = LinearLayoutManager(this)
        StudentRepository.add(Student("", "Student 1", 1))
        StudentRepository.add(Student("", "Student 2", id=2))
        StudentRepository.add(Student("", "Student 3", id=3))
        recyclerView.adapter = StudentAdapter(StudentRepository.getAll())

        fab.setOnClickListener {
            // open add-student dialog or activity
        }
    }
}
