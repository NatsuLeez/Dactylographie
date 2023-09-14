package com.example.dactylographie

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import kotlin.properties.Delegates


class MainFrag : Fragment() {

    private var list = mutableListOf<String>()
    private lateinit var myTextView: TextView
    private var myTimer: TextView? = null
    public var ind: Int = 0
    private lateinit var fragmentScore: ScoreFrag
    private lateinit var fragmentInput: InputFrag
    private lateinit var localStorage :LocalStorageSingleton.LocalStorageHelper
    lateinit var countDownTimer: CountDownTimer
    var isTimerRunning = false
    private lateinit var element: String
    var secondesRestantes: Long = 0
    public var tempsFinal = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.fragment_main, container, false)

        localStorage = LocalStorageSingleton.getLocalStorageHelper(requireContext())

        myTextView = v.findViewById(R.id.textView)
        myTimer = v.findViewById(R.id.textView2)

        fragmentScore = ScoreFrag()
        val childFragmentManager = childFragmentManager
        val fragmentTransaction = childFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragmentScore)
        fragmentInput = InputFrag()
        fragmentTransaction.replace(R.id.input_container, fragmentInput)
        fragmentTransaction.commit()

        val constraintLayout = v.findViewById<ConstraintLayout>(R.id.constraint_layout)
        val buttonRe = v.findViewById<Button>(R.id.buttonre)

        constraintLayout.setBackgroundColor(android.graphics.Color.parseColor("#7e9cd6"))


        val tempsTotalEnMillisecondes: Long = 160000 //
        val intervalleEnMillisecondes: Long = 1000 // 1 seconde

        countDownTimer = object : CountDownTimer(tempsTotalEnMillisecondes, intervalleEnMillisecondes) {
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                secondesRestantes = millisUntilFinished / 1000
                isTimerRunning = true
                myTimer?.text = "Temps restant : $secondesRestantes secondes"
                localStorage.sauvegarderDonnee(MainActivity.timeKey, secondesRestantes.toInt())
            }
            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                myTimer?.text = "Le minuteur est terminé."
                tempsFinal = secondesRestantes.toInt()
            }

        }
        val test =  LoremIpsum(60)
        Log.d("logtest", test.values.count().toString())

        test.values.forEach {
            Log.d("logtest",it)
        }

        val list1 = test.values

        for (i in list1) {
            val words = i.replace("\n", " ").replace(",", "").replace(".", "").split(" ")
            list.addAll(words)
        }

        myTextView.text = test.values.joinToString(separator = "")

        return v
    }

    fun colorWord() {
        val partiColor = "<font color='#4287f5'>${list.subList(0, ind).joinToString(" ")}</font> <font color='#ffffff'>${list.subList(ind, list.size).joinToString(" ")}</font>"
        myTextView.text = Html.fromHtml(partiColor, Html.FROM_HTML_MODE_LEGACY)
    }

    fun timeRecup(): String {
        return "Temps restant : $secondesRestantes secondes"
    }

    fun stopTimer() {
        countDownTimer.cancel()
        temps = 0
        isTimerRunning = false
    }

    var temps = 0

    fun reinSco() = fragmentInput.reinScore()

    fun editScore() = fragmentScore.scorChange()

    fun timeStart() {
        countDownTimer.start()
        temps = 1
    }



    fun editInd() {
        ind ++
    }
    fun clearInd() {
        ind = 0
    }

    fun parcourirEnBoucle(): String {

        element = list[ind]
        Log.d("chepa","gg $element")
        return element

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}
