package com.example.mobileproject1.Students.viewmodels

import androidx.lifecycle.ViewModel
import com.example.mobileproject1.Students.models.Student
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class StudentViewModel : ViewModel() {
    private val _students = MutableStateFlow(
        listOf(
            Student("Neder"),
            Student("Miguel"),
            Student("Parra"),
            Student("Alex"),
            Student("Jose")
        )
    )
    val students: StateFlow<List<Student>> = _students
}
