package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.remote

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore

class RemoteDatabase(
    val mDb: FirebaseFirestore
) {

    companion object {
        const val TAG = "RemoteDatabase"
    }

    fun uploadContent(questions: List<String>, actions: List<String>) {
        // Create a new user with a first and last name
        val content = hashMapOf(
            "questions" to questions,
            "actions" to actions
        )

        // Add a new document with a generated ID
        mDb.collection("content")
            .add(content)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }

    }


}