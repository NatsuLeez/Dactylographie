package com.example.dactylographie


import android.os.Bundle
import android.text.InputFilter
import android.text.Spanned
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.google.android.material.textfield.TextInputEditText


class InputFrag : Fragment() {

    private lateinit var res: String
    private lateinit var editText: EditText
    private lateinit var localStorage: LocalStorageSingleton.LocalStorageHelper
    public lateinit var fragmentMain: MainFrag

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.input_frag, container, false)
        editText = v.findViewById<TextInputEditText>(R.id.textInput)
        localStorage = LocalStorageSingleton.getLocalStorageHelper(requireContext())

        // Inflate the layout for this fragment
        return v
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentMain = parentFragment as MainFrag

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
        motActuel = fragmentMain.parcourirEnBoucle()
    }

    fun texteSaisi() {
        res = editText.text.toString()
    }

    var score = 0

    data class Result(val value1: String, val value2: String)



    fun verif(): Boolean? {
        texteSaisi()
        textActuel()
        if (res == motActuel) {
            score += 1
            fragmentMain.editInd()
            localStorage.sauvegarderDonnee(MainActivity.scoreKey, score)
            fragmentMain.editScore()
            editText.setText("")
            fragmentMain.colorWord()
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
        if (fragmentMain.temps != 1) {
            fragmentMain.timeStart()
        }
        texteSaisi()
        verif()
    }


}