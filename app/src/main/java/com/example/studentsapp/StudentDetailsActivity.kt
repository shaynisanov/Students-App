package com.example.studentsapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
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

    private val REQUEST_EDIT_STUDENT = 1  // Unique request code for EditStudentActivity
    private var student: Student? = null
    private var studentPos: Int? = null

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
        student = intent.getParcelableExtra<Student>("studentData")
        studentPos = intent.getIntExtra("studentPos",-1)

        // Check if student data is passed
        student?.let{displayStudentDetails(it)}

        findViewById<Button>(R.id.studentDetailsEditButton).setOnClickListener{
            val intent = Intent(this, EditStudentActivity::class.java)
            intent.putExtra("studentData", student)
            intent.putExtra("studentPos", studentPos)
            startActivityForResult(intent, REQUEST_EDIT_STUDENT)
        }
    }

    private fun displayStudentDetails(student: Student) {
        nameTextData?.text = student.name
        idTextData?.text = student.id
        phoneTextData?.text = student.phone
        addressTextData?.text = student.address
        isChecked?.isChecked = student.isChecked
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_EDIT_STUDENT && resultCode == RESULT_OK) {
            if (data != null){
                val updatedStudent = data.getSerializableExtra("updatedStudent") as? Student
                updatedStudent?.let {
                    // Update the student object
                    student = it
                    // Update the TextViews to display the new data
                    displayStudentDetails(it)
                }
            }
            finish()
        }
    }
}