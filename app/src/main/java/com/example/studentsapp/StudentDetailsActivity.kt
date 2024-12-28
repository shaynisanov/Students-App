package com.example.studentsapp

import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.studentsapp.model.Model
import com.example.studentsapp.model.Student

class StudentDetailsActivity : AppCompatActivity() {

    private var nameTextData: TextView? = null
    private var idTextData: TextView? = null
    private var phoneTextData: TextView? = null
    private var addressTextData: TextView? = null
    private var isChecked: CheckBox? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_student_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        nameTextData = findViewById(R.id.studentDetailsNameTextData)
        idTextData = findViewById(R.id.studentDetailsIdTextData)
        phoneTextData = findViewById(R.id.studentDetailsPhoneTextData)
        addressTextData = findViewById(R.id.studentDetailsAddressTextData)
        isChecked = findViewById(R.id.studentDetailsCheckBox)

        // Get the student object from studentsList activity
        val student = intent.getParcelableExtra<Student>("studentData")

        // Check if student data is passed
        student?.let{displayStudentDetails(it)}
    }

    private fun displayStudentDetails(student: Student) {
        nameTextData?.text = student.name
        idTextData?.text = student.id
        phoneTextData?.text = student.phone
        addressTextData?.text = student.address
        isChecked?.isChecked = student.isChecked
    }
}