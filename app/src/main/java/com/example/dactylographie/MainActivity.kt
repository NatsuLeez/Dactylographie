package com.example.dactylographie

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.example.dactylographie.ui.theme.DactylographieTheme
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import android.annotation.SuppressLint
import android.text.Editable
import android.widget.Button
import android.widget.Toast
import androidx.compose.ui.text.TextLayoutInput
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import android.view.KeyEvent
import android.widget.EditText
import android.text.InputFilter
import android.text.Spanned
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputEditText


class MainActivity : AppCompatActivity() {

    public var words: String? = null
    private var list = mutableListOf<String>()
    public var newList = mutableListOf<String>()
    private lateinit var myTextView: TextView
    public var ind: Int = 0


    @SuppressLint("MissingInflatedId", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_text)

        myTextView = findViewById(R.id.textView)


        val test =  LoremIpsum(60)
        Log.d("logtest", test.values.count().toString())

        test.values.forEach {
            Log.d("logtest",it)
        }




        myTextView.text = test.values.joinToString(separator = "")

        val fragmentScore = ScoreFrag() // Create an instance of your fragment
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragmentScore)
        val fragmentInput = InputFrag()
        fragmentTransaction.replace(R.id.input_container, fragmentInput)
        fragmentTransaction.commit()

        val list1 = test.values

        for (i in list1) {
            val words = i.split(" ")
            list.addAll(words)
        }
}
    fun tourDeList(): String? {
        for ((index, element) in list.withIndex()) {
            ind = index
            newList.add(list[index])
            return list[index]
        }
        return null
    }

}

