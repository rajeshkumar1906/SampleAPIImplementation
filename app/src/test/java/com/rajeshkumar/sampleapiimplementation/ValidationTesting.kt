package com.rajeshkumar.sampleapiimplementation

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import com.google.common.truth.Truth.assertThat

@RunWith(JUnit4::class)
class ValidationTesting {

    @Test
    fun `empty username return false` (){
        val validationVerification = ValidationVerification.getValidation(
            "",
            "1234",
        "1234"
        )

        assertThat(validationVerification).isFalse()
    }

    @Test
    fun `empty password return false`(){
        val validationVerification = ValidationVerification.getValidation(
            "Rajesh",
                   "",
        ""
        )

        assertThat(validationVerification).isFalse()
    }

    @Test
    fun `user name already existed return false`(){
        val validationVerification = ValidationVerification.getValidation(
            "Rajesh",
            "123",
            "123"
        )

        assertThat(validationVerification).isFalse()
    }

    @Test
    fun `password and confirm password not same return false`(){
        val validationTesting = ValidationVerification.getValidation(
            "Rajesh",
            "123",
            "1234"
        )

        assertThat(validationTesting).isFalse()
    }

    @Test
    fun `valid input details return true`(){
        val validationVerification = ValidationVerification.getValidation(
            "Raaz",
            "123",
            "123"
        )
        assertThat(validationVerification).isTrue()
    }

}