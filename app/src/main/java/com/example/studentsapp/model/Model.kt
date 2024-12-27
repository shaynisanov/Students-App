package com.example.studentsapp.model

class Model private constructor() {
    val students: MutableList<Student> = ArrayList()

    companion object {
        val shared = Model()
    }

    init {
        for (i in 0..20) {
            val student = Student(
                picUrl = "",
                name = "Yosi Cohen $i",
                id = i.toString(),
                isChecked = false
            )
            students.add(student)
        }
    }
}