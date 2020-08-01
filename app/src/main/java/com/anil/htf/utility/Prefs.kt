package com.anil.htf.utility

import android.content.Context
import android.content.SharedPreferences

class Prefs (context: Context) {
    val PREFS_FILENAME = "com.anil.htf.prefs"

    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0);


    var accessToken: String?
        get() = prefs.getString(UtilConstants.PREF_KEY_ACCESS_TOKEN, UtilConstants.PREF_KEY_ACCESS_TOKEN)
        set(value) = prefs.edit().putString(UtilConstants.PREF_KEY_ACCESS_TOKEN, value).apply()

    var tokenType: String?
        get() = prefs.getString(UtilConstants.PREF_KEY_TOKEN_TYPE, UtilConstants.PREF_KEY_TOKEN_TYPE)
        set(value) = prefs.edit().putString(UtilConstants.PREF_KEY_TOKEN_TYPE, value).apply()
}