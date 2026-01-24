package com.example.students_app

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar

class AddStudentActivity : AppCompatActivity() {
    private lateinit var topBar: MaterialToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)
        topBar = findViewById<MaterialToolbar>(R.id.topAppBar)
        topBar.title = "Add Student"

        val editName = findViewById<EditText>(R.id.editTextName)
        val editId = findViewById<EditText>(R.id.editTextId)
        val editPhone = findViewById<EditText>(R.id.editTextPhone)
        val editAddress = findViewById<EditText>(R.id.editTextAddress)
        val checkbox = findViewById<CheckBox>(R.id.checkboxChecked)
        val btnCancel = findViewById<Button>(R.id.buttonCancel)
        val btnSave = findViewById<Button>(R.id.buttonSave)

        btnCancel.setOnClickListener { finish() }

        btnSave.setOnClickListener {

            val name = editName.text.toString().trim()
            val id = editId.text.toString().trim()
            val checked = checkbox.isChecked
            val phone = editPhone.text.toString().trim()
            val address = editAddress.text.toString().trim()
            val existingStudent = StudentRepository.getById(id)
            if (existingStudent != null) {
                Toast.makeText(this, "Student already exists", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val student = Student("", name, id, phone, address)
            student.check = checked
            StudentRepository.add(student)

            setResult(RESULT_OK)
            finish()
        }
    }
}
