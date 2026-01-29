package com.example.students_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Student (val picturePath: String, val name: String, val id: String, val phone: String, val address: String)  {
    var check: Boolean = false

    fun setCheck() {
        check = !check
    }
}

object StudentRepository {
    private val students = mutableListOf<Student>()

    fun add(student: Student) {
        students.add(student)
    }
    fun remove(student: Student){
        students.remove(student)
    }
    fun getAll(): List<Student> {
        return students
    }
    fun getById(id: String): Student? {
        return students.find { it.id == id }
    }

}

class StudentAdapter(
    private val students: List<Student>,
    private val onStudentClick: (Student) -> Unit
) : RecyclerView.Adapter<StudentAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.textName)
        val id: TextView = view.findViewById(R.id.textId)
        val check: CheckBox = view.findViewById(R.id.checkbox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.student_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val student = students[position]
        holder.name.text = student.name
        holder.id.text = student.id
        holder.check.isChecked = student.check

        holder.check.setOnClickListener {
            student.setCheck()
            holder.check.isChecked = student.check
        }

        holder.itemView.setOnClickListener {
            onStudentClick(student)
        }
    }

    override fun getItemCount() = students.size
}
