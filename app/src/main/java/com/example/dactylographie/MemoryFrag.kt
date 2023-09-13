package com.example.dactylographie

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2

class MemoryFrag : Fragment() {

    private lateinit var resultView: TextView
    private lateinit var act : MainActivity
    public var fragmentInput = InputFrag()
    public lateinit var Main: MainActivity
    private lateinit var viewPager2: ViewPager2


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_memory, container, false)

        resultView = v.findViewById(R.id.textView4)

        fragmentInput = InputFrag()
        resultView.text = LocalStorageSingleton.localStorageHelper?.lireDonnee("SCORE_KEY",0).toString()



        return v

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Main = (activity as MainActivity)

    }


}