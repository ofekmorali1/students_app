package com.example.students_app

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import android.content.Intent
class EditStudentDetailsActivity : AppCompatActivity() {

    private lateinit var topBar: MaterialToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student_details)

        topBar = findViewById(R.id.topAppBar)
        topBar.title = "Edit Student"
        topBar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        val studentId = intent.getStringExtra("student_id")
        if (studentId == null) {
            Toast.makeText(this, "Student not found", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        val student = StudentRepository.getById(studentId)
        if (student == null) {
            Toast.makeText(this, "Student not found", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        val editName = findViewById<EditText>(R.id.editStudentName)
        val editId = findViewById<EditText>(R.id.editStudentId)
        val editPhone = findViewById<EditText>(R.id.editStudentPhone)
        val editAddress = findViewById<EditText>(R.id.editStudentAddress)
        val checkbox = findViewById<CheckBox>(R.id.checkboxChecked)
        val buttonCancel = findViewById<Button>(R.id.buttonCancel)
        val buttonSave = findViewById<Button>(R.id.buttonSave)
        val buttonDelete = findViewById<Button>(R.id.buttonDelete)

        editName.setText(student.name)
        editId.setText(student.id)
        editPhone.setText(student.phone)
        editAddress.setText(student.address)
        checkbox.isChecked = student.check

        buttonCancel.setOnClickListener { finish() }

        buttonSave.setOnClickListener {
            val newName = editName.text.toString()
            val newId = editId.text.toString()
            val newPhone = editPhone.text.toString()
            val newAddress = editAddress.text.toString()
            val newCheck = checkbox.isChecked

            val success = StudentRepository.updateStudent(student, newName, newId, newPhone, newAddress, newCheck)

            if (!success) {
                Toast.makeText(this, "Student ID already exists", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Toast.makeText(this, "Student updated", Toast.LENGTH_SHORT).show()
            finish()
        }

        buttonDelete.setOnClickListener {
            StudentRepository.delete(student.id)

            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }
}
