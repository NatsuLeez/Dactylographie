package com.example.dactylographie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText


class ScoreFrag : Fragment() {

    private val editTextNumber3: EditText? = view?.findViewById(R.id.editTextNumber3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_score, container, false)
    }
        fun scorChange() {
            var nouveauContenu = 0// Nouveau contenu que vous souhaitez définir
            nouveauContenu += 1
            editTextNumber3?.setText(nouveauContenu.toString())
        }
        if (verif() == true) {
            scorChange()
        }
    }
