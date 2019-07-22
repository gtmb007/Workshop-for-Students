package com.example.gautam.workshop.sharedPreferences

import android.content.Context
import android.content.SharedPreferences.Editor
import android.content.SharedPreferences

class SessionManager(internal var _context: Context) {

    internal var pref: SharedPreferences
    internal var editor: Editor
    internal var PRIVATE_MODE = 0

    val isLoggedIn: Boolean
        get() = pref.getBoolean(IS_LOGIN, false)
    val isKeyEmail: String
        get() = pref.getString(KEY_EMAIL, "")

    init {
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref.edit()
    }

    fun createLoginSession(email: String) {
        editor.putBoolean(IS_LOGIN, true)
        editor.putString(KEY_EMAIL, email)
        //KEY_EMAIL = email
        editor.commit()
    }

    fun checkLogin():Boolean {
        if(this.isLoggedIn)
            return true
        else
            return false
    }

    fun userDeatil():String {
        return isKeyEmail
    }

    fun logoutUser() {
        editor.clear()
        editor.commit()
    }

    companion object {
        private val PREF_NAME = "AndroidHivePref"
        private var IS_LOGIN  = "IsLoggedIn"
        val KEY_EMAIL = "email"
    }
}