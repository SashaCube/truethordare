package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.data.content

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.data.content.model.Action
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.data.content.model.Question
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.util.*
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import javax.inject.Inject

class ContentRepository @Inject constructor() : IContentRepository {

    private val firestore by lazy { FirebaseFirestore.getInstance() }

    override val questions = MutableLiveData<List<Question>>()
    override val actions = MutableLiveData<List<Action>>()
    override val state = MutableLiveData<String>()

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

    private fun getContentReference() = firestore.collection(CONTENT).document(DOCUMENT_ID)

    @Suppress("UNCHECKED_CAST")
    private fun initActions(document: DocumentSnapshot) {
        actions.value = (document.data?.get(ACTIONS) as List<String>).map { Action(it) }
        Log.d(TAG_CONTENT, "loaded $ACTIONS: $actions")
    }

    @Suppress("UNCHECKED_CAST")
    private fun initQuestions(document: DocumentSnapshot) {
        questions.value = (document.data?.get(QUESTIONS) as List<String>).map { Question(it) }
        Log.d(TAG_CONTENT, "loaded $QUESTIONS: $questions")
    }

    private fun initFirebaseSettings() {
        val settings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(true)
            .build()
        firestore.firestoreSettings = settings
    }
}