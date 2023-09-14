package com.example.dactylographie

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import kotlin.properties.Delegates

class MemoryFrag : Fragment() {

    private lateinit var resultView: TextView
    private lateinit var act : MainActivity
    public lateinit var fragmentInput: InputFrag
    private lateinit var localStorage :LocalStorageSingleton.LocalStorageHelper
    public lateinit var Main: MainActivity
    private lateinit var fragmentScore: ScoreFrag
    public var motParSeconde: Float = 0F
    public var meilleurScore:Float = 0F
    public var dureeEnSeconde:String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_memory, container, false)
        resultView = v.findViewById(R.id.textView4)
        resultView.text = ("Votre score est de ")
        localStorage = LocalStorageSingleton.getLocalStorageHelper(requireContext())
        fragmentInput = InputFrag()
        fragmentScore = ScoreFrag()


        return v

    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Main = (activity as MainActivity)

    }

    fun calcMo() {
        val nombreDeMot = (localStorage.lireDonnee(MainActivity.scoreKey,0)).toString()
        dureeEnSeconde = (localStorage.lireDonnee(MainActivity.timeKey,0)).toString()
        dureeEnSeconde = (160-dureeEnSeconde.toFloat()).toString()
        println(dureeEnSeconde)
        println(nombreDeMot)
        motParSeconde = nombreDeMot.toFloat() / (dureeEnSeconde.toFloat())
    }

    fun meillScor() {
        if (meilleurScore <= motParSeconde){
            meilleurScore = motParSeconde
        }
    }

    val nombreDeSautsDeLigne = 2
    val sautsDeLigne = "\n".repeat(nombreDeSautsDeLigne)

    @SuppressLint("SetTextI18n")
    override fun onResume() {
        super.onResume()
        calcMo()
        meillScor()
        resultView.text = "Votre score est de ${localStorage.lireDonnee(MainActivity.scoreKey, 0)}" +
                ", votre temps est de $dureeEnSeconde secondes" +
                " ${sautsDeLigne}Vous avez donc une moyenne de $motParSeconde mot par seconde. " +
                "${sautsDeLigne}Votre meilleur score est de $meilleurScore"
    }



}



