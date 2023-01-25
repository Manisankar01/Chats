package com.example.chats

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.*
import com.google.firebase.firestore.FirebaseFirestore


class FireBaseMethods {
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    var fireBase: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val activity = MainActivity()
    fun creteAccount(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        mobileNumber: String, context: Context
    ):Boolean{

        try {
            var user: Task<AuthResult> =
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("SignUp-status", "SignUp-Successful")
                        Toast.makeText(
                            context, "Sign-Up successful",
                            Toast.LENGTH_SHORT
                        ).show()
                        val user = FirebaseAuth.getInstance().currentUser
                        val profileUpdates = UserProfileChangeRequest.Builder()
                            .setDisplayName(firstName).build()
                        user?.updateProfile(profileUpdates)
                        val chatRoomsList = mutableListOf<String>()
                        val userDetails = hashMapOf(
                            "uniqueId" to auth.currentUser?.uid,
                            "profile" to "",
                            "firstName" to firstName,
                            "lastName" to lastName,
                            "email" to email,
                            "status" to "Online",
                            "chatRooms" to chatRoomsList,
                            "mobileNumber" to mobileNumber
                        )
                        auth.currentUser?.uid?.let {
                            fireBase.collection("users").document(it).set(userDetails)
                        }
                        return@addOnCompleteListener


                    } else {
                        Log.d("SignUp-status", "SignUp-Failed")

                        Toast.makeText(
                            context, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                        return@addOnCompleteListener

                    }

                }
        } catch (e: Exception) {
            println("Oops!");
            return false
        }
        return  true
    }

    fun userLogin(userEmail: String, password: String, context: Context) {

        auth.signInWithEmailAndPassword(userEmail, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d("SignIn-status", "SignIn-Successful")
            } else {
                Log.d("SignIn-status", "SignIn-Failed")

                Toast.makeText(
                    context, "Sign in failed.",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
    }
}