//강사님 추천 코드
package com.example.ml_kit_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ml_kit_practice.ui.theme.ML_Kit_PracticeTheme
import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.analytics
import com.google.firebase.analytics.logEvent
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions


class MainActivity : ComponentActivity() {
    private lateinit var firebaseAnalytics: FirebaseAnalytics
    override fun onCreate(savedInstanceState: Bundle?) {
        firebaseAnalytics = Firebase.analytics
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ML_Kit_PracticeTheme {
                Main(firebaseAnalytics)
            }
        }
    }
}

@Composable
fun Main(firebaseAnalytics: FirebaseAnalytics) {
    var inputText by remember { mutableStateOf("입력") }
    var tranText by remember { mutableStateOf("") }
    var textCount by remember { mutableIntStateOf(0) }
    val textSize by remember { mutableIntStateOf(20) }
    var id = "lms"
    var name = "lms"
    var isDownloaded by remember {
        mutableStateOf(false)
    }
    TransScreen(input = inputText, tranText = tranText, onInputChange = {
        inputText = it
        textCount = inputText.length
        firebaseAnalytics.logEvent("change_value") {
            param(FirebaseAnalytics.Param.ITEM_ID, id)
            param(FirebaseAnalytics.Param.ITEM_NAME, name)
            param(FirebaseAnalytics.Param.CONTENT_TYPE, "image")
            param("new_value", it)
        }
    }, onClickTranslate = {
        val translated = TranslationPart(TranslateLanguage.KOREAN, TranslateLanguage.ENGLISH)
        translated.download()
        translated.TranslateOnClick(inputText, onSuccess = { tranText = translated.tranText })
    })
}

@Composable
fun TransScreen(
    input: String,
    tranText: String,
    onInputChange: (String) -> Unit,
    onClickTranslate: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(80.dp))
        TextField(
            value = input,
            onValueChange = { newText -> onInputChange(newText) },
            modifier = Modifier
                .fillMaxWidth()
                .size(200.dp),
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            textStyle = TextStyle(fontSize = 12.sp)
        )
        Spacer(modifier = Modifier.size(30.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .size(200.dp)
                .background(
                    color = Color(0xfffaf2e6),
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(15.dp)
        ) {
            Text(
                text = tranText,
                fontSize = 12.sp
            )
        }
        Spacer(modifier = Modifier.size(30.dp))
        Button(
            onClick = { onClickTranslate() },
            enabled = true,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFd69f47)
            )
        ) {
            Text(text = "번역")

        }
    }
}

class TranslationPart(startLang: String, targetLang: String) {
    var tranText = ""
    val options = TranslatorOptions.Builder()
        .setSourceLanguage(startLang)
        .setTargetLanguage(targetLang)
        .build()
    val koenTranslator = Translation.getClient(options)
    var isDownloaded = false

//    @Composable
//    fun TranslateActive() {
//        DownloadModel( onSuccess = { this.isDownloaded = true })
//    }

    fun TranslateOnClick(inputText: String, onSuccess: (String) -> Unit) {
        this.koenTranslator.translate(inputText)
            .addOnSuccessListener { translatedText ->
                onSuccess(translatedText)
                this.tranText = translatedText
            }
    }

    fun download() {
        val conditions = DownloadConditions.Builder()
            .requireWifi()
            .build()
        koenTranslator.downloadModelIfNeeded(conditions)
            .addOnSuccessListener {
                isDownloaded = true
            }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ML_Kit_PracticeTheme {
        TransScreen("입력", "결과", {}, {})
    }
}

@Composable
fun Main_Preview() {
    var inputText by remember { mutableStateOf("입력") }
    var tranText by remember { mutableStateOf("") }
    var textCount by remember { mutableIntStateOf(0) }
    val textSize by remember { mutableIntStateOf(20) }
    var id = "lms"
    var name = "lms"
    var isDownloaded by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(80.dp))
        TextField(
            value = inputText,
            onValueChange = { newText ->
                inputText = newText
                textCount = inputText.length
            },
            modifier = Modifier
                .fillMaxWidth()
                .size(200.dp),
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            textStyle = TextStyle(fontSize = textSize.sp)
        )
        Spacer(modifier = Modifier.size(30.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .size(200.dp)
                .background(
                    color = Color(0xfffaf2e6),
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(15.dp)
        ) {
            Text(
                text = tranText,
                fontSize = textSize.sp
            )
        }
        Spacer(modifier = Modifier.size(30.dp))
        Button(
            onClick = {
                val translated =
                    TranslationPart(TranslateLanguage.KOREAN, TranslateLanguage.ENGLISH)
                translated.download()
                translated.TranslateOnClick(inputText, { tranText = translated.tranText })
                tranText = translated.tranText
            },
            enabled = isDownloaded,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFd69f47)
            )
        ) {
            Text(text = "번역")

        }
    }
}
