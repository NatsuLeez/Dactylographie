package com.example.dactylographie

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.example.dactylographie.ui.theme.DactylographieTheme

class MainActivity : ComponentActivity() {

    public var words: Int? = null
    private lateinit var myTextView: TextView
    private lateinit var text: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_text)

        myTextView = findViewById(R.id.textView)
        text = myTextView.text.toString()


        val loremIpsum = LoremIpsum()
        text = loremIpsum.toString()
    }



}
