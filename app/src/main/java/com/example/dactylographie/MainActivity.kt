package com.example.dactylographie

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.CountDownTimer
import android.text.Html
import android.text.SpannableString
import org.w3c.dom.Text
import android.text.Spannable
import android.text.style.ForegroundColorSpan
import androidx.constraintlayout.widget.ConstraintLayout
import java.lang.StringBuilder
import android.graphics.Color
import android.graphics.Color.BLUE
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private var list = mutableListOf<String>()
    private lateinit var myTextView: TextView
    private lateinit var myTimer: TextView
    public var ind: Int = 0
    private lateinit var fragmentScore: ScoreFrag
    private lateinit var fragmentInput: InputFrag
    lateinit var countDownTimer: CountDownTimer
    var isTimerRunning = false
    private lateinit var element: String
    var secondesRestantes: Long = 0


    @SuppressLint("MissingInflatedId", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_text)
        val tempsTotalEnMillisecondes: Long = 120000 //
        val intervalleEnMillisecondes: Long = 1000 // 1 seconde

        myTextView = findViewById(R.id.textView)
        myTimer = findViewById(R.id.textView2)
        val constraintLayout = findViewById<ConstraintLayout>(R.id.constraint_layout)
        val buttonRe = findViewById<Button>(R.id.buttonre)
        val buttonEnd = findViewById<Button>(R.id.buttonEnd)


        constraintLayout.setBackgroundColor(android.graphics.Color.parseColor("#7e9cd6"))

        buttonEnd.setOnClickListener{
            fragmentInput.end()
        }



        countDownTimer = object : CountDownTimer(tempsTotalEnMillisecondes, intervalleEnMillisecondes) {
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                secondesRestantes = millisUntilFinished / 1000
                isTimerRunning = true
                myTimer.text = "Temps restant : $secondesRestantes secondes"
            }
            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                myTimer.text = "Le minuteur est terminé."
            }

        }


        val test =  LoremIpsum(60)
        Log.d("logtest", test.values.count().toString())

        test.values.forEach {
            Log.d("logtest",it)
        }


        LocalStorageSingleton.init(this)

        myTextView.text = test.values.joinToString(separator = "")

        fragmentScore = ScoreFrag()
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragmentScore)
        fragmentInput = InputFrag()
        fragmentTransaction.replace(R.id.input_container, fragmentInput)
        fragmentTransaction.commit()

        val list1 = test.values

        for (i in list1) {
            val words = i.replace("\n", " ").replace(",", "").replace(".", "").split(" ")
            list.addAll(words)
        }


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

    open class LocalStorageHelper(private val context: Context) {
        private val sharedPreferences: SharedPreferences =
            context.getSharedPreferences("score", Context.MODE_PRIVATE)

        fun sauvegarderDonnee(cle: String, valeur: Int) {
            val editor = sharedPreferences.edit()
            editor.putString(cle.toString(), valeur.toString())
            editor.apply()
        }

        fun lireDonnee(cle: String, valeurParDefaut: Int): Comparable<*> {
            return sharedPreferences.getString(cle.toString(), valeurParDefaut.toString()) ?: valeurParDefaut
        }
    }

    object LocalStorageSingleton {
        @SuppressLint("StaticFieldLeak")
        private var localStorageHelper: LocalStorageHelper? = null

        fun init(context: Context) {
            if (localStorageHelper == null) {
                localStorageHelper = object: LocalStorageHelper(context) {}
            }
        }

        fun getLocalStorageHelper(requireContext: Context): LocalStorageHelper {
            if (localStorageHelper == null) {
                throw IllegalStateException("LocalStorageSingleton doit être initialisé avec le contexte.")
            }
            return localStorageHelper!!
        }

    }
    var temps = 0

    fun reinSco() = fragmentInput.reinScore()

    fun editScore() = fragmentScore.scorChange()

    fun timeStart() {
        countDownTimer.start()
        temps = 1
    }

    fun endFun() = fragmentInput.end()

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
    companion object {
        const val scoreKey = "SCORE_KEY"
    }
}

