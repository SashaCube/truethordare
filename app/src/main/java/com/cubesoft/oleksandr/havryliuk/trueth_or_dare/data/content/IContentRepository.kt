package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.data.content

import androidx.lifecycle.LiveData
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.data.content.model.Action
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.data.content.model.Question

interface IContentRepository {

    val questions: LiveData<List<Question>>

    val actions: LiveData<List<Action>>

    val state: LiveData<String>
}