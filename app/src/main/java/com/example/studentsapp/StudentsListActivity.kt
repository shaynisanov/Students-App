package com.example.studentsapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.adapter.StudentViewHolder
import com.example.studentsapp.adapter.StudentsRecyclerAdapter
import com.example.studentsapp.model.Model
import com.example.studentsapp.model.Student

interface OnItemClickListener {
    fun onItemClick(position:Int, student: Student?)
}

class StudentsListActivity : AppCompatActivity() {

    private var studentsList: MutableList<Student> = Model.shared.students
    private var adapter: RecyclerView.Adapter<StudentViewHolder> = StudentsRecyclerAdapter(studentsList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_students_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Add student button click activity
        findViewById<Button>(R.id.addStudentButton)?.setOnClickListener {
            val intent = Intent(this, NewStudentActivity::class.java)
            startActivity(intent)
        }

        val recyclerView: RecyclerView = findViewById(R.id.studentsList)
        recyclerView.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        (adapter as StudentsRecyclerAdapter).listener = object : OnItemClickListener {
            override fun onItemClick(position:Int ,student: Student?) {
                val intent = Intent(this@StudentsListActivity, StudentDetailsActivity::class.java)
                intent.putExtra("studentData", student)
                intent.putExtra("studentPos", position)
                startActivity(intent)
            }
        }
        recyclerView.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }
}