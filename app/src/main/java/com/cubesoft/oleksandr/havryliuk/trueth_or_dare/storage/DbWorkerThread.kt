package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage

import android.os.Handler
import android.os.HandlerThread

class DbWorkerThread(threadName: String) : HandlerThread(threadName) {

    private var mWorkerHandler: Handler? = null

    override fun onLooperPrepared() {
        super.onLooperPrepared()
        mWorkerHandler = Handler(looper)
    }

    fun postTask(task: Runnable) {
        if (mWorkerHandler == null) {
            onLooperPrepared()
        }
        mWorkerHandler?.post(task)
    }
}