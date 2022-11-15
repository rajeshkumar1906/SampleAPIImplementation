package com.rajeshkumar.sampleapiimplementation

import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ValidationVerificationTest{
    lateinit var student: ValidationVerification.Student
    lateinit var studentService: ValidationVerification.StudentService
    @Before
    fun setup(){
         studentService= mock(ValidationVerification.StudentService::class.java)
         student = ValidationVerification.Student(studentService)
    }

    @Test
    fun `check the interface functionality with mocking`(){
        Mockito.`when`(studentService.getNumberOfStudents()).thenReturn(10)
        Mockito.`when`(studentService.getTotalMarks()).thenReturn(500)

        val avergaeMarks  =student.getAverageMarks()
        Assert.assertEquals(50,avergaeMarks)
    }

}