package com.example.dactylographie

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class MemoryFrag : Fragment() {

    private lateinit var resultView: TextView
    private lateinit var act : MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_memory, container, false)
        resultView = v.findViewById(R.id.textView3)
        act = activity as MainActivity
        resultView.text = act.endFun().toString()
        return v
    }



}