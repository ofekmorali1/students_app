package com.example.students_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar

class StudentDetailsActivity : AppCompatActivity() {

    private lateinit var topBar: MaterialToolbar
    private lateinit var student: Student

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        topBar = findViewById<MaterialToolbar>(R.id.topAppBar)
        topBar.title = "Student Details"

        val studentId = intent.getStringExtra("student_id")
        if (studentId == null) {
            Toast.makeText(this, "Student not found -1 ", Toast.LENGTH_SHORT).show()
            finish()
            return
        }
        student = StudentRepository.getById(studentId)!!
        if (student == null) {
            Toast.makeText(this, "Student not found - 2", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        val nameTextView = findViewById<TextView>(R.id.textViewName)
        val idTextView = findViewById<TextView>(R.id.textViewId)
        val phoneTextView = findViewById<TextView>(R.id.textViewPhone)
        val addressTextView = findViewById<TextView>(R.id.textViewAddress)
        val checkedCheckBox = findViewById<CheckBox>(R.id.checkboxChecked)
        val editButton = findViewById<Button>(R.id.buttonEdit)

        nameTextView.text = student.name
        idTextView.text = student.id
        phoneTextView.text = student.phone
        addressTextView.text = student.address
        checkedCheckBox.isChecked = student.check
        checkedCheckBox.isEnabled = false


        editButton.setOnClickListener {
            val intent = Intent(this, EditStudentDetailsActivity::class.java)
            intent.putExtra("student_id", student.id)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        populateStudentDetails()
    }

    private fun populateStudentDetails() {
        findViewById<TextView>(R.id.textViewName).text = student.name
        findViewById<TextView>(R.id.textViewId).text = student.id
        findViewById<TextView>(R.id.textViewPhone).text = student.phone
        findViewById<TextView>(R.id.textViewAddress).text = student.address
        findViewById<CheckBox>(R.id.checkboxChecked).isChecked = student.check
    }
}