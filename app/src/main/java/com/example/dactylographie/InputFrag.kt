package com.example.dactylographie


import android.os.Bundle
import android.text.InputFilter
import android.text.Spanned
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.google.android.material.textfield.TextInputEditText


class InputFrag : Fragment() {

    private lateinit var res: String
    private lateinit var act : MainActivity
    private lateinit var editText: EditText
    private lateinit var localStorage: MainActivity.LocalStorageHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.input_frag, container, false)
        editText = v.findViewById<TextInputEditText>(R.id.textInput)
        localStorage = MainActivity.LocalStorageSingleton.getLocalStorageHelper(requireContext())
        act = activity as MainActivity
        // Inflate the layout for this fragment
        return v
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val context = this

        // Set an InputFilter to detect spacebar presses
        editText?.filters = arrayOf(object : InputFilter {
            override fun filter(
                source: CharSequence?,
                start: Int,
                end: Int,
                dest: Spanned?,
                dstart: Int,
                dend: Int
            ): CharSequence? {
                // Check if the space character is being entered
                if (source?.contains(" ") == true) {
                    // Spacebar was pressed
                    context.handleSpacebarPress()
                }
                return null // Allow the input
            }
        })


    }
    lateinit var motActuel: String

    fun textActuel() {
        motActuel = act.parcourirEnBoucle()
    }

    fun texteSaisi() {
        res = editText.text.toString()
    }

    var score = 0

    data class Result(val value1: Int, val value2: String)

    fun end(): Any {
        val scoreFinal = score
        val tempsFinal = act.timeRecup()
        return Result(scoreFinal, tempsFinal)
    }

    fun verif(): Boolean? {
        texteSaisi()
        textActuel()
        if (res == motActuel) {
            score += 1
            act.editInd()
            localStorage.sauvegarderDonnee(MainActivity.scoreKey, score)
            act.editScore()
            editText.setText("")
            act.colorWord()
            return true
        }  else {
            println("wrong")
        }

        return null
    }

    fun reinScore() {
        score = 0
    }

    fun handleSpacebarPress() {
        if (act.temps != 1) {
            act.timeStart()
        }
        texteSaisi()
        verif()
    }


}