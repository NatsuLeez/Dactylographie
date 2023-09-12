package com.example.dactylographie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.util.*



class ScoreFrag : Fragment() {

    private lateinit var editTextNumber3: TextView
    private lateinit var retryButton: Button
    private lateinit var localStorage :MainActivity.LocalStorageHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_score, container, false)
        editTextNumber3 = v.findViewById(R.id.editTextNumber3)
        retryButton = v.findViewById(R.id.buttonre)
        // Inflate the layout for this fragment
        localStorage = MainActivity.LocalStorageSingleton.getLocalStorageHelper(requireContext())
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retryButton.setOnClickListener{
            retry()
        }
    }

    fun retry() {
            if ((activity as MainActivity).isTimerRunning) {
                (activity as MainActivity).stopTimer()
        }
        (activity as MainActivity).clearInd()
        scorRein()
    }

    fun scorChange() {

        val valeurLue = localStorage.lireDonnee(MainActivity.scoreKey, 0)
        Log.d("valeur lue", valeurLue.toString())
        editTextNumber3.text = valeurLue.toString()
    }
    fun scorRein() {
        (activity as MainActivity).reinSco()
        val valeurLue = localStorage.lireDonnee(MainActivity.scoreKey, 0)
        editTextNumber3.text = valeurLue.toString()
    }
}
