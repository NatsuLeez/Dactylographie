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
import kotlin.properties.Delegates


class ScoreFrag : Fragment() {

    //var endButtonPressed = true
    private lateinit var editTextNumber3: TextView
    private lateinit var retryButton: Button
    lateinit var endButton: Button
    private lateinit var localStorage :LocalStorageSingleton.LocalStorageHelper
    public lateinit var fragmentMain: MainFrag
    private lateinit var fragmentInput: InputFrag
    private lateinit var fragmentMem: MemoryFrag
    private lateinit var fragmentScore: ScoreFrag



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_score, container, false)
        editTextNumber3 = v.findViewById(R.id.editTextNumber3)
        retryButton = v.findViewById(R.id.buttonre)
        endButton = v.findViewById(R.id.buttonEnd)
        fragmentInput = InputFrag()

        // Inflate the layout for this fragment
        localStorage = LocalStorageSingleton.getLocalStorageHelper(requireContext())
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        fragmentMain = parentFragment as MainFrag
        fragmentMem = MemoryFrag()
        fragmentScore = ScoreFrag()

        retryButton.setOnClickListener{
            retry()
        }

        endButton.setOnClickListener{
            end()
        }
    }




    fun end() {
        if (fragmentMain.isTimerRunning) {
            fragmentMain.stopTimer()
        }
        fragmentMain.clearInd()
    }


    fun retry() {
        if (fragmentMain.isTimerRunning) {
            fragmentMain.stopTimer()
        }
        fragmentMain.clearInd()
        scorRein()
    }

    fun scorChange() {

        val valeurLue = localStorage.lireDonnee(MainActivity.scoreKey, 0)
        Log.d("valeur lue", valeurLue.toString())
        editTextNumber3.text = valeurLue.toString()
    }
    fun scorRein() {
        fragmentMain.reinSco()
        val valeurLue = localStorage.lireDonnee(MainActivity.scoreKey, 0)
        editTextNumber3.text = valeurLue.toString()
    }


}