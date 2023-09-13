package com.example.dactylographie



import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import android.annotation.SuppressLint
import android.os.CountDownTimer
import android.text.Html
import android.widget.Adapter
import androidx.constraintlayout.widget.ConstraintLayout
import android.widget.Button
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {


    lateinit var viewPager2: ViewPager2



    @SuppressLint("MissingInflatedId", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_text)



        LocalStorageSingleton.init(this)



    }

    companion object {
        const val scoreKey = "SCORE_KEY"
    }
}

