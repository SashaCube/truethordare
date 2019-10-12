package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.data.content

import androidx.lifecycle.LiveData

interface IContentRepository {

    fun getQuestions(): LiveData<List<String>>

    fun getActions(): LiveData<List<String>>

    fun getState(): LiveData<String>
}