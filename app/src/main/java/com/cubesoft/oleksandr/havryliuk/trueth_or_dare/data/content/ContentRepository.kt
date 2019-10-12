package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.data.content

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.util.*
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

class ContentRepository : ContentDataSource {

    private val firestore by lazy { FirebaseFirestore.getInstance() }

    private val questions = MutableLiveData<List<String>>()
    private val actions = MutableLiveData<List<String>>()
    private val state = MutableLiveData<String>()

    init {
        initFirebaseSettings()

        getContentReference().get()
            .addOnSuccessListener { document ->
                initQuestions(document)
                initActions(document)
                state.value = STATE_LOADED
            }
            .addOnFailureListener {
                state.value = STATE_EMPTY
            }
        Log.d(TAG_CONTENT, "state: ${state.value}")
    }

    override fun getQuestions() = questions

    override fun getActions() = actions

    override fun getState() = state

    private fun getContentReference() = firestore.collection(CONTENT).document(
        DOCUMENT_ID
    )

    private fun initActions(document: DocumentSnapshot) {
        actions.value = document.data?.get(ACTIONS) as List<String>
        Log.d(TAG_CONTENT, "loaded $ACTIONS: $actions")
    }

    private fun initQuestions(document: DocumentSnapshot) {
        questions.value = document.data?.get(QUESTIONS) as List<String>
        Log.d(TAG_CONTENT, "loaded $QUESTIONS: $questions")
    }

    private fun initFirebaseSettings() {
        val settings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(true)
            .build()
        firestore.firestoreSettings = settings
    }
}