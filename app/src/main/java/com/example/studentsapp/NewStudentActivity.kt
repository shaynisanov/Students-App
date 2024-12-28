package com.example.studentsapp

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.studentsapp.model.Model
import com.example.studentsapp.model.Student

class NewStudentActivity : AppCompatActivity() {

    private var studentsList: MutableList<Student> = Model.shared.students

    private var nameTextField: EditText? = null
    private var idTextField: EditText? = null
    private var phoneTextField: EditText? = null
    private var addressTextField: EditText? = null
    private var isNewStudentChecked: CheckBox? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_new_student)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        nameTextField = findViewById(R.id.addStudentNameTextEdit)
        idTextField = findViewById(R.id.addStudentIdTextEdit)
        phoneTextField = findViewById(R.id.addStudentPhoneTextEdit)
        addressTextField = findViewById(R.id.addStudentAddressTextEdit)
        isNewStudentChecked = findViewById(R.id.addStudentCheckBox)

        findViewById<Button>(R.id.addStudentSaveButton).setOnClickListener{saveNewStudent()}

        findViewById<Button>(R.id.addStudentCancelButton).setOnClickListener {
            finish()
        }
    }

    private fun saveNewStudent() {
        val student = Student(
            name = nameTextField?.text?.toString()?.trim() ?: "",
            id = idTextField?.text?.toString()?.trim() ?: "",
            phone = phoneTextField?.text?.toString()?.trim() ?: "",
            address = addressTextField?.text?.toString()?.trim() ?: "",
            isChecked = isNewStudentChecked?.isChecked ?: false
        )

        studentsList.add(student)
        finish()
    }
}