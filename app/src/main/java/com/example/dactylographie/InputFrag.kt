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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.input_frag, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editText = view.findViewById<TextInputEditText>(R.id.textInput)


        val motActuel = (activity as MainActivity).tourDeList()

        fun texteSaisi() {
            res = editText?.text.toString()
        }

        fun verif(): Boolean? {
            texteSaisi()
            if (res == motActuel) {
                return true
            }
            return null
        }

        fun handleSpacebarPress() {
            texteSaisi()
            verif()
            // Handle the spacebar press event here
            // Example: Show a toast message
            //Log.d("verif","gg $res")
            //Log.d("verif","gg $motActuel")
        }


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
                    handleSpacebarPress()
                }
                return null // Allow the input
            }
        })

    }



}