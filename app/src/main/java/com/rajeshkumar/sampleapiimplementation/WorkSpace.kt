package com.rajeshkumar.sampleapiimplementation

object ValidationVerification{

    val addedItems = listOf<String>("Rajesh","Kumar")
    fun getValidation(uname:String,password:String, confirmPassword:String): Boolean {

        if(uname.isEmpty()||password.isEmpty()){
            return false
        }
        if(uname in addedItems){
            return false
        }

        if(password!=confirmPassword){
            return false
        }
        return true

    }
}