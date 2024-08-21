package com.example.voiceai


import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.mushafconsolidated.R

import org.sj.conjugator.activity.BaseActivity
import java.util.Locale


class VoiceRecognitionActivity : BaseActivity() {
    private var spokenText: TextView? = null
    private lateinit var startListening: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_voice_recognition)
        spokenText = findViewById<TextView>(R.id.spokenText)
        startListening = findViewById<Button>(R.id.startListening)
        startListening.setOnClickListener(View.OnClickListener { view: View? -> startSpeechToText() })
    }

    private fun startSpeechToText() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak something...")
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                applicationContext,
                "Speech recognition not supported on your device",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_CODE_SPEECH_INPUT) {
            if (resultCode == RESULT_OK && data != null) {
                val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                if (result != null && !result.isEmpty()) {
                    val spokenTextResult = result[0]
                    spokenText!!.text = spokenTextResult
                    // Now you can process the spoken text as needed.
                }
            }
        }
    }

    companion object {
        private const val REQ_CODE_SPEECH_INPUT = 100
    }
}
