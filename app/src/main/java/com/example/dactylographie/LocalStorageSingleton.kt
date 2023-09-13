package com.example.dactylographie

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

object LocalStorageSingleton {

        @SuppressLint("StaticFieldLeak")
        var localStorageHelper: LocalStorageHelper? = null

        fun init(context: MainActivity) {
            if (localStorageHelper == null) {
                localStorageHelper = object: LocalStorageHelper(context) {}
            }
        }

        fun getLocalStorageHelper(requireContext: Context): LocalStorageHelper {
            if (localStorageHelper == null) {
                throw IllegalStateException("LocalStorageSingleton doit être initialisé avec le contexte.")
            }
            return localStorageHelper!!
        }



    open class LocalStorageHelper(private val context: MainActivity) {
        private val sharedPreferences: SharedPreferences =
            context.getSharedPreferences("score", Context.MODE_PRIVATE)

        fun sauvegarderDonnee(cle: String, valeur: Int) {
            val editor = sharedPreferences.edit()
            editor.putString(cle.toString(), valeur.toString())
            editor.apply()
        }

        fun lireDonnee(cle: String, valeurParDefaut: Int): Comparable<*> {
            return sharedPreferences.getString(cle.toString(), valeurParDefaut.toString()) ?: valeurParDefaut
        }
    }



}