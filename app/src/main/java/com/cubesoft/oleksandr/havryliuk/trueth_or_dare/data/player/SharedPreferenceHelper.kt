package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.data.player

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

class SharedPreferenceHelper {

    companion object {
        private lateinit var sPref: SharedPreferences
        private lateinit var edit: SharedPreferences.Editor

        fun init(context: Context) {
            sPref = PreferenceManager.getDefaultSharedPreferences(context)
            edit = sPref.edit()
        }

        fun setStringList(key: String, value: List<String>) {
            edit.putStringSet(key, value.toMutableSet())
            edit.commit()
        }

        fun getStringList(key: String): List<String> =
            sPref.getStringSet(key, mutableSetOf()).toList()
    }
}