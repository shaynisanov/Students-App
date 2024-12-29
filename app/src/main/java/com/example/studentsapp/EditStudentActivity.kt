package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.studentsapp.model.Model
import com.example.studentsapp.model.Student

class EditStudentActivity : AppCompatActivity() {

    private var studentList = Model.shared.students
    private var studentPos: Int? = null
    private var student: Student? = null
    private var nameTextField: EditText? = null
    private var idTextField: EditText? = null
    private var phoneTextField: EditText? = null
    private var addressTextField: EditText? = null
    private var isNewStudentChecked: CheckBox? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_student)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        nameTextField = findViewById(R.id.editStudentNameTextEdit)
        idTextField = findViewById(R.id.editStudentIdTextEdit)
        phoneTextField = findViewById(R.id.editStudentPhoneTextEdit)
        addressTextField = findViewById(R.id.editStudentAddressTextEdit)
        isNewStudentChecked = findViewById(R.id.editStudentCheckBox)

        student = intent.getParcelableExtra<Student>("studentData")
        studentPos = intent.getIntExtra("studentPos", -1)

        student?.let{displayStudentDetails(it)}

        findViewById<Button>(R.id.editStudentSaveButton).setOnClickListener{saveEditedStudent()}
        findViewById<Button>(R.id.editStudentDeleteButton).setOnClickListener{deleteEditedStudent()}
        findViewById<Button>(R.id.editStudentCancelButton).setOnClickListener{finish()}
    }

    private fun displayStudentDetails(student: Student) {
        nameTextField?.setText(student.name)
        idTextField?.setText(student.id)
        phoneTextField?.setText(student.phone)
        addressTextField?.setText(student.address)
        isNewStudentChecked?.isChecked = student.isChecked
    }

    private fun saveEditedStudent() {
        // Get the updated data from the EditTexts
        val updatedName = nameTextField?.text.toString()
        val updatedId = idTextField?.text.toString()
        val updatedPhone = phoneTextField?.text.toString()
        val updatedAddress = addressTextField?.text.toString()
        val updatedCheck = isNewStudentChecked?.isChecked

        // Update the student object with new data
        student?.name = updatedName
        student?.id = updatedId
        student?.phone = updatedPhone
        student?.address = updatedAddress
        student?.isChecked = updatedCheck ?: false
        studentList[studentPos!!] = student!!

        val resultIntent = Intent()
        resultIntent.putExtra("updatedStudent", student)  // Pass the updated student object
        setResult(RESULT_OK, resultIntent)

        Toast.makeText(this, "Student updated", Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun deleteEditedStudent() {
        studentPos?.let {studentList.removeAt(it)}
        setResult(RESULT_OK, Intent())
        finish()
    }
}