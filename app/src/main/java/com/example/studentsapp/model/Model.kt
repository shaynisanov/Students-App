package com.example.studentsapp.model

class Model private constructor() {
    val students: MutableList<Student> = ArrayList()

    companion object {
        val shared = Model()
    }

    init {
        for (i in 0..4) {
            val student = Student(
                name = "Yosi Cohen $i",
                id = i.toString(),
                phone = "0522222222",
                address = "Tel Aviv",
                isChecked = false
            )
            students.add(student)
        }
    }
}